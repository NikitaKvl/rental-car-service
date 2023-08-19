package rental.car.service.controller.rental.car;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.model.Manufacturer;
import rental.car.service.model.RentalCar;
import rental.car.service.service.ManufacturerService;
import rental.car.service.service.RentalCarService;

@WebServlet(urlPatterns = "/rental-cars/add")
public class AddRentalCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RentalCarService rentalCarService =
            (RentalCarService) injector.getInstance(RentalCarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/rental.cars/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String model = req.getParameter("model");
        long manufacturerId = Long.parseLong(req.getParameter("manufacturerId"));
        Optional<Manufacturer> manufacturer = manufacturerService.get(manufacturerId);
        if (manufacturer.isPresent()) {
            RentalCar rentalCar = new RentalCar();
            rentalCar.setModel(model);
            rentalCar.setManufacturer(manufacturer.get());
            rentalCarService.create(rentalCar);
            resp.sendRedirect(req.getContextPath() + "/rental-cars/add");
        }
    }
}
