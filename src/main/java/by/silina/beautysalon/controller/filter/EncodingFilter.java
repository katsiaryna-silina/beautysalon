package by.silina.beautysalon.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

/**
 * The DisableCacheFilter class is responsible for encode characters.
 *
 * @author Silina Katsiaryna
 */
@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter implements Filter {
    private static final String ENCODING = "encoding";
    private String fileEncoding;

    /**
     * Initializes the filter.
     *
     * @param config FilterConfig. The filter config.
     */
    @Override
    public void init(FilterConfig config) {
        fileEncoding = config.getInitParameter(ENCODING);
    }

    /**
     * Does encoding filter.
     *
     * @param request  ServletRequest. The servlet request.
     * @param response ServletResponse. The servlet response.
     * @param chain    FilterChain. The filter chain.
     * @throws IOException      if a IOException occurs.
     * @throws ServletException if a ServletException occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var requestEncoding = request.getCharacterEncoding();
        if (fileEncoding != null && !fileEncoding.equalsIgnoreCase(requestEncoding)) {
            request.setCharacterEncoding(fileEncoding);
            response.setCharacterEncoding(fileEncoding);
        }
        chain.doFilter(request, response);
    }

    /**
     * Destroys the filter.
     */
    @Override
    public void destroy() {
        fileEncoding = null;
    }
}
