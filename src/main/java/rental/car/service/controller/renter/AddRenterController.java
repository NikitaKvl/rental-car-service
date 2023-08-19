package rental.car.service.controller.renter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rental.car.service.lib.Injector;
import rental.car.service.model.Renter;
import rental.car.service.service.RenterService;

@WebServlet(urlPatterns = "/renters/add")
public class AddRenterController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("rental.car.service");
    private final RenterService renterService =
            (RenterService) injector.getInstance(RenterService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/renters/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String driverLicenseNumber = req.getParameter("driverLicenseNumber");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Renter renter = new Renter();
        renter.setName(name);
        renter.setDriverLicenseNumber(driverLicenseNumber);
        renter.setLogin(login);
        renter.setPassword(password);
        renterService.create(renter);
        resp.sendRedirect(req.getContextPath() + "/renters/add");
    }
}
