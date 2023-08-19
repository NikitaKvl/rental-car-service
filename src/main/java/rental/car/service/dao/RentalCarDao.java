package rental.car.service.dao;

import java.util.List;
import rental.car.service.model.RentalCar;

public interface RentalCarDao extends GenericDao<RentalCar> {
    List<RentalCar> getAllByRenter(Long renterId);
}
