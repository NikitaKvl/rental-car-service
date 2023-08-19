package rental.car.service.service.impl;

import java.util.Optional;
import rental.car.service.exception.AuthenticationException;
import rental.car.service.lib.Inject;
import rental.car.service.lib.Service;
import rental.car.service.model.Renter;
import rental.car.service.service.AuthenticationService;
import rental.car.service.service.RenterService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private RenterService renterService;

    @Override
    public Renter login(String login, String password) throws AuthenticationException {
        Optional<Renter> renter = renterService.findByLogin(login);
        if (renter.isEmpty() || !renter.get().getPassword().equals(password)) {
            throw new AuthenticationException("Login or password is incorrect.");
        }
        return renter.get();
    }
}
