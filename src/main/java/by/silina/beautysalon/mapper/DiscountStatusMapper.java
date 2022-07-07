package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.entity.DiscountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DiscountStatusMapper {
    DiscountStatus toEntity(ResultSet resultSet) throws SQLException;
}
