package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.mapper.DiscountStatusMapper;
import by.silina.beautysalon.model.entity.DiscountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The DiscountStatusMapperImpl class responsible for mapping DiscountStatus.
 *
 * @author Silina Katsiaryna
 */
public class DiscountStatusMapperImpl implements DiscountStatusMapper {
    private static final DiscountStatusMapperImpl instance = new DiscountStatusMapperImpl();

    /**
     * Initializes a new DiscountStatusMapperImpl.
     */
    private DiscountStatusMapperImpl() {
    }

    /**
     * Gets the single instance of DiscountStatusMapperImpl.
     *
     * @return DiscountStatusMapperImpl
     */
    public static DiscountStatusMapperImpl getInstance() {
        return instance;
    }

    /**
     * Maps passed ResultSet to DiscountStatus entity.
     *
     * @param resultSet ResultSet
     * @return DiscountStatus
     * @throws SQLException if a sql exception occurs.
     */
    @Override
    public DiscountStatus toEntity(ResultSet resultSet) throws SQLException {
        return DiscountStatus.builder()
                .id(resultSet.getLong(ID))
                .status(resultSet.getString(STATUS))
                .discount(resultSet.getBigDecimal(DISCOUNT))
                .build();
    }
}
