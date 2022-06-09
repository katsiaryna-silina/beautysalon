package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.PagePath;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import java.util.Map;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;

public class RegistrationCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        var userRegistrationDto = userMapper.toUserRegistrationDto(sessionRequestContent);

        var page = PagePath.REGISTRATION;

        try {
            var errorMap = userService.addUser(userRegistrationDto);
            if (errorMap.isEmpty()) {
                sessionRequestContent.putSessionAttribute(USERNAME, userRegistrationDto.getFirstName());
                page = PagePath.WELCOME;
            } else {
                fillRequestAttributesFrom(errorMap, sessionRequestContent);
                sessionRequestContent.putRequestAttribute(USERNAME, userRegistrationDto.getFirstName());
                sessionRequestContent.putRequestAttribute(EMAIL, userRegistrationDto.getEmail());
                sessionRequestContent.putRequestAttribute(FIRST_NAME, userRegistrationDto.getFirstName());
                sessionRequestContent.putRequestAttribute(LAST_NAME, userRegistrationDto.getLastName());
                sessionRequestContent.putRequestAttribute(PHONE_NUMBER, userRegistrationDto.getPhoneNumber());
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }

    private void fillRequestAttributesFrom(Map<String, String> errorMap, SessionRequestContent sessionRequestContent) {
        errorMap.forEach(sessionRequestContent::putRequestAttribute);
    }
}
