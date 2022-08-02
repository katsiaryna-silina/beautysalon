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

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.USER_ID;
import static by.silina.beautysalon.controller.command.PagePath.DELETE_USER_FAILED;
import static by.silina.beautysalon.controller.command.PagePath.DELETE_USER_SUCCESS;

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

        String page;
        try {
            long numberOfOrders = orderService.findNumberOfOrders(userId);
            if (numberOfOrders == 0 && userService.deleteUser(userId)) {
                page = DELETE_USER_SUCCESS;
                sessionRequestContent.invalidateSession();
            } else {
                page = DELETE_USER_FAILED;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.REDIRECT);
    }
}
