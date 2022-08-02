package by.silina.beautysalon.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * The SessionRequestContent class for session attributes, request parameters and attributes.
 *
 * @author Silina Katsiaryna
 */
public class SessionRequestContent {
    private static final String LESS_THAN_SIGN = "<";
    private static final String GREATER_THAN_SIGN = ">";
    private static final String REPLACEMENT = "";
    private final Map<String, Object> requestAttributes = new HashMap<>();
    private final Map<String, String[]> requestParameters = new HashMap<>();
    private final Map<String, Object> sessionAttributes = new HashMap<>();
    private boolean isSessionInvalid;

    /**
     * Initializes a new session request content.
     *
     * @param request the request
     */
    public SessionRequestContent(HttpServletRequest request) {
        request.getAttributeNames()
                .asIterator()
                .forEachRemaining(requestAttributeName ->
                        requestAttributes.put(requestAttributeName, request.getAttribute(requestAttributeName))
                );

        requestParameters.putAll(request.getParameterMap());

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.getAttributeNames()
                    .asIterator()
                    .forEachRemaining(sessionAttributeName ->
                            sessionAttributes.put(sessionAttributeName, session.getAttribute(sessionAttributeName)));
        }
    }

    /**
     * Invalidates the session.
     */
    public void invalidateSession() {
        isSessionInvalid = true;
    }


    /**
     * Inserts request attributes.
     *
     * @param request the request
     */
    public void insertRequestAttributes(HttpServletRequest request) {
        requestAttributes.forEach(request::setAttribute);

        var session = request.getSession(false);
        if (session != null) {
            sessionAttributes.forEach(session::setAttribute);
        }
        if (isSessionInvalid) {
            request.getSession().invalidate();
        }
    }

    /**
     * Gets request parameter by name.
     *
     * @param parameterName String. The name of request parameter.
     * @return String
     */
    public String getParameterByName(String parameterName) {
        String[] parameters = requestParameters.get(parameterName);
        return (parameters == null || parameters.length == 0) ? null : getSafeString(parameters[0]);
    }

    /**
     * Gets request parameters by name.
     *
     * @param parameterName String. The name of request parameters.
     * @return String[]
     */
    public String[] getParametersByName(String parameterName) {
        return requestParameters.get(parameterName);
    }

    /**
     * Puts request attribute.
     *
     * @param attributeName String. The name of request attribute.
     * @param object        Object. The parameter.
     * @return Object
     */
    public Object putRequestAttribute(String attributeName, Object object) {
        return requestAttributes.put(attributeName, object);
    }

    /**
     * Gets session attribute by name.
     *
     * @param sessionAttributeName String. The name of session attribute.
     * @return Object
     */
    public Object getSessionAttributeByName(String sessionAttributeName) {
        return sessionAttributes.get(sessionAttributeName);
    }

    /**
     * Puts session attribute.
     *
     * @param sessionAttributeName String. The name of session attribute.
     * @param object               Object. The attribute.
     * @return Object
     */
    public Object putSessionAttribute(String sessionAttributeName, Object object) {
        return sessionAttributes.put(sessionAttributeName, object);
    }

    /**
     * Gets safe String in order to avoiding xss attacks.
     *
     * @param string String
     * @return String
     */
    private String getSafeString(String string) {
        return string.strip()
                .replace(LESS_THAN_SIGN, REPLACEMENT)
                .replace(GREATER_THAN_SIGN, REPLACEMENT);
    }
}
