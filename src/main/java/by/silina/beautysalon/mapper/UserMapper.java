package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.dto.UserRegistrationDto;
import by.silina.beautysalon.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserMapper {
    User toEntity(UserRegistrationDto userRegistrationDto);

    User toEntity(ResultSet resultSet) throws SQLException;

    UserRegistrationDto toDto(User user);

    UserRegistrationDto toDto(SessionRequestContent sessionRequestContent);

}
