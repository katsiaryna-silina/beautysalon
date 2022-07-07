package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.OrderFeedback;

import java.util.Optional;

public interface OrderFeedbackDao {
    Optional<OrderFeedback> findById(Long id) throws DaoException;
}
