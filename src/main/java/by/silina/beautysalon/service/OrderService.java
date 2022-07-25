package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * The OrderService interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderService {

    /**
     * Finds order by its id.
     *
     * @param orderId Long
     * @return Optional of Order
     * @throws ServiceException if a service exception occurs.
     */
    Optional<Order> findById(Long orderId) throws ServiceException;

    /**
     * Finds number of all orders.
     *
     * @return long. Number of orders
     * @throws ServiceException if a service exception occurs.
     */
    long findNumberOfOrders() throws ServiceException;

    /**
     * Finds user's orders by user's id.
     *
     * @param userId Long
     * @return long. Number of orders
     * @throws ServiceException if a service exception occurs.
     */
    long findNumberOfOrders(Long userId) throws ServiceException;

    /**
     * Finds paged orders for admin. All orders can be shown.
     *
     * @param fromOrderId    Long. The first order to find.
     * @param numberOfOrders Integer. Number of orders.
     * @return List of OrderForAdminDto
     * @throws ServiceException if a service exception occurs.
     */
    List<OrderForAdminDto> findPagedOrderForAdminDtoList(Long fromOrderId, Integer numberOfOrders) throws ServiceException;

    /**
     * Finds paged orders for client. Only user's orders can be shown.
     *
     * @param fromOrderId    Long. The first order to find.
     * @param numberOfOrders Integer. Number of orders.
     * @return List of OrderForAdminDto
     * @throws ServiceException if a service exception occurs.
     */
    List<OrderForClientDto> findPagedOrderForClientDtoList(Long fromOrderId, Integer numberOfOrders, Long userId) throws ServiceException;

    /**
     * Adds an order using data from dto.
     *
     * @param orderFormDto OrderFormDto
     * @return boolean. True if the order is added; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean addOrder(OrderFormDto orderFormDto) throws ServiceException;

    /**
     * Changes order status by its id.
     *
     * @param orderId    Long. Order id.
     * @param statusName String. New status name.
     * @return boolean. True if the order status is changed; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean changeStatus(Long orderId, String statusName) throws ServiceException;
}
