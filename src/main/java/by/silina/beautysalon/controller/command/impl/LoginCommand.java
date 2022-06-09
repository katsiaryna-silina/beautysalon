package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.PagePath;
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

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.LOGIN_FAILED_MESSAGE;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.USERNAME;

public class LoginCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        UserLoginDto userLoginDto = userMapper.toUserLoginDto(sessionRequestContent);

        String page = PagePath.LOGIN;
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
        switch (user.getRole()) {
            case ADMIN -> {
                //todo constants and difference
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
