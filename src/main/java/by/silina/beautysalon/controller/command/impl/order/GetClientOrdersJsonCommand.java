package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderListForClientJsonDto;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;
import by.silina.beautysalon.util.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;

public class GetClientOrdersJsonCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();

        Long userId = (Long) sessionRequestContent.getSessionAttributeByName(USER_ID);

        JsonElement orderListJsonElement = null;
        try {
            long numberOfOrders = orderService.findNumberOfOrders(userId);
            if (numberOfOrders != 0L) {
                Integer numberOfOrdersPerPage = Integer.valueOf(sessionRequestContent.getParameterByName(LIMIT));
                Long fromOrderId = Long.valueOf(sessionRequestContent.getParameterByName(OFFSET));
                List<OrderForClientDto> pagedOrderDtoList = orderService.findPagedOrderForClientDtoList(fromOrderId, numberOfOrdersPerPage, userId);

                OrderListForClientJsonDto orderListJsonDto = OrderListForClientJsonDto.builder()
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
