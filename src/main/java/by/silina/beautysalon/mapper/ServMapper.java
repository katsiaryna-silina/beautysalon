package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.entity.Serv;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServMapper {
    Serv toEntity(ResultSet resultSet) throws SQLException;
}
