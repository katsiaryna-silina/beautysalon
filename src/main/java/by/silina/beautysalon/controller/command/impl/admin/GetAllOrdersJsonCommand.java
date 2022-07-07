package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderListForAdminJsonDto;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;
import by.silina.beautysalon.util.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.LIMIT;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.OFFSET;

public class GetAllOrdersJsonCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();

        JsonElement orderListJsonElement = null;
        try {
            long numberOfOrders = orderService.findNumberOfOrders();
            if (numberOfOrders != 0L) {
                Integer numberOfOrdersPerPage = Integer.valueOf(sessionRequestContent.getParameterByName(LIMIT));
                Long fromOrderId = Long.valueOf(sessionRequestContent.getParameterByName(OFFSET));
                List<OrderForAdminDto> pagedOrderDtoList = orderService.findPagedOrderForAdminDtoList(fromOrderId, numberOfOrdersPerPage);

                OrderListForAdminJsonDto orderListJsonDto = OrderListForAdminJsonDto.builder()
                        .recordsTotal(numberOfOrders)
                        .recordsFiltered(numberOfOrders)
                        .rows(pagedOrderDtoList)
                        .build();
                Gson gson = GsonLocalDateTimeAdapter.createGson();
                orderListJsonElement = gson.toJsonTree(orderListJsonDto);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(orderListJsonElement, Router.Type.JSON);
    }
}
