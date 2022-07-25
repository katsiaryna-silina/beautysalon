package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import liquibase.util.StringUtil;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.ORDER_FORM_ORDER_DATA;

/**
 * The ShowNewOrderDataCommand class for showing new order data command.
 *
 * @author Silina Katsiaryna
 */
public class ShowNewOrderDataCommand implements Command {
    public static final String REGEX_SPLITTER = "_";

    /**
     * Executes show new order data command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String servicesIds = sessionRequestContent.getParameterByName(SERVICES_IDS);
        sessionRequestContent.putSessionAttribute(SERVICES_IDS, servicesIds);

        String dateString = sessionRequestContent.getParameterByName(DATE);
        sessionRequestContent.putRequestAttribute(DATE, dateString);

        processOrderServicesFrom(sessionRequestContent);

        String fullPrice = sessionRequestContent.getParameterByName(FULL_PRICE);
        sessionRequestContent.putRequestAttribute(FULL_PRICE, fullPrice);
        String priceWithDiscount = sessionRequestContent.getParameterByName(PRICE_WITH_DISCOUNT);
        sessionRequestContent.putRequestAttribute(PRICE_WITH_DISCOUNT, priceWithDiscount);

        String visitTimeDataString = sessionRequestContent.getParameterByName(VISIT_TIME);
        String[] visitTimeData = visitTimeDataString.split(REGEX_SPLITTER);
        sessionRequestContent.putRequestAttribute(VISIT_TIME_BEGIN, visitTimeData[0]);
        sessionRequestContent.putRequestAttribute(VISIT_TIME_END, visitTimeData[1]);
        sessionRequestContent.putRequestAttribute(VISIT_TIME_IDS, visitTimeData[2]);

        String neededTime = sessionRequestContent.getParameterByName(NEEDED_TIME);
        sessionRequestContent.putRequestAttribute(NEEDED_TIME, neededTime);

        return new Router(ORDER_FORM_ORDER_DATA, Router.Type.FORWARD);
    }

    /**
     * Processes order services from session and request content.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     */
    private void processOrderServicesFrom(SessionRequestContent sessionRequestContent) {
        String complexServiceName = sessionRequestContent.getParameterByName(COMPLEX_SERVICE_NAME);
        if (!StringUtil.isEmpty(complexServiceName)) {
            sessionRequestContent.putRequestAttribute(COMPLEX_SERVICE_NAME, complexServiceName);
        } else {
            String[] notComplexServiceNames = sessionRequestContent.getParametersByName(NOT_COMPLEX_SERVICE_NAMES);
            sessionRequestContent.putRequestAttribute(NOT_COMPLEX_SERVICE_NAMES, notComplexServiceNames);
        }
    }
}
