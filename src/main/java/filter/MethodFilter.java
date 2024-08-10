package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;
import java.util.List;

/**
 * The type Method filter.
 */
@WebFilter("/secure/products")
public class MethodFilter implements Filter {

    private final List<String> httpMethods = List.of("PATCH", "PUT", "DELETE");


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getParameter("_method");

        if (method != null && httpMethods.contains(method.toUpperCase())) {
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req) {
                @Override
                public String getMethod() {
                    return method.toUpperCase();
                }
            };
            chain.doFilter(requestWrapper, response);
        }
        else {
            chain.doFilter(request, response);
        }
    }
}