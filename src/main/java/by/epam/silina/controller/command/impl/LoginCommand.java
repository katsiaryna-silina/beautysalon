package by.epam.silina.controller.command.impl;

import by.epam.silina.controller.command.Command;
import by.epam.silina.controller.command.Router;
import by.epam.silina.entity.User;
import by.epam.silina.exception.CommandException;
import by.epam.silina.exception.ServiceException;
import by.epam.silina.service.UserService;
import by.epam.silina.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.silina.controller.command.AttributeAndParameterName.*;
import static by.epam.silina.controller.command.PagePath.LOGIN;
import static by.epam.silina.controller.command.PagePath.MAIN;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();

        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        try {
            Optional<User> optionalUser = userService.findUserByUsernameAndPassword(username, password);
            String page = LOGIN;
            if (optionalUser.isPresent()) {
                session.setAttribute(USERNAME, username);
                page = MAIN;
            } else {
                request.setAttribute(LOGIN_FAILED_MESSAGE, "Incorrect login or password");
            }
            return new Router(page, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
