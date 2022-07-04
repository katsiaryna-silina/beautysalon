package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.DisplayingOrderDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.service.OrderService;

import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final OrderServiceImpl instance = new OrderServiceImpl();
    private final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<DisplayingOrderDto> findPagedOrderDtoList(Long fromOrderId, Integer numberOfOrders) throws ServiceException {
        return null;
    }

    @Override
    public List<LocalDate> findFreeDatesToVisit() {
        return null;
    }

    @Override
    public boolean addOrder(OrderFormDto orderFormDto) throws ServiceException {
        try {
            return orderDao.insertOrder(orderFormDto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
