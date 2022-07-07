package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long orderId) throws ServiceException;

    long findNumberOfOrders() throws ServiceException;

    long findNumberOfOrders(Long userId) throws ServiceException;

    List<OrderForAdminDto> findPagedOrderForAdminDtoList(Long fromOrderId, Integer numberOfOrders) throws ServiceException;

    List<OrderForClientDto> findPagedOrderForClientDtoList(Long fromOrderId, Integer numberOfOrders, Long userId) throws ServiceException;

    boolean addOrder(OrderFormDto orderFormDto) throws ServiceException;

    boolean changeStatus(Long orderId, String statusName) throws ServiceException;
}
