package by.epam.silina.controller.command.impl;

import by.epam.silina.controller.SessionRequestContent;
import by.epam.silina.controller.command.Command;
import by.epam.silina.controller.command.Router;
import by.epam.silina.exception.CommandException;
import by.epam.silina.exception.ServiceException;
import by.epam.silina.mapper.UserMapper;
import by.epam.silina.mapper.impl.UserMapperImpl;
import by.epam.silina.service.UserService;
import by.epam.silina.service.impl.UserServiceImpl;

import java.util.Map;

import static by.epam.silina.controller.command.AttributeAndParameterName.*;
import static by.epam.silina.controller.command.PagePath.REGISTRATION;
import static by.epam.silina.controller.command.PagePath.WELCOME;

public class RegistrationCommand implements Command {
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        var userRegistrationDto = userMapper.toDto(sessionRequestContent);

        var page = REGISTRATION;

        try {
            var errorMap = userService.addUser(userRegistrationDto);
            if (errorMap.isEmpty()) {
                sessionRequestContent.putSessionAttribute(USERNAME, userRegistrationDto.getFirstName());
                page = WELCOME;
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
