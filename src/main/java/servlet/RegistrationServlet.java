package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.UserService;

import java.io.IOException;
import java.util.UUID;

/**
 * The type Registration servlet.
 */
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        bCryptPasswordEncoder = (BCryptPasswordEncoder) servletContext.getAttribute("encoder");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = bCryptPasswordEncoder.encode(req.getParameter("password"));

        userService.saveUser(
                User.builder()
                        .id(UUID.randomUUID())
                        .name(name)
                        .password(password)
                        .build()
        );

        resp.sendRedirect("/login");
    }
}