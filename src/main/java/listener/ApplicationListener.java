package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductDAO;
import dao.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.ProductService;
import service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * The type Application listener.
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    private UserDAO userDAO;
    private ProductDAO productDAO;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ObjectMapper objectMapper = new ObjectMapper();

        File userFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("users.json")).getFile());
        File productFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("products.json")).getFile());

        userDAO = new UserDAO(objectMapper, userFile);
        productDAO = new ProductDAO(objectMapper, productFile);

        UserService userService = new UserService(userDAO);
        ProductService productService = new ProductService(productDAO);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        servletContext.setAttribute("encoder", encoder);
        servletContext.setAttribute("productService", productService);
        servletContext.setAttribute("userService", userService);
    }

}