package by.silina.beautysalon.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private final Map<String, Object> requestAttributes = new HashMap<>();
    private final Map<String, String[]> requestParameters = new HashMap<>();
    private final Map<String, Object> sessionAttributes = new HashMap<>();
    private boolean isSessionInvalid;

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

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void invalidateSession() {
        isSessionInvalid = true;
    }

    // added data for jsp
    public void insertAttributes(HttpServletRequest request) {
        requestAttributes.forEach(request::setAttribute);

        HttpSession session = request.getSession(false);
        if (session != null) {
            sessionAttributes.forEach(session::setAttribute);
        }
        if (isSessionInvalid) {
            request.getSession().invalidate();
        }
    }

    public String getParameterByName(String parameterName) {
        String[] parameters = requestParameters.get(parameterName);
        return parameters.length == 0 ? null : parameters[0];
    }

    public Object getRequestAttributeByName(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public Object putRequestAttribute(String attributeName, Object object) {
        return requestAttributes.put(attributeName, object);
    }

    public Object getSessionAttributeByName(String sessionAttributeName) {
        return sessionAttributes.get(sessionAttributeName);
    }

    public Object putSessionAttribute(String sessionAttributeName, Object object) {
        return sessionAttributes.put(sessionAttributeName, object);
    }
}
