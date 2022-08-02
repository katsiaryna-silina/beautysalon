package by.silina.beautysalon.controller.filter;

import by.silina.beautysalon.model.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.ROLE;
import static by.silina.beautysalon.controller.command.PagePath.*;

/**
 * The PageSecurityFilter class.
 * Forbids direct access to the jsp and redirect to the specified page (index.jsp) if page is not allowed.
 *
 * @author Silina Katsiaryna
 */
@WebFilter(urlPatterns = "/jsp/*")
public class PageSecurityFilter implements Filter {
    private Set<String> adminPages;
    private Set<String> clientPages;
    private Set<String> guestPages;

    /**
     * Initializes the filter.
     *
     * @param config FilterConfig. The filter config.
     */
    @Override
    public void init(FilterConfig config) {
        adminPages = Set.of(
                INDEX,
                SERVICES,
                PROFILE,
                MAIN_ADMIN,
                CHANGE_PASSWORD,
                SUCCESS_CHANGE_PASSWORD,
                ALL_USERS,
                UPDATE_USER_FORM,
                UPDATE_USER_DISCOUNT_SUCCESS,
                UPDATE_USER_STATUS_SUCCESS,
                UPDATE_USER_ROLE_SUCCESS,
                UPDATE_USER_FAILED,
                ORDER_FORM_PICK_SERVICES,
                ORDER_FORM_PICK_DATE,
                ORDER_FORM_PICK_TIME,
                ORDER_FORM_NO_FREE_TIME,
                ORDER_FORM_ORDER_DATA,
                SUCCESS_NEW_ORDER,
                FAILED_NEW_ORDER,
                ALL_ORDERS_FOR_ADMIN,
                UPDATE_ORDER_FORM_BY_ADMIN,
                ORDER_STATUS_SUCCESS_CHANGE,
                ORDER_STATUS_FAILED_CHANGE,
                ALL_ORDERS_FOR_USER,
                UPDATE_ORDER_FORM_BY_USER,
                IMPOSSIBLE_TO_UPDATE_ORDER,
                ALL_SERVICES_FOR_ADMIN,
                UPDATE_FEEDBACK,
                UPDATE_FEEDBACK_SUCCESS,
                UPDATE_FEEDBACK_FAILED,
                DELETE_USER,
                DELETE_USER_FAILED,
                DELETE_USER_SUCCESS
        );

        clientPages = Set.of(
                INDEX,
                SERVICES,
                PROFILE,
                MAIN_CLIENT,
                CHANGE_PASSWORD,
                SUCCESS_CHANGE_PASSWORD,
                ORDER_FORM_PICK_SERVICES,
                ORDER_FORM_PICK_DATE,
                ORDER_FORM_PICK_TIME,
                ORDER_FORM_NO_FREE_TIME,
                ORDER_FORM_ORDER_DATA,
                SUCCESS_NEW_ORDER,
                FAILED_NEW_ORDER,
                ORDER_STATUS_SUCCESS_CHANGE,
                ORDER_STATUS_FAILED_CHANGE,
                ALL_ORDERS_FOR_USER,
                UPDATE_ORDER_FORM_BY_USER,
                IMPOSSIBLE_TO_UPDATE_ORDER,
                UPDATE_FEEDBACK,
                UPDATE_FEEDBACK_SUCCESS,
                UPDATE_FEEDBACK_FAILED,
                DELETE_USER,
                DELETE_USER_FAILED,
                DELETE_USER_SUCCESS
        );

        guestPages = Set.of(INDEX,
                SERVICES,
                LOGIN,
                DELETE_USER_SUCCESS,
                REGISTRATION
        );
    }

    /**
     * Does page security filter.
     *
     * @param request  ServletRequest. The servlet request.
     * @param response ServletResponse. The servlet response.
     * @param chain    FilterChain. The filter chain.
     * @throws IOException      if a IOException occurs.
     * @throws ServletException if a ServletException occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        var httpServletRequest = (HttpServletRequest) request;
        var httpServletResponse = (HttpServletResponse) response;
        var session = httpServletRequest.getSession();

        var requestURI = httpServletRequest.getServletPath();

        var roleFromSession = session.getAttribute(ROLE);
        Role userRole;
        if (roleFromSession == null) {
            userRole = Role.GUEST;
        } else {
            userRole = Role.valueOf(String.valueOf(roleFromSession));
        }

        boolean isAllowed =
                switch (userRole) {
                    case GUEST -> guestPages.stream().anyMatch(requestURI::contains);
                    case CLIENT -> clientPages.stream().anyMatch(requestURI::contains);
                    case ADMIN -> adminPages.stream().anyMatch(requestURI::contains);
                };

        if (isAllowed) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
