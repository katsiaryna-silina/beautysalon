package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;

import static by.silina.beautysalon.controller.command.PagePath.ALL_ORDERS_FOR_CLIENT;

public class ShowClientOrdersCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        return new Router(ALL_ORDERS_FOR_CLIENT, Router.Type.FORWARD);
    }
}
