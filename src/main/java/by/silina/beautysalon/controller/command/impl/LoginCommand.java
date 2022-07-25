package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.*;
import static by.silina.beautysalon.model.entity.Role.ADMIN;
import static by.silina.beautysalon.model.entity.Role.CLIENT;

/**
 * The LoginCommand class for login command.
 *
 * @author Silina Katsiaryna
 */
public class LoginCommand implements Command {

    /**
     * Executes login command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        UserLoginDto userLoginDto = userMapper.toUserLoginDto(sessionRequestContent);

        var page = LOGIN;
        try {
            var optionalUser = userService.findUser(userLoginDto);
            if (optionalUser.isPresent()) {
                var user = optionalUser.get();
                sessionRequestContent.putSessionAttribute(USERNAME, user.getUsername());
                page = fillSessionAttributesFrom(user, sessionRequestContent);
            } else {
                sessionRequestContent.putRequestAttribute(LOGIN_FAILED_MESSAGE, "Incorrect username or password");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }

    /**
     * Fills session attributes.
     *
     * @param user                  User. Session attributes are filled from this user.
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return String. Jsp page.
     */
    private String fillSessionAttributesFrom(User user, SessionRequestContent sessionRequestContent) {
        sessionRequestContent.putSessionAttribute(USER_ID, user.getId());
        sessionRequestContent.putSessionAttribute(EMAIL, user.getEmail());
        sessionRequestContent.putSessionAttribute(DISCOUNT_STATUS, user.getDiscountStatus());
        sessionRequestContent.putSessionAttribute(FIRST_NAME, user.getFirstName());
        sessionRequestContent.putSessionAttribute(LAST_NAME, user.getLastName());
        sessionRequestContent.putSessionAttribute(PHONE_NUMBER, user.getPhoneNumber());

        var role = user.getRole();
        sessionRequestContent.putSessionAttribute(ROLE, role);

        if (CLIENT.equals(role)) {
            return MAIN_CLIENT;
        } else if (ADMIN.equals(role)) {
            return MAIN_ADMIN;
        } else {
            return LOGIN;
        }
    }
}
