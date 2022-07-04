package by.silina.beautysalon.controller.command.impl.order;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.mapper.impl.OrderMapperImpl;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.service.OrderService;
import by.silina.beautysalon.service.impl.OrderServiceImpl;

import static by.silina.beautysalon.controller.command.PagePath.SUCCESS_NEW_ORDER;

public class CreateOrderCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        OrderMapper orderMapper = OrderMapperImpl.getInstance();

        OrderFormDto orderFormDto = orderMapper.toOrderFormDto(sessionRequestContent);
        try {
            orderService.addOrder(orderFormDto);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(SUCCESS_NEW_ORDER, Router.Type.FORWARD);
    }
}