package rental.car.service.service;

import java.util.List;
import rental.car.service.model.RentalCar;
import rental.car.service.model.Renter;

public interface RentalCarService extends GenericService<RentalCar> {
    void addRenterToRentalCar(Renter renter, RentalCar rentalCar);

    List<RentalCar> getAllByRenter(Long renterId);
}
