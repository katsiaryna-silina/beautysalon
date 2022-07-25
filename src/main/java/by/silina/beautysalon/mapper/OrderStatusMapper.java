package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The OrderStatusMapperImpl class responsible for mapping OrderStatus.
 *
 * @author Silina Katsiaryna
 */
public interface OrderStatusMapper {
    
    /**
     * Maps passed ResultSet to OrderStatus entity.
     *
     * @param resultSet ResultSet
     * @return OrderStatus
     * @throws SQLException if a sql exception occurs.
     */
    OrderStatus toEntity(ResultSet resultSet) throws SQLException;
}
