package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.ServiceMapper;
import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.entity.Serv;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapperImpl implements ServiceMapper {
    private static final ServiceMapperImpl instance = new ServiceMapperImpl();

    private ServiceMapperImpl() {
    }

    public static ServiceMapperImpl getInstance() {
        return instance;
    }

    @Override
    public Serv toEntity(ResultSet resultSet) throws SQLException {
        String isComplexString = resultSet.getString(TableColumnName.IS_COMPLEX);
        boolean isComplex = "Y".equalsIgnoreCase(isComplexString);

        String isDeprecatedString = resultSet.getString(TableColumnName.IS_DEPRECATED);
        boolean isDeprecated = "Y".equalsIgnoreCase(isDeprecatedString);

        return Serv.builder()
                .id(resultSet.getLong(TableColumnName.ID))
                .name(resultSet.getString(TableColumnName.NAME))
                .isComplex(isComplex)
                .price(resultSet.getBigDecimal(TableColumnName.PRICE))
                .minutesNeeded(resultSet.getInt(TableColumnName.MINUTES_NEEDED))
                .description(resultSet.getString(TableColumnName.DESCRIPTION))
                .isDeprecated(isDeprecated)
                .build();
    }

    @Override
    public ServiceDto toDto(Serv service) {
        return ServiceDto.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .isComplex(service.isComplex())
                .price(service.getPrice())
                .isDeprecated(service.isDeprecated())
                .minutesNeeded(service.getMinutesNeeded())
                .build();
    }
}
