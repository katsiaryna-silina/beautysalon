package by.silina.beautysalon.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The DisableCacheFilter class is responsible for disabling cache and forbidding page back with old data.
 *
 * @author Silina Katsiaryna
 */
@WebFilter(filterName = "DisableCacheFilter", urlPatterns = "/*")
public class DisableCacheFilter implements Filter {

    /**
     * Does disable cache filter.
     *
     * @param request  ServletRequest. The servlet request.
     * @param response ServletResponse. The servlet response.
     * @param chain    FilterChain. The filter chain.
     * @throws IOException      if a IOException occurs.
     * @throws ServletException if a ServletException occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpServletResponse.setDateHeader("Expires", 0); // Proxies.
        chain.doFilter(request, httpServletResponse);
    }
}
