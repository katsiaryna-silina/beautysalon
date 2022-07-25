package by.silina.beautysalon.controller.filter;

import by.silina.beautysalon.controller.command.CommandType;
import by.silina.beautysalon.model.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.COMMAND;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.ROLE;
import static by.silina.beautysalon.controller.command.CommandType.*;
import static by.silina.beautysalon.controller.command.PagePath.INDEX;

/**
 * The ServletSecurityFilter class.
 * Limits available commands depending on the role.
 *
 * @author Silina Katsiaryna
 */
@WebFilter(urlPatterns = "/controller", servletNames = "controller")
public class ServletSecurityFilter implements Filter {
    private Set<CommandType> adminCommandSet;
    private Set<CommandType> clientCommandSet;
    private Set<CommandType> guestCommandSet;

    /**
     * Initializes the filter.
     *
     * @param config FilterConfig. The filter config.
     */
    @Override
    public void init(FilterConfig config) {
        adminCommandSet = Set.of(
                LOGOUT,
                CHANGE_PASSWORD,
                SHOW_ALL_USERS,
                GET_USERS_JSON,
                UPDATE_USER,
                CHANGE_USER_ROLE,
                CHANGE_DISCOUNT,
                CHANGE_USER_STATUS,
                PICK_SERVICE_IN_ORDER,
                PICK_DATE_IN_ORDER,
                PICK_TIME_IN_ORDER,
                SHOW_NEW_ORDER_DATA,
                CREATE_NEW_ORDER,
                SHOW_ALL_ORDERS_FOR_ADMIN,
                GET_ORDERS_FOR_ADMIN_JSON,
                UPDATE_ORDER_BY_ADMIN,
                CHANGE_ORDER_STATUS,
                SHOW_USER_ORDERS,
                GET_ORDERS_FOR_USER_JSON,
                SHOW_ALL_SERVICES_BY_ADMIN,
                GET_ALL_SERVICES_JSON,
                UPDATE_SERVICE,
                DELETE_SERVICE,
                SHOW_FEEDBACK,
                UPDATE_FEEDBACK,
                DELETE_USER,
                CHANGE_LOCALE,
                DEFAULT
        );
        clientCommandSet = Set.of(
                LOGOUT,
                CHANGE_PASSWORD,
                PICK_SERVICE_IN_ORDER,
                PICK_DATE_IN_ORDER,
                PICK_TIME_IN_ORDER,
                SHOW_NEW_ORDER_DATA,
                CREATE_NEW_ORDER,
                CHANGE_ORDER_STATUS,
                SHOW_USER_ORDERS,
                GET_ORDERS_FOR_USER_JSON,
                UPDATE_ORDER_BY_CLIENT,
                SHOW_FEEDBACK,
                UPDATE_FEEDBACK,
                DELETE_USER,
                CHANGE_LOCALE,
                DEFAULT
        );

        guestCommandSet = Set.of(
                REGISTRATION,
                LOGIN,
                CHANGE_LOCALE,
                DEFAULT
        );
    }

    /**
     * Does servlet security filter.
     *
     * @param request  ServletRequest. The servlet request.
     * @param response ServletResponse. The servlet response.
     * @param chain    FilterChain. The filter chain.
     * @throws IOException      if a IOException occurs.
     * @throws ServletException if a ServletException occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) request;
        var httpServletResponse = (HttpServletResponse) response;
        var session = httpServletRequest.getSession();

        var commandName = httpServletRequest.getParameter(COMMAND);
        if (commandName == null) {
            httpServletResponse.sendRedirect(INDEX);
            return;
        }

        var commandType = valueOf(commandName.toUpperCase());

        var roleFromSession = session.getAttribute(ROLE);
        Role userRole;
        if (roleFromSession == null) {
            userRole = Role.GUEST;
        } else {
            userRole = Role.valueOf(String.valueOf(roleFromSession));
        }

        boolean isAllowed =
                switch (userRole) {
                    case GUEST -> guestCommandSet.stream().anyMatch(el -> el == commandType);
                    case CLIENT -> clientCommandSet.stream().anyMatch(el -> el == commandType);
                    case ADMIN -> adminCommandSet.stream().anyMatch(el -> el == commandType);
                };

        if (isAllowed) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
