package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.util.MessageLocalizer;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_DISCOUNT_SUCCESS;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_FAILED;

/**
 * The ChangeDiscountCommand class for change user's discount command by admin.
 *
 * @author Silina Katsiaryna
 */
public class ChangeDiscountCommand implements Command {
    private static final String USER_DISCOUNT_UPDATE_FAILED_CAUSE_SAME_KEY = "user.discount.update.failed.cause.same";
    private static final String USER_DISCOUNT_CANNOT_UPDATE_KEY = "user.discount.cannot.update";

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

        var sessionLocale = (String) sessionRequestContent.getSessionAttributeByName(LOCALE);

        var router = new Router(UPDATE_USER_FAILED, Router.Type.FORWARD);

        if (currentDiscountStatusName != null && currentDiscountStatusName.equals(newDiscountStatusName)) {
            var message = MessageLocalizer.getLocalizedMessage(USER_DISCOUNT_UPDATE_FAILED_CAUSE_SAME_KEY, sessionLocale);
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, message);
        } else {
            try {
                if (userService.changeDiscount(userId, newDiscountStatusName)) {
                    router = new Router(UPDATE_USER_DISCOUNT_SUCCESS, Router.Type.REDIRECT);
                } else {
                    var message = MessageLocalizer.getLocalizedMessage(USER_DISCOUNT_CANNOT_UPDATE_KEY, sessionLocale);
                    sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, message);
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return router;
    }
}
