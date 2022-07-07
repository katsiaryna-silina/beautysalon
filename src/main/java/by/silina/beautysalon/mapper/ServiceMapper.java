package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.entity.Serv;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServiceMapper {
    Serv toEntity(ResultSet resultSet) throws SQLException;

    ServiceDto toDto(Serv service);
}
