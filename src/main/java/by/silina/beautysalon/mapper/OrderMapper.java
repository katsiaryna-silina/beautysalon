package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderMapper {
    OrderForClientDto toOrderForClientDto(Order order);

    OrderForAdminDto toOrderForAdminDto(Order order);

    Order toEntity(OrderFormDto orderFormDto);

    Order toEntity(ResultSet resultSet) throws SQLException;

    OrderFormDto toOrderFormDto(SessionRequestContent sessionRequestContent);
}
