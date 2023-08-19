package rental.car.service.service;

import rental.car.service.exception.AuthenticationException;
import rental.car.service.model.Renter;

public interface AuthenticationService {
    Renter login(String login, String password) throws AuthenticationException;
}
