package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderStatusDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.service.OrderStatusService;

import java.util.List;
import java.util.Optional;

public class OrderStatusServiceImpl implements OrderStatusService {
    private static final OrderStatusServiceImpl instance = new OrderStatusServiceImpl();
    private final OrderStatusDaoImpl orderStatusDao = OrderStatusDaoImpl.getInstance();

    private OrderStatusServiceImpl() {
    }

    public static OrderStatusServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<OrderStatus> findAll() throws ServiceException {
        try {
            return orderStatusDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<OrderStatus> findByName(String statusName) throws ServiceException {
        try {
            return orderStatusDao.findByName(statusName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
