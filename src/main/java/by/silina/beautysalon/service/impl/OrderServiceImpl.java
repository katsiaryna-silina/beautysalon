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

/**
 * The OrderServiceImpl class that responsible for processing Order.
 *
 * @author Silina Katsiaryna
 */
public class OrderServiceImpl implements OrderService {
    private static final OrderServiceImpl instance = new OrderServiceImpl(OrderDaoImpl.getInstance());
    private final OrderMapper orderMapper = OrderMapperImpl.getInstance();
    private final OrderDaoImpl orderDao;

    /**
     * Initializes a new OrderServiceImpl.
     */
    private OrderServiceImpl(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Gets the single instance of OrderServiceImpl.
     *
     * @return OrderServiceImpl
     */
    public static OrderServiceImpl getInstance() {
        return instance;
    }

    /**
     * Finds order by its id.
     *
     * @param orderId Long
     * @return Optional of Order
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public Optional<Order> findById(Long orderId) throws ServiceException {
        try {
            return orderDao.findById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds number of all orders.
     *
     * @return long. Number of orders
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public long findNumberOfOrders() throws ServiceException {
        try {
            return orderDao.findNumberOfOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds user's orders by user's id.
     *
     * @param userId Long
     * @return long. Number of orders
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public long findNumberOfOrders(Long userId) throws ServiceException {
        try {
            return orderDao.findNumberOfOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds paged orders for admin. All orders can be shown.
     *
     * @param fromOrderId    Long. The first order to find.
     * @param numberOfOrders Integer. Number of orders.
     * @return List of OrderForAdminDto
     * @throws ServiceException if a service exception occurs.
     */
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

    /**
     * Finds paged orders for client. Only user's orders can be shown.
     *
     * @param fromOrderId    Long. The first order to find.
     * @param numberOfOrders Integer. Number of orders.
     * @return List of OrderForAdminDto
     * @throws ServiceException if a service exception occurs.
     */
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

    /**
     * Adds an order using data from dto.
     *
     * @param orderFormDto OrderFormDto
     * @return boolean. True if the order is added; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public boolean addOrder(OrderFormDto orderFormDto) throws ServiceException {
        Order order = orderMapper.toEntity(orderFormDto);
        try {
            return orderDao.insert(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Changes order status by its id.
     *
     * @param orderId    Long. Order id.
     * @param statusName String. New status name.
     * @return boolean. True if the order status is changed; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public boolean changeStatus(Long orderId, String statusName) throws ServiceException {
        try {
            return orderDao.changeStatus(orderId, statusName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
