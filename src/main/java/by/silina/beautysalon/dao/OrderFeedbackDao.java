package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.OrderFeedback;

import java.util.Optional;

/**
 * The OrderFeedbackDao interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderFeedbackDao {

    /**
     * Finds an order feedback by feedback id.
     *
     * @param id Long. The feedback id.
     * @return Optional of OrderFeedback
     * @throws DaoException if a dao exception occurs.
     */
    Optional<OrderFeedback> findById(Long id) throws DaoException;
}
