package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.entity.DiscountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DiscountStatusMapperImpl class responsible for mapping DiscountStatus.
 *
 * @author Silina Katsiaryna
 */
public interface DiscountStatusMapper {

    /**
     * Maps passed ResultSet to DiscountStatus entity.
     *
     * @param resultSet ResultSet
     * @return DiscountStatus
     * @throws SQLException if a sql exception occurs.
     */
    DiscountStatus toEntity(ResultSet resultSet) throws SQLException;
}
