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
import service.validation.ValidationExecutor;
import service.validation.impl.PasswordValidator;
import service.validation.impl.UsernameValidator;

import java.io.File;
import java.util.Map;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        File userFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("users.json")).getFile());
        File productFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("products.json")).getFile());

        userDAO = new UserDAO(objectMapper, userFile);
        productDAO = new ProductDAO(objectMapper, productFile);

        UserService userService = new UserService(userDAO, encoder);
        ProductService productService = new ProductService(productDAO);
        ValidationExecutor validationExecutor = new ValidationExecutor(Map.ofEntries(
                Map.entry("username", new UsernameValidator(userService)),
                Map.entry("password", new PasswordValidator())
        ));



        UsernameValidator usernameValidator = new UsernameValidator(userService);
        PasswordValidator passwordValidator = new PasswordValidator();

        servletContext.setAttribute("encoder", encoder);
        servletContext.setAttribute("productService", productService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("usernameValidator", usernameValidator);
        servletContext.setAttribute("passwordValidator", passwordValidator);
        servletContext.setAttribute("validationExecutor", validationExecutor);
    }

}