package rental.car.service.controller;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.model.Renter;
import rental.car.service.service.RenterService;

@WebServlet(urlPatterns = "/index")
public class IndexController extends HttpServlet {
    private final Injector injector = Injector.getInstance("rental.car.service");
    private final RenterService renterService =
            (RenterService) injector.getInstance(RenterService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long renterId = (Long) req.getSession().getAttribute("renterId");
        Optional<Renter> renter = renterService.get(renterId);
        renter.ifPresent(value -> req.setAttribute("userName", value.getName()));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
