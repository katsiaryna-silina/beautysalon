package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.mapper.impl.OrderMapperImpl;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.entity.Order;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.OrderStatusService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;
import by.silina.beautysalon.service.impl.OrderStatusServiceImpl;

import java.util.Optional;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.*;

public class UpdateOrderStatusByClientCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        OrderStatusService orderStatusService = OrderStatusServiceImpl.getInstance();
        OrderMapper orderMapper = OrderMapperImpl.getInstance();

        Long orderId = Long.valueOf(sessionRequestContent.getParameterByName(ID));
        String orderStatus = sessionRequestContent.getParameterByName(STATUS);

        var page = UPDATE_ORDER_FORM_BY_USER;
        try {
            Optional<Order> orderOptional = orderService.findById(orderId);
            if (orderOptional.isPresent()) {

                var orderStatusNames = orderStatusService.findAll()
                        .stream()
                        .filter(el -> el.getAccessibleTo().equals(Role.CLIENT))
                        .map(OrderStatus::getStatus)
                        .filter(status -> !status.equals(orderStatus))
                        .toList();
                if (orderStatusNames.isEmpty()) {
                    return new Router(IMPOSSIBLE_TO_UPDATE_ORDER, Router.Type.FORWARD);
                }

                OrderForAdminDto orderDto = orderMapper.toOrderForAdminDto(orderOptional.get());
                sessionRequestContent.putRequestAttribute(ORDER, orderDto);
                sessionRequestContent.putRequestAttribute(ORDER_STATUSES, orderStatusNames);
            } else {
                page = ALL_ORDERS_FOR_USER;
                sessionRequestContent.putRequestAttribute(UPDATE_ORDER_ERROR_MESSAGE, "Cannot update order with id=" + orderId);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }
}
