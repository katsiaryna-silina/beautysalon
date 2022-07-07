package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.OrderStatus;

import java.util.Optional;

public interface OrderStatusDao {
    Optional<OrderStatus> findByName(String statusName) throws DaoException;
}
