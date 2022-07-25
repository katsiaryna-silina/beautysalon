package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;

import static by.silina.beautysalon.controller.command.PagePath.ALL_SERVICES_FOR_ADMIN;

/**
 * The ShowAllOrdersCommand class for showing all services command by admin.
 *
 * @author Silina Katsiaryna
 */
public class ShowAllServicesCommand implements Command {

    /**
     * Executes show all services command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        return new Router(ALL_SERVICES_FOR_ADMIN, Router.Type.FORWARD);
    }
}
