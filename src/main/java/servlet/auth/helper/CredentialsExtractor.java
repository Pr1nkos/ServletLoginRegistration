package servlet.auth.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import servlet.auth.helper.dto.Credential;

public class CredentialsExtractor {
    public static Credential extractCredentials(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        return Credential.builder()
                .username(username)
                .password(password)
                .build();
    }
}
