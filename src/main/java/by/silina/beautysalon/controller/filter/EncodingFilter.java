package by.silina.beautysalon.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter implements Filter {
    private static final String ENCODING = "encoding";
    private String fileEncoding;

    @Override
    public void init(FilterConfig filterConfig) {
        fileEncoding = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var requestEncoding = request.getCharacterEncoding();
        if (fileEncoding != null && !fileEncoding.equalsIgnoreCase(requestEncoding)) {
            request.setCharacterEncoding(fileEncoding);
            response.setCharacterEncoding(fileEncoding);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        fileEncoding = null;
    }
}
