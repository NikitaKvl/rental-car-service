package rental.car.service.service.impl;

import java.util.List;
import java.util.Optional;
import rental.car.service.dao.RenterDao;
import rental.car.service.lib.Inject;
import rental.car.service.lib.Service;
import rental.car.service.model.Renter;
import rental.car.service.service.RenterService;

@Service
public class RenterServiceImpl implements RenterService {
    @Inject
    private RenterDao renterDao;

    @Override
    public Renter create(Renter renter) {
        return renterDao.create(renter);
    }

    @Override
    public Optional<Renter> get(Long id) {
        return renterDao.get(id);
    }

    @Override
    public List<Renter> getAll() {
        return renterDao.getAll();
    }

    @Override
    public Renter update(Renter renter) {
        return renterDao.update(renter);
    }

    @Override
    public boolean delete(Long id) {
        return renterDao.delete(id);
    }

    @Override
    public Optional<Renter> findByLogin(String login) {
        return renterDao.findByLogin(login);
    }
}
