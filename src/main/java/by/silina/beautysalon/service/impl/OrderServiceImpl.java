package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.mapper.impl.OrderMapperImpl;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.Order;
import by.silina.beautysalon.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final OrderServiceImpl instance = new OrderServiceImpl();
    private final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private final OrderMapper orderMapper = OrderMapperImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Order> findById(Long orderId) throws ServiceException {
        Optional<Order> orderOptional;
        try {
            orderOptional = orderDao.findById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderOptional;
    }

    @Override
    public long findNumberOfOrders() throws ServiceException {
        try {
            return orderDao.findNumberOfOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long findNumberOfOrders(Long userId) throws ServiceException {
        try {
            return orderDao.findNumberOfOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderForAdminDto> findPagedOrderForAdminDtoList(Long fromOrderId, Integer numberOfOrders) throws ServiceException {
        List<Order> pagedOrders;
        try {
            pagedOrders = orderDao.findPagedOrders(fromOrderId, numberOfOrders);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return pagedOrders.stream()
                .map(orderMapper::toOrderForAdminDto)
                .toList();
    }

    @Override
    public List<OrderForClientDto> findPagedOrderForClientDtoList(Long fromOrderId, Integer numberOfOrders, Long userId) throws ServiceException {
        List<Order> pagedOrders;
        try {
            pagedOrders = orderDao.findPagedOrders(fromOrderId, numberOfOrders, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return pagedOrders.stream()
                .map(orderMapper::toOrderForClientDto)
                .toList();
    }

    @Override
    public boolean addOrder(OrderFormDto orderFormDto) throws ServiceException {
        Order order = orderMapper.toEntity(orderFormDto);
        try {
            return orderDao.insert(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeStatus(Long orderId, String statusName) throws ServiceException {
        try {
            return orderDao.changeStatus(orderId, statusName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
