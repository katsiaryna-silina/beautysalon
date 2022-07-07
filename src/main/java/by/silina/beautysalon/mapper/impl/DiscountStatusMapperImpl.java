package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.mapper.DiscountStatusMapper;
import by.silina.beautysalon.model.entity.DiscountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.dao.TableColumnName.*;

public class DiscountStatusMapperImpl implements DiscountStatusMapper {
    private static final DiscountStatusMapperImpl instance = new DiscountStatusMapperImpl();

    private DiscountStatusMapperImpl() {
    }

    public static DiscountStatusMapperImpl getInstance() {
        return instance;
    }

    @Override
    public DiscountStatus toEntity(ResultSet resultSet) throws SQLException {
        return DiscountStatus.builder()
                .id(resultSet.getLong(ID))
                .status(resultSet.getString(STATUS))
                .discount(resultSet.getBigDecimal(DISCOUNT))
                .build();
    }
}
