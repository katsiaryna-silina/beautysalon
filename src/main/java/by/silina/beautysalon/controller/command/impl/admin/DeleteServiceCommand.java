package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.service.ServService;
import by.silina.beautysalon.service.impl.ServServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.ID;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.IS_DEPRECATED;
import static by.silina.beautysalon.controller.command.PagePath.ALL_SERVICES_FOR_ADMIN;

public class DeleteServiceCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        ServService servService = ServServiceImpl.getInstance();

        Long serviceId = Long.valueOf(sessionRequestContent.getParameterByName(ID));
        boolean isDeprecated = Boolean.parseBoolean(sessionRequestContent.getParameterByName(IS_DEPRECATED));
        try {
            if (!isDeprecated) {
                servService.deleteById(serviceId);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(ALL_SERVICES_FOR_ADMIN, Router.Type.FORWARD);
    }
}
