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

import java.util.Optional;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.*;

public class LoginCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        UserLoginDto userLoginDto = userMapper.toUserLoginDto(sessionRequestContent);

        var page = LOGIN;
        try {
            Optional<User> optionalUser = userService.findUser(userLoginDto);
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

    private String fillSessionAttributesFrom(User user, SessionRequestContent sessionRequestContent) {
        var role = user.getRole();
        switch (role) {
            case ADMIN -> {
                //todo difference
                sessionRequestContent.putSessionAttribute(ROLE, role);
                sessionRequestContent.putSessionAttribute(USER_ID, user.getId());
                sessionRequestContent.putSessionAttribute(EMAIL, user.getEmail());
                sessionRequestContent.putSessionAttribute(DISCOUNT, user.getDiscountStatus().getDiscount());
                sessionRequestContent.putSessionAttribute(DISCOUNT_STATUS, user.getDiscountStatus().getStatus());
                sessionRequestContent.putSessionAttribute(FIRST_NAME, user.getFirstName());
                sessionRequestContent.putSessionAttribute(LAST_NAME, user.getLastName());
                sessionRequestContent.putSessionAttribute(PHONE_NUMBER, user.getPhoneNumber());
                return MAIN_ADMIN;
            }
            case CLIENT -> {
                sessionRequestContent.putSessionAttribute(ROLE, role);
                sessionRequestContent.putSessionAttribute(USER_ID, user.getId());
                sessionRequestContent.putSessionAttribute(EMAIL, user.getEmail());
                sessionRequestContent.putSessionAttribute(DISCOUNT, user.getDiscountStatus().getDiscount());
                sessionRequestContent.putSessionAttribute(DISCOUNT_STATUS, user.getDiscountStatus().getStatus());
                sessionRequestContent.putSessionAttribute(FIRST_NAME, user.getFirstName());
                sessionRequestContent.putSessionAttribute(LAST_NAME, user.getLastName());
                sessionRequestContent.putSessionAttribute(PHONE_NUMBER, user.getPhoneNumber());
                return MAIN_CLIENT;
            }
        }
        return LOGIN;
    }
}
