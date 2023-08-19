package rental.car.service.controller.renter;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rental.car.service.lib.Injector;
import rental.car.service.model.RentalCar;
import rental.car.service.service.RentalCarService;

@WebServlet(urlPatterns = "/renters/rental-cars")
public class GetMyRentalsCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RentalCarService rentalCarService =
            (RentalCarService) injector.getInstance(RentalCarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long renterId = (Long) session.getAttribute("renterId");
        List<RentalCar> rentalCars = rentalCarService.getAllByRenter(renterId);
        req.setAttribute("rentalCars", rentalCars);
        req.getRequestDispatcher("WEB-INF/views/rental-cars/all.jsp").forward(req, resp);
    }
}
