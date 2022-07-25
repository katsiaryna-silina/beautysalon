package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * The OrderDao interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderDao {

    /**
     * Finds an order by order id.
     *
     * @param orderId Long. The order id.
     * @return Optional of Order
     * @throws DaoException if a dao exception occurs.
     */
    Optional<Order> findById(Long orderId) throws DaoException;

    /**
     * Finds number of all orders.
     *
     * @return long. Number of orders.
     * @throws DaoException if a dao exception occurs.
     */
    long findNumberOfOrders() throws DaoException;

    /**
     * Finds number of user's orders by user id.
     *
     * @param userId Long. The user id.
     * @return long. Number of orders.
     * @throws DaoException if a dao exception occurs.
     */
    long findNumberOfOrders(Long userId) throws DaoException;

    /**
     * Finds paged orders.
     *
     * @param fromOrderId    Long. The first order to find.
     * @param numberOfOrders Integer. Number of orders.
     * @return List of Order
     * @throws DaoException if a dao exception occurs.
     */
    List<Order> findPagedOrders(Long fromOrderId, Integer numberOfOrders) throws DaoException;

    /**
     * Finds paged orders of user.
     *
     * @param fromOrderId    Long. The first order to find.
     * @param numberOfOrders Integer. Number of orders.
     * @param userId         Long. Number of orders.
     * @return List of Order
     * @throws DaoException if a dao exception occurs.
     */
    List<Order> findPagedOrders(Long fromOrderId, Integer numberOfOrders, Long userId) throws DaoException;

    /**
     * Changes status of the order.
     *
     * @param orderId    Long. The id of order.
     * @param statusName String. Change to this status name.
     * @return boolean. True if this status is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean changeStatus(Long orderId, String statusName) throws DaoException;
}
