package rental.car.service.dao;

import java.util.Optional;
import rental.car.service.model.Renter;

public interface RenterDao extends GenericDao<Renter> {
    Optional<Renter> findByLogin(String login);
}
