package by.epam.silina.mapper;

import by.epam.silina.controller.SessionRequestContent;
import by.epam.silina.dto.UserRegistrationDto;
import by.epam.silina.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserMapper {
    User toEntity(UserRegistrationDto userRegistrationDto);

    User toEntity(ResultSet resultSet) throws SQLException;

    UserRegistrationDto toDto(User user);

    UserRegistrationDto toDto(SessionRequestContent sessionRequestContent);

}
