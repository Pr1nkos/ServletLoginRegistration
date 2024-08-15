package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebFilter("/language")
public class LanguageFilter implements Filter {
    String language;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Properties prop = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")){
            if(inputStream != null){
                prop.load(inputStream);
                language = prop.getProperty("language");
            }
        }catch (IOException e){
            throw new ServletException("Не удалось загрузить файл конфигурации",e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String language = req.getParameter(this.language);

        if (language != null && (language.equals("en") || language.equals("ru"))) {
            req.getSession().setAttribute(this.language, req.getParameter(this.language));
        } else {
            req.getSession().setAttribute(this.language, "en");
        }
        chain.doFilter(request, response);

    }
}
