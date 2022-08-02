package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Serv;
import by.silina.beautysalon.service.ServService;
import by.silina.beautysalon.service.impl.ServServiceImpl;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.ORDER_FORM_PICK_DATE;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * The PickDateInOrderCommand class for pick date in order command.
 *
 * @author Silina Katsiaryna
 */
public class PickDateInOrderCommand implements Command {
    public static final int DECIMAL_PLACES = 2;

    /**
     * Executes pick date in order command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        BigDecimal fullPrice = getFullPriceAndProcessOrderServices(sessionRequestContent);
        sessionRequestContent.putRequestAttribute(FULL_PRICE, fullPrice);

        BigDecimal priceWithDiscount = getPriceWithDiscount(fullPrice, sessionRequestContent);
        sessionRequestContent.putRequestAttribute(PRICE_WITH_DISCOUNT, priceWithDiscount);

        var today = LocalDate.now();
        LocalDate nextMonthLastDay = today.plusMonths(1).with(lastDayOfMonth());
        List<LocalDate> dates = today.datesUntil(nextMonthLastDay.plusDays(1)).toList();
        sessionRequestContent.putRequestAttribute(DATES, dates);
        return new Router(ORDER_FORM_PICK_DATE, Router.Type.FORWARD);
    }

    /**
     * Calculates full price, gets order services from request and puts them to the request.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return BigDecimal. Full price of the order.
     * @throws CommandException if a command exception occurs.
     */
    private BigDecimal getFullPriceAndProcessOrderServices(SessionRequestContent sessionRequestContent) throws CommandException {
        ServService servService = ServServiceImpl.getInstance();

        var fullPrice = BigDecimal.ZERO;
        List<Long> servicesIds = new ArrayList<>();
        String complexServiceName = sessionRequestContent.getParameterByName(COMPLEX_SERVICE_NAME);
        if (!StringUtils.isEmpty(complexServiceName)) {
            sessionRequestContent.putRequestAttribute(COMPLEX_SERVICE_NAME, complexServiceName);

            try {
                var serviceOptional = servService.findServiceByName(complexServiceName);
                if (serviceOptional.isPresent()) {
                    Serv service = serviceOptional.get();
                    fullPrice = service.getPrice();
                    servicesIds.add(service.getId());
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            List<String> notComplexServiceNames = List.of(sessionRequestContent.getParametersByName(NOT_COMPLEX_SERVICE_NAMES));
            sessionRequestContent.putRequestAttribute(NOT_COMPLEX_SERVICE_NAMES, notComplexServiceNames);

            for (String serviceName : notComplexServiceNames) {
                try {
                    var serviceOptional = servService.findServiceByName(serviceName);
                    if (serviceOptional.isPresent()) {
                        Serv service = serviceOptional.get();
                        BigDecimal servicePrice = service.getPrice();
                        fullPrice = fullPrice.add(servicePrice);
                        servicesIds.add(service.getId());
                    }
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            }
        }
        sessionRequestContent.putSessionAttribute(SERVICES_IDS, servicesIds);
        return fullPrice;
    }

    /**
     * Calculates price with discount from request.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return BigDecimal. Price with discount of the order.
     */
    private BigDecimal getPriceWithDiscount(BigDecimal fullPrice, SessionRequestContent sessionRequestContent) {
        var discountStatus = (DiscountStatus) sessionRequestContent.getSessionAttributeByName(DISCOUNT_STATUS);
        return fullPrice.multiply(BigDecimal.valueOf(100).subtract(discountStatus.getDiscount()))
                .divide(BigDecimal.valueOf(100))
                .setScale(DECIMAL_PLACES, RoundingMode.DOWN);
    }
}
