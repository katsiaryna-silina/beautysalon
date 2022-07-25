package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.mapper.impl.OrderMapperImpl;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.OrderStatusService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;
import by.silina.beautysalon.service.impl.OrderStatusServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.IMPOSSIBLE_TO_UPDATE_ORDER;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_ORDER_FORM_BY_ADMIN;

/**
 * The UpdateOrderStatusByAdminCommand class for update order status command by admin.
 *
 * @author Silina Katsiaryna
 */
public class UpdateOrderStatusByAdminCommand implements Command {

    /**
     * Executes update order status command by admin.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        OrderStatusService orderStatusService = OrderStatusServiceImpl.getInstance();
        OrderMapper orderMapper = OrderMapperImpl.getInstance();

        var orderId = Long.valueOf(sessionRequestContent.getParameterByName(ID));

        try {
            var orderOptional = orderService.findById(orderId);
            if (orderOptional.isPresent()) {

                String currentOrderStatusName = sessionRequestContent.getParameterByName(STATUS);

                var newOrderStatusNames = orderStatusService.findOrderStatusNamesForAdmin(currentOrderStatusName);
                if (newOrderStatusNames.isEmpty()) {
                    return new Router(IMPOSSIBLE_TO_UPDATE_ORDER, Router.Type.FORWARD);
                }

                OrderForAdminDto orderDto = orderMapper.toOrderForAdminDto(orderOptional.get());
                sessionRequestContent.putRequestAttribute(ORDER, orderDto);
                sessionRequestContent.putRequestAttribute(ORDER_STATUSES, newOrderStatusNames);
            } else {
                return new Router(IMPOSSIBLE_TO_UPDATE_ORDER, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(UPDATE_ORDER_FORM_BY_ADMIN, Router.Type.FORWARD);
    }
}
