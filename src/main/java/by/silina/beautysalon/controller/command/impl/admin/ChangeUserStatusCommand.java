package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.util.MessageLocalizer;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_FAILED;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_STATUS_SUCCESS;

/**
 * The ChangeUserStatusCommand class for change user's status command by admin.
 *
 * @author Silina Katsiaryna
 */
public class ChangeUserStatusCommand implements Command {
    private static final String USER_STATUS_UPDATE_FAILED_CAUSE_SAME_KEY = "user.status.update.failed.cause.same";
    private static final String USER_STATUS_UPDATE_FAILED_KEY = "user.status.update.failed";

    /**
     * Executes change user's status command.
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

        String currentUserStatusName = sessionRequestContent.getParameterByName(CURRENT_USER_STATUS_NAME);
        String newUserStatusName = sessionRequestContent.getParameterByName(NEW_USER_STATUS_NAME);
        var newUserStatus = UserStatus.valueOf(newUserStatusName);

        var sessionLocale = (String) sessionRequestContent.getSessionAttributeByName(LOCALE);

        var router = new Router(UPDATE_USER_FAILED, Router.Type.FORWARD);

        if (currentUserStatusName != null && currentUserStatusName.equals(newUserStatusName)) {
            var message = MessageLocalizer.getLocalizedMessage(USER_STATUS_UPDATE_FAILED_CAUSE_SAME_KEY, sessionLocale);
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, message);
        } else {
            try {
                if (userService.changeUserStatus(userId, newUserStatus)) {
                    router = new Router(UPDATE_USER_STATUS_SUCCESS, Router.Type.REDIRECT);
                } else {
                    var message = MessageLocalizer.getLocalizedMessage(USER_STATUS_UPDATE_FAILED_KEY, sessionLocale);
                    sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, message);
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return router;
    }
}
