package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> findById(Long orderId) throws DaoException;

    long findNumberOfOrders() throws DaoException;

    long findNumberOfOrders(Long userId) throws DaoException;

    List<Order> findPagedOrders(Long fromOrderId, Integer numberOfOrders) throws DaoException;

    List<Order> findPagedOrders(Long fromOrderId, Integer numberOfOrders, Long userId) throws DaoException;

    boolean changeStatus(Long orderId, String statusName) throws DaoException;
}
