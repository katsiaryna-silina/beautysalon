package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.dto.UserLoginDto;
import by.silina.beautysalon.entity.User;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.controller.command.AttributeAndParameterName;
import by.silina.beautysalon.controller.command.PagePath;

import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

        String username = sessionRequestContent.getParameterByName(AttributeAndParameterName.USERNAME);
        String password = sessionRequestContent.getParameterByName(AttributeAndParameterName.PASSWORD);
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .username(username)
                .password(password)
                .build();

        String page = PagePath.LOGIN;
        try {
            Optional<User> optionalUser = userService.findUser(userLoginDto);
            if (optionalUser.isPresent()) {
                sessionRequestContent.putSessionAttribute(AttributeAndParameterName.USERNAME, username);
                page = fillSessionAttributesFrom(optionalUser.get(), sessionRequestContent);
            } else {
                sessionRequestContent.putRequestAttribute(AttributeAndParameterName.LOGIN_FAILED_MESSAGE, "Incorrect username or password");
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
                return PagePath.MAIN_ADMIN;
            }
            case CLIENT -> {
                sessionRequestContent.putSessionAttribute("email", user.getEmail());
                sessionRequestContent.putSessionAttribute("discount", user.getDiscountStatus().getDiscount());
                sessionRequestContent.putSessionAttribute("discount_status", user.getDiscountStatus().getStatus());
                sessionRequestContent.putSessionAttribute("first_name", user.getFirstName());
                sessionRequestContent.putSessionAttribute("last_name", user.getLastName());
                sessionRequestContent.putSessionAttribute("phone_number", user.getPhoneNumber());
                return PagePath.MAIN_CLIENT;
            }
        }
        return PagePath.LOGIN;
    }
}
