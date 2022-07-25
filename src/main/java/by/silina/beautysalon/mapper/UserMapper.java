package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UserMapperImpl class responsible for mapping User.
 *
 * @author Silina Katsiaryna
 */
public interface UserMapper {

    /**
     * Maps passed UserRegistrationDto to User entity.
     *
     * @param userRegistrationDto UserRegistrationDto
     * @return User
     */
    User toEntity(UserRegistrationDto userRegistrationDto);

    /**
     * Maps passed ResultSet to User entity.
     *
     * @param resultSet ResultSet
     * @return User
     * @throws SQLException if a sql exception occurs.
     */
    User toEntity(ResultSet resultSet) throws SQLException;

    /**
     * Maps passed SessionRequestContent to UserRegistrationDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return UserRegistrationDto
     */
    UserRegistrationDto toUserRegistrationDto(SessionRequestContent sessionRequestContent);

    /**
     * Maps passed SessionRequestContent to UserLoginDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return UserLoginDto
     */
    UserLoginDto toUserLoginDto(SessionRequestContent sessionRequestContent);

    /**
     * Maps passed SessionRequestContent to UserPasswordsDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return UserPasswordsDto
     */
    UserPasswordsDto toUserPasswordsDto(SessionRequestContent sessionRequestContent);

    /**
     * Maps passed User entity to UserAuthorizedDto.
     *
     * @param user User
     * @return UserAuthorizedDto
     */
    UserAuthorizedDto toUserAuthorizedDto(User user);
}
