package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.controller.command.AttributeAndParameterName;
import by.silina.beautysalon.controller.command.PagePath;

import java.util.Map;

public class RegistrationCommand implements Command {
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        var userRegistrationDto = userMapper.toDto(sessionRequestContent);

        var page = PagePath.REGISTRATION;

        try {
            var errorMap = userService.addUser(userRegistrationDto);
            if (errorMap.isEmpty()) {
                sessionRequestContent.putSessionAttribute(AttributeAndParameterName.USERNAME, userRegistrationDto.getFirstName());
                page = PagePath.WELCOME;
            } else {
                fillRequestAttributesFrom(errorMap, sessionRequestContent);
                sessionRequestContent.putRequestAttribute(AttributeAndParameterName.USERNAME, userRegistrationDto.getFirstName());
                sessionRequestContent.putRequestAttribute(AttributeAndParameterName.EMAIL, userRegistrationDto.getEmail());
                sessionRequestContent.putRequestAttribute(AttributeAndParameterName.FIRST_NAME, userRegistrationDto.getFirstName());
                sessionRequestContent.putRequestAttribute(AttributeAndParameterName.LAST_NAME, userRegistrationDto.getLastName());
                sessionRequestContent.putRequestAttribute(AttributeAndParameterName.PHONE_NUMBER, userRegistrationDto.getPhoneNumber());
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
