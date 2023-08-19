package rental.car.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import rental.car.service.dao.RentalCarDao;
import rental.car.service.exception.DataProcessingException;
import rental.car.service.lib.Dao;
import rental.car.service.model.Manufacturer;
import rental.car.service.model.RentalCar;
import rental.car.service.model.Renter;
import rental.car.service.util.ConnectionUtil;

@Dao
public class RentalCarDaoImpl implements RentalCarDao {
    @Override
    public RentalCar create(RentalCar rentalCar) {
        String query = "INSERT INTO rental_cars (model, manufacturer_id)"
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                         connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, rentalCar.getModel());
            statement.setLong(2, rentalCar.getManufacturer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                rentalCar.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create rental car " + rentalCar, e);
        }
        insertAllRenters(rentalCar);
        return rentalCar;
    }

    @Override
    public Optional<RentalCar> get(Long id) {
        String query = "SELECT rc.id AS id, "
                + "model, "
                + "manufacturer_id, "
                + "m.name AS manufacturer_name, "
                + "m.country AS manufacturer_country "
                + "FROM rental_cars rc "
                + "JOIN manufacturers m ON rc.manufacturer_id = m.id "
                + "WHERE rc.id = ? AND rc.is_deleted = FALSE";
        RentalCar rentalCar = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rentalCar = parseRentalCarFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get rental car by id: " + id, e);
        }
        if (rentalCar != null) {
            rentalCar.setRenters(getAllRentersByRentalCarId(rentalCar.getId()));
        }
        return Optional.ofNullable(rentalCar);
    }

    @Override
    public List<RentalCar> getAll() {
        String query = "SELECT rc.id AS id, "
                + "model, "
                + "manufacturer_id, "
                + "m.name AS manufacturer_name, "
                + "m.country AS manufacturer_country "
                + "FROM rental_cars rc "
                + "JOIN manufacturers m ON rc.manufacturer_id = m.id "
                + "WHERE rc.is_deleted = FALSE";
        List<RentalCar> rentalCars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rentalCars.add(parseRentalCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all rental cars", e);
        }
        rentalCars.forEach(rentalCar ->
                rentalCar.setRenters(getAllRentersByRentalCarId(rentalCar.getId())));
        return rentalCars;
    }

    @Override
    public RentalCar update(RentalCar rentalCar) {
        String query = "UPDATE rental_cars SET model = ?, manufacturer_id = ? WHERE id = ? "
                + "AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, rentalCar.getModel());
            statement.setLong(2, rentalCar.getManufacturer().getId());
            statement.setLong(3, rentalCar.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update rental car " + rentalCar, e);
        }
        deleteAllRenters(rentalCar);
        insertAllRenters(rentalCar);
        return rentalCar;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE rental_cars SET is_deleted = TRUE WHERE id = ? "
                + "AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete rental car by id " + id, e);
        }
    }

    @Override
    public List<RentalCar> getAllByRenter(Long renterId) {
        String query = "SELECT rc.id AS id, "
                + "model, "
                + "manufacturer_id, "
                + "m.name AS manufacturer_name, "
                + "m.country AS manufacturer_country "
                + "FROM rental_cars rc "
                + "JOIN manufacturers m ON rc.manufacturer_id = m.id "
                + "JOIN rental_cars_renters rcr ON rc.id = rcr.car_id "
                + "JOIN renters r ON rcr.renter_id = r.id "
                + "WHERE rc.is_deleted = FALSE AND renter_id = ? "
                + "AND rc.is_deleted = FALSE";
        List<RentalCar> rentalCars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, renterId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rentalCars.add(parseRentalCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all rental cars for renter with id: "
                    + renterId, e);
        }
        rentalCars.forEach(rentalCar ->
                rentalCar.setRenters(getAllRentersByRentalCarId(rentalCar.getId())));
        return rentalCars;
    }

    private void insertAllRenters(RentalCar rentalCar) {
        List<Renter> renters = rentalCar.getRenters();
        if (renters.size() == 0) {
            return;
        }
        String query = "INSERT INTO rental_cars_renters (rental_car_id, renter_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, rentalCar.getId());
            for (Renter renter : renters) {
                statement.setLong(2, renter.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't insert renters " + renters, e);
        }
    }

    private void deleteAllRenters(RentalCar rentalCar) {
        String query = "DELETE FROM rental_cars_renters WHERE rental_car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, rentalCar.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete renters " + rentalCar.getRenters()
                    + " of rental car with id: " + rentalCar.getId(), e);
        }
    }

    private List<Renter> getAllRentersByRentalCarId(Long rentalCarId) {
        String query = "SELECT id, name, driver_license_number, login, password "
                + "FROM rental_cars_renters rcr "
                + "JOIN renters r ON rcr.renter_id = r.id "
                + "WHERE rental_car_id = ? AND is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, rentalCarId);
            ResultSet resultSet = statement.executeQuery();
            List<Renter> renters = new ArrayList<>();
            while (resultSet.next()) {
                renters.add(parseRenterFromResultSet(resultSet));
            }
            return renters;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all renters by rental car id"
                    + rentalCarId, e);
        }
    }

    private Renter parseRenterFromResultSet(ResultSet resultSet) throws SQLException {
        Long renterId = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String driverLicenseNumber = resultSet.getString("driver_license_number");
        Renter renter = new Renter();
        renter.setId(renterId);
        renter.setName(name);
        renter.setLogin(login);
        renter.setPassword(password);
        renter.setDriverLicenseNumber(driverLicenseNumber);
        return renter;
    }

    private RentalCar parseRentalCarFromResultSet(ResultSet resultSet) throws SQLException {
        Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
        String manufacturerName = resultSet.getString("manufacturer_name");
        String manufacturerCountry = resultSet.getString("manufacturer_country");
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        manufacturer.setName(manufacturerName);
        manufacturer.setCountry(manufacturerCountry);
        Long rentalCarId = resultSet.getObject("id", Long.class);
        String model = resultSet.getString("model");
        RentalCar rentalCar = new RentalCar();
        rentalCar.setId(rentalCarId);
        rentalCar.setModel(model);
        rentalCar.setManufacturer(manufacturer);
        return rentalCar;
    }
}
