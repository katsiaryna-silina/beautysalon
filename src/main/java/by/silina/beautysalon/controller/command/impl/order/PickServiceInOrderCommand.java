package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.Serv;
import by.silina.beautysalon.service.ServService;
import by.silina.beautysalon.service.impl.ServServiceImpl;

import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.COMPLEX_SERVICES;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.NOT_COMPLEX_SERVICES;
import static by.silina.beautysalon.controller.command.PagePath.ORDER_FORM_PICK_SERVICES;

/**
 * The PickServiceInOrderCommand class for select services in order command.
 *
 * @author Silina Katsiaryna
 */
public class PickServiceInOrderCommand implements Command {

    /**
     * Executes pick services in order command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        ServService servService = ServServiceImpl.getInstance();
        try {
            List<Serv> complexServices = servService.findComplexServices();
            sessionRequestContent.putRequestAttribute(COMPLEX_SERVICES, complexServices);

            List<Serv> notComplexServices = servService.findNotComplexServices();
            sessionRequestContent.putRequestAttribute(NOT_COMPLEX_SERVICES, notComplexServices);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(ORDER_FORM_PICK_SERVICES, Router.Type.FORWARD);
    }
}
