package rental.car.service.controller.renter;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.model.Renter;
import rental.car.service.service.RenterService;

@WebServlet(urlPatterns = "/renters")
public class GetAllRentersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RenterService renterService = (RenterService) injector
            .getInstance(RenterService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Renter> renters = renterService.getAll();
        req.setAttribute("renters", renters);
        req.getRequestDispatcher("/WEB-INF/views/renters/all.jsp").forward(req, resp);
    }
}
