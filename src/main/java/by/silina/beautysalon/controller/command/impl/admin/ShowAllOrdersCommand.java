package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;

import static by.silina.beautysalon.controller.command.PagePath.ALL_ORDERS_FOR_ADMIN;

public class ShowAllOrdersCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        return new Router(ALL_ORDERS_FOR_ADMIN, Router.Type.FORWARD);
    }
}
