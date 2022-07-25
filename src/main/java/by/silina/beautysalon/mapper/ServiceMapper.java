package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.entity.Serv;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The ServiceMapperImpl class responsible for mapping Service.
 *
 * @author Silina Katsiaryna
 */
public interface ServiceMapper {

    /**
     * Maps passed ResultSet to service entity.
     *
     * @param resultSet ResultSet
     * @return Serv
     * @throws SQLException if a sql exception occurs.
     */
    Serv toEntity(ResultSet resultSet) throws SQLException;

    /**
     * Maps passed Serv to ServiceDto.
     *
     * @param service Serv
     * @return ServiceDto
     */
    ServiceDto toDto(Serv service);
}
