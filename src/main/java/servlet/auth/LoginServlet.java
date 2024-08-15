package servlet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import servlet.auth.helper.CredentialsExtractor;
import servlet.auth.helper.dto.Credential;

import java.io.IOException;
import java.util.Optional;

/**
 * The type Login servlet.
 */
@WebServlet(urlPatterns = "/login",
        initParams = {
                @WebInitParam(name="templateName", value = "/login.jsp")
        })
public class LoginServlet extends BaseAuthServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Optional<User> existedUser = userService.getExistedUser(CredentialsExtractor.extractCredentials(req));

        if (existedUser.isPresent()) {
            session.setAttribute("userID", existedUser.get().getId());
            resp.sendRedirect("/secure/products");
        }
        else {
            resp.sendRedirect("/login");
        }
    }


}