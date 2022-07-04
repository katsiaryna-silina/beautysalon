package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.ServMapper;
import by.silina.beautysalon.model.entity.Serv;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServMapperImpl implements ServMapper {
    private static final ServMapperImpl instance = new ServMapperImpl();

    private ServMapperImpl() {
    }

    public static ServMapperImpl getInstance() {
        return instance;
    }

    @Override
    public Serv toEntity(ResultSet resultSet) throws SQLException {
        String isComplexString = resultSet.getString(TableColumnName.IS_COMPLEX);
        boolean isComplex = "Y".equalsIgnoreCase(isComplexString);

        return Serv.builder()
                .id(resultSet.getLong(TableColumnName.ID))
                .name(resultSet.getString(TableColumnName.NAME))
                .isComplex(isComplex)
                .price(resultSet.getBigDecimal(TableColumnName.PRICE))
                .minutesNeeded(resultSet.getInt(TableColumnName.MINUTES_NEEDED))
                .description(resultSet.getString(TableColumnName.DESCRIPTION))
                .build();
    }
}
