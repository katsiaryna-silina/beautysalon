package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.DELETE_USER_RESULT_MESSAGE;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.USER_ID;
import static by.silina.beautysalon.controller.command.PagePath.DELETE_USER_RESULT;

/**
 * The DeleteUserCommand class for delete user command.
 *
 * @author Silina Katsiaryna
 */
public class DeleteUserCommand implements Command {

    /**
     * Executes delete user command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();

        var userId = (Long) sessionRequestContent.getSessionAttributeByName(USER_ID);

        try {
            long numberOfOrders = orderService.findNumberOfOrders(userId);
            if (numberOfOrders == 0 && userService.deleteUser(userId)) {
                sessionRequestContent.putRequestAttribute(DELETE_USER_RESULT_MESSAGE,
                        "User was deleted.");
                sessionRequestContent.invalidateSession();
            } else {
                sessionRequestContent.putRequestAttribute(DELETE_USER_RESULT_MESSAGE,
                        "Cannot delete user cause this user has orders or just cannot be deleted.");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(DELETE_USER_RESULT, Router.Type.FORWARD);
    }
}
