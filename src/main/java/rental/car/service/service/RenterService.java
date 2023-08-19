package rental.car.service.service;

import java.util.Optional;
import rental.car.service.model.Renter;

public interface RenterService extends GenericService<Renter> {
    Optional<Renter> findByLogin(String login);
}
