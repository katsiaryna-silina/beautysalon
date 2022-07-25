package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

/**
 * The OrderStatusService interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderStatusService {

    /**
     * Finds all order statuses.
     *
     * @return List of OrderStatus
     * @throws ServiceException if a service exception occurs.
     */
    List<OrderStatus> findAll() throws ServiceException;

    /**
     * Finds order status by its name.
     *
     * @param statusName String. Status name.
     * @return Optional of OrderStatus
     * @throws ServiceException if a service exception occurs.
     */
    Optional<OrderStatus> findByName(String statusName) throws ServiceException;

    /**
     * Finds default order status when order has just created.
     *
     * @return Optional of OrderStatus
     * @throws ServiceException if a service exception occurs.
     */
    Optional<OrderStatus> findDefault() throws ServiceException;

    /**
     * Finds order status names available for update the order by client.
     *
     * @param orderStatusCurrentName String. Order current status name.
     * @return List of String. List of order status names to update the order.
     */
    List<String> findOrderStatusNamesForClient(String orderStatusCurrentName) throws ServiceException;


    /**
     * Finds order status names available for update the order by admin.
     *
     * @param orderStatusCurrentName String. Order current status name.
     * @return List of String. List of order status names to update the order.
     */
    List<String> findOrderStatusNamesForAdmin(String orderStatusCurrentName) throws ServiceException;
}
