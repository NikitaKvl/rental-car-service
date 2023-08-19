package rental.car.service.service.impl;

import java.util.List;
import java.util.Optional;
import rental.car.service.dao.RentalCarDao;
import rental.car.service.lib.Inject;
import rental.car.service.lib.Service;
import rental.car.service.model.RentalCar;
import rental.car.service.model.Renter;
import rental.car.service.service.RentalCarService;

@Service
public class RentalCarServiceImpl implements RentalCarService {
    @Inject
    private RentalCarDao rentalCarDao;

    @Override
    public void addRenterToRentalCar(Renter renter, RentalCar rentalCar) {
        rentalCar.getRenters().add(renter);
        rentalCarDao.update(rentalCar);
    }

    @Override
    public List<RentalCar> getAllByRenter(Long renterId) {
        return rentalCarDao.getAllByRenter(renterId);
    }

    @Override
    public RentalCar create(RentalCar rentalCar) {
        return rentalCarDao.create(rentalCar);
    }

    @Override
    public Optional<RentalCar> get(Long id) {
        return rentalCarDao.get(id);
    }

    @Override
    public List<RentalCar> getAll() {
        return rentalCarDao.getAll();
    }

    @Override
    public RentalCar update(RentalCar rentalCar) {
        return rentalCarDao.update(rentalCar);
    }

    @Override
    public boolean delete(Long id) {
        return rentalCarDao.delete(id);
    }
}
