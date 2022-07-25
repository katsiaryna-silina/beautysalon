package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.service.DiscountStatusService;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.DiscountStatusServiceImpl;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.ALL_USERS;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_FORM;

/**
 * The UpdateUserCommand class for update user command by admin.
 *
 * @author Silina Katsiaryna
 */
public class UpdateUserCommand implements Command {

    /**
     * Executes update user command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        DiscountStatusService discountStatusService = DiscountStatusServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        String username = sessionRequestContent.getParameterByName(USERNAME);

        var page = UPDATE_USER_FORM;
        try {
            var optionalUser = userService.findUser(username);
            if (optionalUser.isPresent()) {
                var discountStatusNames = discountStatusService.findAll()
                        .stream()
                        .map(DiscountStatus::getStatus)
                        .toList();

                var userDto = userMapper.toUserAuthorizedDto(optionalUser.get());
                sessionRequestContent.putRequestAttribute(USER, userDto);
                sessionRequestContent.putRequestAttribute(ROLES, Role.values());
                sessionRequestContent.putRequestAttribute(USER_STATUSES, UserStatus.values());
                sessionRequestContent.putRequestAttribute(DISCOUNT_STATUSES, discountStatusNames);
            } else {
                page = ALL_USERS;
                sessionRequestContent.putRequestAttribute(UPDATE_USER_ERROR_MESSAGE, "Cannot find user with username=" + username);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }
}
