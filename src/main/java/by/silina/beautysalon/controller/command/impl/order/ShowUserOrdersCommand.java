package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;

import static by.silina.beautysalon.controller.command.PagePath.ALL_ORDERS_FOR_USER;

/**
 * The ShowClientOrdersCommand class for showing all client's orders command.
 *
 * @author Silina Katsiaryna
 */
public class ShowUserOrdersCommand implements Command {

    /**
     * Executes show client's order command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        return new Router(ALL_ORDERS_FOR_USER, Router.Type.FORWARD);
    }
}
