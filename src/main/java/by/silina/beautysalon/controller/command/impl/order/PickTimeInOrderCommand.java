package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.VisitTimeSlotDto;
import by.silina.beautysalon.service.ServService;
import by.silina.beautysalon.service.VisitTimeService;
import by.silina.beautysalon.service.impl.ServServiceImpl;
import by.silina.beautysalon.service.impl.VisitTimeServiceImpl;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.ORDER_FORM_NO_FREE_TIME;
import static by.silina.beautysalon.controller.command.PagePath.ORDER_FORM_PICK_TIME;

/**
 * The PickTimeInOrderCommand class for select visit time in order command.
 *
 * @author Silina Katsiaryna
 */
public class PickTimeInOrderCommand implements Command {

    /**
     * Executes pick time in order command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String servicesIds = sessionRequestContent.getParameterByName(SERVICES_IDS);
        sessionRequestContent.putSessionAttribute(SERVICES_IDS, servicesIds);

        Integer minutesNeeded = countMinutesNeededForOrder(sessionRequestContent);
        sessionRequestContent.putRequestAttribute(NEEDED_TIME, minutesNeeded);

        String dateString = sessionRequestContent.getParameterByName(DATE);
        sessionRequestContent.putRequestAttribute(DATE, dateString);
        var date = LocalDate.parse(dateString);

        var page = ORDER_FORM_PICK_TIME;
        try {
            VisitTimeService visitTimeService = VisitTimeServiceImpl.getInstance();
            List<VisitTimeSlotDto> timeSlotDtoList = visitTimeService.getVisitTimeSlotDtos(date, minutesNeeded);
            if (timeSlotDtoList.isEmpty()) {
                sessionRequestContent.putRequestAttribute(NO_VISIT_TIMES_MESSAGE,
                        "There are no free time this day. Go back and pick another one.");
                page = ORDER_FORM_NO_FREE_TIME;
            } else {
                sessionRequestContent.putRequestAttribute(VISIT_TIMES, timeSlotDtoList);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        String fullPrice = sessionRequestContent.getParameterByName(FULL_PRICE);
        sessionRequestContent.putRequestAttribute(FULL_PRICE, fullPrice);
        String priceWithDiscount = sessionRequestContent.getParameterByName(PRICE_WITH_DISCOUNT);
        sessionRequestContent.putRequestAttribute(PRICE_WITH_DISCOUNT, priceWithDiscount);

        return new Router(page, Router.Type.FORWARD);
    }

    /**
     * Counts minutes needed for user's order.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Integer. Number of minutes.
     * @throws CommandException if a command exception occurs.
     */
    private Integer countMinutesNeededForOrder(SessionRequestContent sessionRequestContent) throws CommandException {
        ServService servService = ServServiceImpl.getInstance();
        Integer minutesNeeded = 0;
        String complexServiceName = sessionRequestContent.getParameterByName(COMPLEX_SERVICE_NAME);
        if (!StringUtils.isEmpty(complexServiceName)) {
            try {
                var optionalService = servService.findServiceByName(complexServiceName);
                if (optionalService.isPresent()) {
                    minutesNeeded = optionalService.get().getMinutesNeeded();
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            sessionRequestContent.putRequestAttribute(COMPLEX_SERVICE_NAME, complexServiceName);
        } else {
            List<String> notComplexServiceNames = List.of(sessionRequestContent.getParametersByName(NOT_COMPLEX_SERVICE_NAMES));
            for (String serviceName : notComplexServiceNames) {
                try {
                    var optionalService = servService.findServiceByName(serviceName);
                    if (optionalService.isPresent()) {
                        minutesNeeded += optionalService.get().getMinutesNeeded();
                    }
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            }
            sessionRequestContent.putRequestAttribute(NOT_COMPLEX_SERVICE_NAMES, notComplexServiceNames);
        }
        return minutesNeeded;
    }
}
