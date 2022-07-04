package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.dto.OrderFormDto;

public interface OrderDao {
    boolean insertOrder(OrderFormDto orderFormDto) throws DaoException;
}
