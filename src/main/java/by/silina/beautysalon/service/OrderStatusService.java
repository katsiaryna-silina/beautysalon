package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {
    List<OrderStatus> findAll() throws ServiceException;

    Optional<OrderStatus> findByName(String statusName) throws ServiceException;
}
