package filter;

import filter.dto.ErrorMessages;
import filter.dto.ValidationResult;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import service.validation.ValidationExecutor;
import servlet.auth.helper.CredentialsExtractor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter("/registration")
public class RegistrationParameterFilter implements Filter {

    private ValidationExecutor validationExecutor;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        validationExecutor = (ValidationExecutor) filterConfig.getServletContext().getAttribute("validationExecutor");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getMethod().equals("POST")) {
            CredentialsExtractor.extractCredentials(httpRequest);

            Map<String, String> map = Map.ofEntries(
                    Map.entry("username", httpRequest.getParameter("name")),
                    Map.entry("password", httpRequest.getParameter("password")));
            List<ErrorMessages> validationResults = validationExecutor.execute(map);

            if (validationResults.isEmpty()) {
                chain.doFilter(request, response);
            }
            else {
                for (ErrorMessages validationResult : validationResults) {
                    request.setAttribute(validationResult.getAttributeName(), validationResult.getErrors());
                }
            }

            httpRequest.getRequestDispatcher("/registration").forward(request, response);
        }
        else {
            chain.doFilter(request, response);
        }
    }
}
