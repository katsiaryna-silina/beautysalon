package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderStatusMapper {
    OrderStatus toEntity(ResultSet resultSet) throws SQLException;
}
