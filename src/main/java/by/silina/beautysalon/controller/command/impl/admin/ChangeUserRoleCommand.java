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

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_RESULT;

public class ChangeUserRoleCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        DiscountStatusService discountStatusService = DiscountStatusServiceImpl.getInstance();

        Long userId = Long.valueOf(sessionRequestContent.getParameterByName(USER_ID));
        sessionRequestContent.putRequestAttribute(USER_ID, userId);

        String username = sessionRequestContent.getParameterByName(USERNAME);
        sessionRequestContent.putRequestAttribute(USERNAME, username);

        Role currentRole = Role.valueOf(sessionRequestContent.getParameterByName(CURRENT_ROLE_NAME));
        Role newRole = Role.valueOf(sessionRequestContent.getParameterByName(NEW_ROLE_NAME));

        if (newRole.equals(currentRole)) {
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "Picked role is the same as current.");
        } else if (Role.CLIENT.equals(currentRole) && Role.ADMIN.equals(newRole)) {
            try {
                var maximumDiscountOptional = discountStatusService.findMaximum();
                if (maximumDiscountOptional.isPresent()) {
                    var discountStatusName = maximumDiscountOptional.get().getStatus();
                    if (userService.changeUserRoleAndDiscount(userId, Role.ADMIN, discountStatusName)) {
                        sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "User's role has changed on \"admin\"");
                    }
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "Cannot change user's role. Contact technical support.");
        }
        return new Router(UPDATE_USER_RESULT, Router.Type.FORWARD);
    }
}
