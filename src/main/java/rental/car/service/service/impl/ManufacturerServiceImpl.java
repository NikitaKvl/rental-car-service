package rental.car.service.service.impl;

import java.util.List;
import java.util.Optional;
import rental.car.service.dao.ManufacturerDao;
import rental.car.service.lib.Inject;
import rental.car.service.lib.Service;
import rental.car.service.model.Manufacturer;
import rental.car.service.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return manufacturerDao.get(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return manufacturerDao.delete(id);
    }
}
