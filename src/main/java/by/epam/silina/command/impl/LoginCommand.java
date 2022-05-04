package by.epam.silina.command.impl;

import by.epam.silina.command.Command;
import by.epam.silina.command.Router;
import by.epam.silina.exception.CommandException;
import by.epam.silina.exception.ServiceException;
import by.epam.silina.service.UserService;
import by.epam.silina.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Router router;

        try {
            //todo add constants
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (userService.authenticate(login, password)) {
                request.setAttribute("user", login); //todo remove and change jsp
                session.setAttribute("user_name", login);
                router = new Router("pages/main.jsp", Router.Type.FORWARD);
            } else {
                request.setAttribute("login_message", "Incorrect login or password.");
                router = new Router("index.jsp", Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
