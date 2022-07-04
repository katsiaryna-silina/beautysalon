package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;

import java.util.Optional;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.ALL_USERS;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_USER_FORM;

public class UpdateUserCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        String username = sessionRequestContent.getParameterByName(USERNAME);

        var page = UPDATE_USER_FORM;
        try {
            Optional<User> optionalUser = userService.findUser(username);
            if (optionalUser.isPresent()) {
                UserAuthorizedDto userDto = userMapper.toUserAuthorizedDto(optionalUser.get());
                sessionRequestContent.putRequestAttribute(USER, userDto);
                sessionRequestContent.putRequestAttribute(ROLES, Role.values());
                sessionRequestContent.putRequestAttribute(USER_STATUSES, UserStatus.values());
                sessionRequestContent.putRequestAttribute(DISCOUNT_STATUSES, DiscountStatus.values());
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
