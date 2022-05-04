package by.epam.silina.command.impl;

import by.epam.silina.command.Command;
import by.epam.silina.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        //todo change "index.jsp" on smth or add constant
        return new Router("index.jsp", Router.Type.REDIRECT);
    }
}
