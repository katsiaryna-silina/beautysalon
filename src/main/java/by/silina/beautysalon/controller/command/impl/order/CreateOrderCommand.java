package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.mapper.impl.OrderMapperImpl;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;

import static by.silina.beautysalon.controller.command.PagePath.FAILED_NEW_ORDER;
import static by.silina.beautysalon.controller.command.PagePath.SUCCESS_NEW_ORDER;

/**
 * The CreateOrderCommand class for create order command.
 *
 * @author Silina Katsiaryna
 */
public class CreateOrderCommand implements Command {

    /**
     * Executes change order status command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        OrderMapper orderMapper = OrderMapperImpl.getInstance();

        var page = SUCCESS_NEW_ORDER;
        var orderFormDto = orderMapper.toOrderFormDto(sessionRequestContent);
        try {
            if (!orderService.addOrder(orderFormDto)) {
                page = FAILED_NEW_ORDER;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }
}