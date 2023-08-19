package rental.car.service.controller.rental.car;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.service.RentalCarService;

@WebServlet(urlPatterns = "/rental-cars/delete")
public class DeleteRentalCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RentalCarService rentalCarService =
            (RentalCarService) injector.getInstance(RentalCarService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        rentalCarService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/rental-cars");
    }
}
