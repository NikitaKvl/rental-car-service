package rental.car.service.controller.renter;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.service.RenterService;

@WebServlet(urlPatterns = "/renters/delete")
public class DeleteRenterController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RenterService renterService =
            (RenterService) injector.getInstance(RenterService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        renterService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/renters");
    }
}
