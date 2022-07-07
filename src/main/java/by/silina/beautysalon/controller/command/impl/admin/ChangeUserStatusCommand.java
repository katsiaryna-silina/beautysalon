package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_RESULT;

public class ChangeUserStatusCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

        Long userId = Long.valueOf(sessionRequestContent.getParameterByName(USER_ID));
        sessionRequestContent.putRequestAttribute(USER_ID, userId);

        String username = sessionRequestContent.getParameterByName(USERNAME);
        sessionRequestContent.putRequestAttribute(USERNAME, username);

        String currentUserStatusName = sessionRequestContent.getParameterByName(CURRENT_USER_STATUS_NAME);
        String newUserStatusName = sessionRequestContent.getParameterByName(NEW_USER_STATUS_NAME);
        UserStatus newUserStatus = UserStatus.valueOf(newUserStatusName);

        if (currentUserStatusName != null && currentUserStatusName.equals(newUserStatusName)) {
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "Picked user status is the same as current.");
        } else {
            try {
                if (userService.changeUserStatus(userId, newUserStatus)) {
                    sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "User status has changed on " + newUserStatusName);
                } else {
                    sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "Cannot change user status.");
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return new Router(UPDATE_USER_RESULT, Router.Type.FORWARD);
    }
}
