package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import java.util.Map;

import static by.silina.beautysalon.controller.command.PagePath.CHANGE_PASSWORD;
import static by.silina.beautysalon.controller.command.PagePath.SUCCESS_CHANGE_PASSWORD;

public class ChangeUserPasswordCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        UserPasswordsDto userPasswordsDto = userMapper.toUserPasswordsDto(sessionRequestContent);
        var page = CHANGE_PASSWORD;
        try {
            Map<String, String> errorMap = userService.changePassword(userPasswordsDto);
            if (errorMap.isEmpty()) {
                page = SUCCESS_CHANGE_PASSWORD;
            } else {
                fillRequestAttributesFrom(errorMap, sessionRequestContent);
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
