package rental.car.service.controller.rental.car;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.model.RentalCar;
import rental.car.service.service.RentalCarService;

@WebServlet(urlPatterns = "/rental-cars")
public class GetAllRentalCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RentalCarService rentalCarService =
            (RentalCarService) injector.getInstance(RentalCarService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<RentalCar> rentalCars = rentalCarService.getAll();
        req.setAttribute("rentalCars", rentalCars);
        req.getRequestDispatcher("/WEB-INF/views/rental.cars/all.jsp").forward(req, resp);
    }
}
