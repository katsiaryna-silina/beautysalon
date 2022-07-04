package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_RESULT;

public class ChangeDiscountCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

        Long userId = Long.valueOf(sessionRequestContent.getParameterByName(USER_ID));
        sessionRequestContent.putRequestAttribute(USER_ID, userId);

        String username = sessionRequestContent.getParameterByName(USERNAME);
        sessionRequestContent.putRequestAttribute(USERNAME, username);

        String currentDiscountStatusName = sessionRequestContent.getParameterByName(CURRENT_DISCOUNT_STATUS_NAME);
        String newDiscountStatusName = sessionRequestContent.getParameterByName(NEW_DISCOUNT_STATUS_NAME);
        DiscountStatus newDiscountStatus = DiscountStatus.valueOf(newDiscountStatusName);

        if (currentDiscountStatusName != null && currentDiscountStatusName.equals(newDiscountStatusName)) {
            sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "Picked discount status is the same as current.");
        } else {
            try {
                if (userService.changeDiscountById(userId, newDiscountStatus)) {
                    sessionRequestContent.putRequestAttribute(CHANGE_USER_MESSAGE, "User's discount status has changed on \"" + newDiscountStatusName + newDiscountStatus.getDiscount() + "%\"");
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
