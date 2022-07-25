package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.OrderStatus;

import java.util.Optional;

/**
 * The DiscountStatusDao interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderStatusDao {

    /**
     * Finds order status by its name.
     *
     * @param statusName String. The name of status.
     * @return Optional of OrderStatus
     * @throws DaoException if a dao exception occurs.
     */
    Optional<OrderStatus> findByName(String statusName) throws DaoException;

    /**
     * Finds default order status when order has just created.
     *
     * @return Optional of OrderStatus
     * @throws DaoException if a dao exception occurs.
     */
    Optional<OrderStatus> findDefault() throws DaoException;
}
