package by.epam.silina.controller.command.impl;

import by.epam.silina.controller.command.Command;
import by.epam.silina.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        //todo change "index.jsp" on smth or add constant
        return new Router("index.jsp", Router.Type.FORWARD);
    }
}
