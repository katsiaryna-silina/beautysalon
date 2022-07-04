package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.DisplayingOrderDto;
import by.silina.beautysalon.model.dto.OrderFormDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<DisplayingOrderDto> findPagedOrderDtoList(Long fromOrderId, Integer numberOfOrders) throws ServiceException;

    List<LocalDate> findFreeDatesToVisit();

    boolean addOrder(OrderFormDto orderFormDto) throws ServiceException;
}
