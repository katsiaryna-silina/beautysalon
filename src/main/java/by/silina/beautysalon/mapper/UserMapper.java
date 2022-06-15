package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserMapper {
    User toEntity(UserRegistrationDto userRegistrationDto);

    User toEntity(ResultSet resultSet) throws SQLException;

    UserRegistrationDto toUserRegistrationDto(SessionRequestContent sessionRequestContent);

    UserLoginDto toUserLoginDto(SessionRequestContent sessionRequestContent);

    UserPasswordsDto toUserPasswordsDto(SessionRequestContent sessionRequestContent);

    UserAuthorizedDto toUserAuthorizedDto(User user);
}
