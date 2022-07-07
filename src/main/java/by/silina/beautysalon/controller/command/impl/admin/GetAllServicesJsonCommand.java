package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.dto.ServiceListJsonDto;
import by.silina.beautysalon.service.ServService;
import by.silina.beautysalon.service.impl.ServServiceImpl;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.LIMIT;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.OFFSET;

public class GetAllServicesJsonCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        ServService servService = ServServiceImpl.getInstance();

        JsonElement serviceListJsonElement = null;
        try {
            long numberOfServices = servService.findNumberOfServices();
            if (numberOfServices != 0L) {
                Integer numberOfServicesPerPage = Integer.valueOf(sessionRequestContent.getParameterByName(LIMIT));
                Long fromServiceId = Long.valueOf(sessionRequestContent.getParameterByName(OFFSET));
                List<ServiceDto> pagedServiceDtoList = servService.findPagedServiceDtoList(fromServiceId, numberOfServicesPerPage);

                ServiceListJsonDto serviceListJsonDto = ServiceListJsonDto.builder()
                        .recordsTotal(numberOfServices)
                        .recordsFiltered(numberOfServices)
                        .rows(pagedServiceDtoList)
                        .build();
                serviceListJsonElement = new GsonBuilder().create().toJsonTree(serviceListJsonDto);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(serviceListJsonElement, Router.Type.JSON);
    }
}
