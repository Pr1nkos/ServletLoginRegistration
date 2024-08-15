package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Auth filter.
 */
@WebFilter(AuthFilter.SECURE_URL_PATTERN)
public class AuthFilter implements Filter {

    public static final String SECURE_URL_PATTERN = "/secure/*";
    private String loginPageUrl;
    private String userIdSessionAttribute;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Properties props = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")){
            if(inputStream != null){
                props.load(inputStream);
                loginPageUrl = props.getProperty("loginPageUrl");
                userIdSessionAttribute = props.getProperty("userIdSessionAttribute");
            }
        }catch(IOException e){
            throw new ServletException("Ошибка чтения файла конфига", e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session.getAttribute(userIdSessionAttribute) != null) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginPageUrl);
        }
    }
}
