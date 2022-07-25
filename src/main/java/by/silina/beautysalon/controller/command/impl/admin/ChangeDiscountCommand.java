package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_RESULT;

/**
 * The ChangeDiscountCommand class for change user's discount command by admin.
 *
 * @author Silina Katsiaryna
 */
public class ChangeDiscountCommand implements Command {

    /**
     * Executes change discount command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

        var userId = Long.valueOf(sessionRequestContent.getParameterByName(USER_ID));
        sessionRequestContent.putRequestAttribute(USER_ID, userId);

        String username = sessionRequestContent.getParameterByName(USERNAME);
        sessionRequestContent.putRequestAttribute(USERNAME, username);

        String currentDiscountStatusName = sessionRequestContent.getParameterByName(CURRENT_DISCOUNT_STATUS_NAME);
        String newDiscountStatusName = sessionRequestContent.getParameterByName(NEW_DISCOUNT_STATUS_NAME);

        if (currentDiscountStatusName != null && currentDiscountStatusName.equals(newDiscountStatusName)) {
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "Picked discount status is the same as current.");
        } else {
            try {
                if (userService.changeDiscount(userId, newDiscountStatusName)) {
                    sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "User's discount status has changed on \"" + newDiscountStatusName + "%\"");
                } else {
                    sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "Cannot change user's discount status.");
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return new Router(UPDATE_USER_RESULT, Router.Type.FORWARD);
    }
}
