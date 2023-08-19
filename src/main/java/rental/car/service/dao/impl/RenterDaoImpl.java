package rental.car.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import rental.car.service.dao.RenterDao;
import rental.car.service.exception.DataProcessingException;
import rental.car.service.lib.Dao;
import rental.car.service.model.Renter;
import rental.car.service.util.ConnectionUtil;

@Dao
public class RenterDaoImpl implements RenterDao {
    @Override
    public Renter create(Renter renter) {
        String query = "INSERT INTO renters (name, driver_license_number, login, password) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, renter.getName());
            statement.setString(2, renter.getDriverLicenseNumber());
            statement.setString(3, renter.getLogin());
            statement.setString(4, renter.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                renter.setId(resultSet.getObject(1, Long.class));
            }
            return renter;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create renter " + renter, e);
        }
    }

    @Override
    public Optional<Renter> get(Long id) {
        String query = "SELECT * FROM renters WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Renter renter = null;
            if (resultSet.next()) {
                renter = parseRenterFromResultSet(resultSet);
            }
            return Optional.ofNullable(renter);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get renter by id " + id, e);
        }
    }

    @Override
    public List<Renter> getAll() {
        String query = "SELECT * FROM renters WHERE is_deleted = FALSE";
        List<Renter> renters = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                renters.add(parseRenterFromResultSet(resultSet));
            }
            return renters;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a list of renters.", e);
        }
    }

    @Override
    public Renter update(Renter renter) {
        String query = "UPDATE renters "
                + "SET name = ?, driver_license_number = ?, login = ?, password = ? "
                + "WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, renter.getName());
            statement.setString(2, renter.getDriverLicenseNumber());
            statement.setString(3, renter.getLogin());
            statement.setString(4, renter.getPassword());
            statement.setLong(5, renter.getId());
            statement.executeUpdate();
            return renter;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update renter" + renter, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE renters SET is_deleted = TRUE WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete renter with id " + id, e);
        }
    }

    @Override
    public Optional<Renter> findByLogin(String login) {
        String query = "SELECT * FROM renters WHERE login = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Renter renter = null;
            if (resultSet.next()) {
                renter = parseRenterFromResultSet(resultSet);
            }
            return Optional.ofNullable(renter);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get renter by login " + login, e);
        }
    }

    private Renter parseRenterFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String driverLicenseNumber = resultSet.getString("driver_license_number");
        Renter renter = new Renter();
        renter.setId(id);
        renter.setName(name);
        renter.setLogin(login);
        renter.setPassword(password);
        renter.setDriverLicenseNumber(driverLicenseNumber);
        return renter;
    }
}
