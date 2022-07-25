package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.mapper.OrderStatusMapper;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The OrderStatusMapperImpl class responsible for mapping OrderStatus.
 *
 * @author Silina Katsiaryna
 */
public class OrderStatusMapperImpl implements OrderStatusMapper {
    private static final OrderStatusMapperImpl instance = new OrderStatusMapperImpl();

    /**
     * Initializes a new OrderStatusMapperImpl.
     */
    private OrderStatusMapperImpl() {
    }

    /**
     * Gets the single instance of OrderStatusMapperImpl.
     *
     * @return OrderStatusMapperImpl
     */
    public static OrderStatusMapperImpl getInstance() {
        return instance;
    }

    /**
     * Maps passed ResultSet to OrderStatus entity.
     *
     * @param resultSet ResultSet
     * @return OrderStatus
     * @throws SQLException if a sql exception occurs.
     */
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
