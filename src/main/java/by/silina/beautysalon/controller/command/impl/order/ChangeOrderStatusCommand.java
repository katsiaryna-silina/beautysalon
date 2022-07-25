package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.ORDER_STATUS_FAILED_CHANGE;
import static by.silina.beautysalon.controller.command.PagePath.ORDER_STATUS_SUCCESS_CHANGE;

/**
 * The ChangeOrderStatusCommand class for change order status command.
 *
 * @author Silina Katsiaryna
 */
public class ChangeOrderStatusCommand implements Command {

    /**
     * Executes change order status command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();

        var orderId = Long.valueOf(sessionRequestContent.getParameterByName(ORDER_ID));
        sessionRequestContent.putRequestAttribute(ORDER_ID, orderId);

        String newOrderStatusName = sessionRequestContent.getParameterByName(NEW_ORDER_STATUS_NAME);

        var page = ORDER_STATUS_SUCCESS_CHANGE;
        try {
            if (orderService.changeStatus(orderId, newOrderStatusName)) {
                String currentOrderStatus = sessionRequestContent.getParameterByName(CURRENT_ORDER_STATUS_NAME);
                sessionRequestContent.putRequestAttribute(CURRENT_ORDER_STATUS_NAME, currentOrderStatus);
                sessionRequestContent.putRequestAttribute(NEW_ORDER_STATUS_NAME, newOrderStatusName);
            } else {
                page = ORDER_STATUS_FAILED_CHANGE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }
}
