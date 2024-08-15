package servlet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import servlet.auth.helper.CredentialsExtractor;
import servlet.auth.helper.dto.Credential;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * The type Registration servlet.
 */
@WebServlet(urlPatterns = "/registration",
        initParams = {
                @WebInitParam(name="templateName", value = "/registration.jsp")
        })
public class RegistrationServlet extends BaseAuthServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Credential credential = new Credential(username, password);


        userService.saveUser(credential);


        resp.sendRedirect("/login");
    }
}