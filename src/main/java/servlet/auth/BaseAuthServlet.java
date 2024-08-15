package servlet.auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.UserService;

import java.io.IOException;

public abstract class BaseAuthServlet extends HttpServlet {

    protected UserService userService;
    protected BCryptPasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        passwordEncoder = (BCryptPasswordEncoder) servletContext.getAttribute("encoder");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(getInitParameter("templateName")).forward(req, resp);
    }
}
