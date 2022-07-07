package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.mapper.OrderStatusMapper;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.dao.TableColumnName.*;

public class OrderStatusMapperImpl implements OrderStatusMapper {
    private static final OrderStatusMapperImpl instance = new OrderStatusMapperImpl();

    private OrderStatusMapperImpl() {
    }

    public static OrderStatusMapperImpl getInstance() {
        return instance;
    }

    @Override
    public OrderStatus toEntity(ResultSet resultSet) throws SQLException {
        return OrderStatus.builder()
                .id(resultSet.getLong(ID))
                .status(resultSet.getString(STATUS))
                .description(resultSet.getString(DESCRIPTION))
                .accessibleTo(Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .build();
    }
}
