package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.service.DiscountStatusService;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.DiscountStatusServiceImpl;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.util.MessageLocalizer;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_FAILED;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_ROLE_SUCCESS;
import static by.silina.beautysalon.model.entity.Role.ADMIN;
import static by.silina.beautysalon.model.entity.Role.CLIENT;

/**
 * The ChangeUserRoleCommand class for change user's role command by admin.
 *
 * @author Silina Katsiaryna
 */
public class ChangeUserRoleCommand implements Command {
    private static final String USER_ROLE_FAILED_CAUSE_SAME_KEY = "user.role.failed.cause.same";
    private static final String USER_ROLE_UPDATE_FAILED_KEY = "user.role.update.failed";

    /**
     * Executes change role command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        DiscountStatusService discountStatusService = DiscountStatusServiceImpl.getInstance();

        var userId = Long.valueOf(sessionRequestContent.getParameterByName(USER_ID));
        sessionRequestContent.putRequestAttribute(USER_ID, userId);

        String username = sessionRequestContent.getParameterByName(USERNAME);
        sessionRequestContent.putRequestAttribute(USERNAME, username);

        var currentRole = Role.valueOf(sessionRequestContent.getParameterByName(CURRENT_ROLE_NAME));
        var newRole = Role.valueOf(sessionRequestContent.getParameterByName(NEW_ROLE_NAME));

        var sessionLocale = (String) sessionRequestContent.getSessionAttributeByName(LOCALE);

        var router = new Router(UPDATE_USER_FAILED, Router.Type.FORWARD);

        if (newRole.equals(currentRole)) {
            var message = MessageLocalizer.getLocalizedMessage(USER_ROLE_FAILED_CAUSE_SAME_KEY, sessionLocale);
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, message);
        } else if (CLIENT.equals(currentRole) && ADMIN.equals(newRole)) {
            try {
                var maximumDiscountOptional = discountStatusService.findMaximum();
                if (maximumDiscountOptional.isPresent()) {
                    var discountStatusName = maximumDiscountOptional.get().getStatus();
                    if (userService.changeUserRoleAndDiscount(userId, ADMIN, discountStatusName)) {
                        router = new Router(UPDATE_USER_ROLE_SUCCESS, Router.Type.REDIRECT);
                    }
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            var message = MessageLocalizer.getLocalizedMessage(USER_ROLE_UPDATE_FAILED_KEY, sessionLocale);
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, message);
        }
        return router;
    }
}
