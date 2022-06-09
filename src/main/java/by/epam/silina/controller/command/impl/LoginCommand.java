package by.epam.silina.controller.command.impl;

import by.epam.silina.controller.SessionRequestContent;
import by.epam.silina.controller.command.Command;
import by.epam.silina.controller.command.Router;
import by.epam.silina.dto.UserLoginDto;
import by.epam.silina.entity.User;
import by.epam.silina.exception.CommandException;
import by.epam.silina.exception.ServiceException;
import by.epam.silina.service.UserService;
import by.epam.silina.service.impl.UserServiceImpl;

import java.util.Optional;

import static by.epam.silina.controller.command.AttributeAndParameterName.*;
import static by.epam.silina.controller.command.PagePath.*;

public class LoginCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

        String username = sessionRequestContent.getParameterByName(USERNAME);
        String password = sessionRequestContent.getParameterByName(PASSWORD);
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .username(username)
                .password(password)
                .build();

        String page = LOGIN;
        try {
            Optional<User> optionalUser = userService.findUser(userLoginDto);
            if (optionalUser.isPresent()) {
                sessionRequestContent.putSessionAttribute(USERNAME, username);
                page = fillSessionAttributesFrom(optionalUser.get(), sessionRequestContent);
            } else {
                sessionRequestContent.putRequestAttribute(LOGIN_FAILED_MESSAGE, "Incorrect username or password");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }

    private String fillSessionAttributesFrom(User user, SessionRequestContent sessionRequestContent) {
        switch (user.getRole()) {
            case ADMIN -> {
                //todo constants
                sessionRequestContent.putSessionAttribute("email", user.getEmail());
                sessionRequestContent.putSessionAttribute("discount", user.getDiscountStatus().getDiscount());
                sessionRequestContent.putSessionAttribute("discount_status", user.getDiscountStatus().getStatus());
                sessionRequestContent.putSessionAttribute("first_name", user.getFirstName());
                sessionRequestContent.putSessionAttribute("last_name", user.getLastName());
                sessionRequestContent.putSessionAttribute("phone_number", user.getPhoneNumber());
                return MAIN_ADMIN;
            }
            case CLIENT -> {
                sessionRequestContent.putSessionAttribute("email", user.getEmail());
                sessionRequestContent.putSessionAttribute("discount", user.getDiscountStatus().getDiscount());
                sessionRequestContent.putSessionAttribute("discount_status", user.getDiscountStatus().getStatus());
                sessionRequestContent.putSessionAttribute("first_name", user.getFirstName());
                sessionRequestContent.putSessionAttribute("last_name", user.getLastName());
                sessionRequestContent.putSessionAttribute("phone_number", user.getPhoneNumber());
                return MAIN_CLIENT;
            }
        }
        return LOGIN;
    }
}
