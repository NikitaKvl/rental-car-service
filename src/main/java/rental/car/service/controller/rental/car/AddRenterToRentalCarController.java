package rental.car.service.controller.rental.car;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.model.RentalCar;
import rental.car.service.model.Renter;
import rental.car.service.service.RentalCarService;
import rental.car.service.service.RenterService;

@WebServlet(urlPatterns = "/rental-cars/renters/add")
public class AddRenterToRentalCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RentalCarService rentalCarService =
            (RentalCarService) injector.getInstance(RentalCarService.class);
    private final RenterService renterService =
            (RenterService) injector.getInstance(RenterService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/rental.cars/renters/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long renterId = Long.parseLong(req.getParameter("renterId"));
        long rentalCarId = Long.parseLong(req.getParameter("rentalCarId"));
        Optional<Renter> renter = renterService.get(renterId);
        Optional<RentalCar> rentalCar = rentalCarService.get(rentalCarId);
        if (renter.isPresent() && rentalCar.isPresent()) {
            rentalCarService.addRenterToRentalCar(renter.get(), rentalCar.get());
            resp.sendRedirect(req.getContextPath() + "/rental-cars/renters/add");
        }
    }
}
