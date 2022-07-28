package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.util.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;

/**
 * The UserMapperImpl class responsible for mapping User.
 *
 * @author Silina Katsiaryna
 */
public class UserMapperImpl implements UserMapper {
    private static final UserMapperImpl instance = new UserMapperImpl();

    /**
     * Initializes a new UserMapperImpl.
     */
    private UserMapperImpl() {
    }

    /**
     * Gets the single instance of UserMapperImpl.
     *
     * @return UserMapperImpl
     */
    public static UserMapperImpl getInstance() {
        return instance;
    }

    /**
     * Maps passed UserRegistrationDto to User entity.
     *
     * @param userRegistrationDto UserRegistrationDto
     * @return User
     */
    @Override
    public User toEntity(UserRegistrationDto userRegistrationDto) {
        var encodePassword = PasswordEncoder.encode(userRegistrationDto.getPassword());
        return User.builder()
                .username(userRegistrationDto.getUsername())
                .password(encodePassword)
                .email(userRegistrationDto.getEmail())
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .phoneNumber(userRegistrationDto.getPhoneNumber())
                .build();
    }

    /**
     * Maps passed ResultSet to User entity.
     *
     * @param resultSet ResultSet
     * @return User
     * @throws SQLException if a sql exception occurs.
     */
    @Override
    public User toEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(TableColumnName.ID))
                .username(resultSet.getString(TableColumnName.USERNAME))
                .password(resultSet.getString(TableColumnName.PASSWORD))
                .email(resultSet.getString(TableColumnName.EMAIL))
                .firstName(resultSet.getString(TableColumnName.FIRST_NAME))
                .lastName(resultSet.getString(TableColumnName.LAST_NAME))
                .role(Role.valueOf(resultSet.getString(TableColumnName.ROLE).toUpperCase()))
                .discountStatus(DiscountStatus.builder()
                        .status(resultSet.getString(TableColumnName.DISCOUNT_STATUS))
                        .discount(resultSet.getBigDecimal(TableColumnName.DISCOUNT))
                        .build())
                .lastLogin(resultSet.getTimestamp(TableColumnName.LAST_LOGIN).toLocalDateTime())
                .phoneNumber(resultSet.getString(TableColumnName.PHONE_NUMBER))
                .userStatus(UserStatus.valueOf(resultSet.getString(TableColumnName.STATUS).toUpperCase()))
                .build();
    }

    /**
     * Maps passed SessionRequestContent to UserRegistrationDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return UserRegistrationDto
     */
    @Override
    public UserRegistrationDto toUserRegistrationDto(SessionRequestContent sessionRequestContent) {
        var username = sessionRequestContent.getParameterByName(USERNAME).strip();
        var password = sessionRequestContent.getParameterByName(PASSWORD).strip();
        var repeatedPassword = sessionRequestContent.getParameterByName(REPEATED_PASSWORD).strip();
        var email = sessionRequestContent.getParameterByName(EMAIL).strip();
        var firstName = sessionRequestContent.getParameterByName(FIRST_NAME).strip();
        var lastName = sessionRequestContent.getParameterByName(LAST_NAME).strip();
        var phoneNumber = sessionRequestContent.getParameterByName(PHONE_NUMBER).strip();

        return UserRegistrationDto.builder()
                .username(username)
                .password(password)
                .repeatedPassword(repeatedPassword)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
    }

    /**
     * Maps passed SessionRequestContent to UserLoginDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return UserLoginDto
     */
    @Override
    public UserLoginDto toUserLoginDto(SessionRequestContent sessionRequestContent) {
        var username = sessionRequestContent.getParameterByName(USERNAME);
        var password = sessionRequestContent.getParameterByName(PASSWORD);

        return UserLoginDto.builder()
                .username(username)
                .password(password)
                .build();
    }

    /**
     * Maps passed SessionRequestContent to UserPasswordsDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return UserPasswordsDto
     */
    @Override
    public UserPasswordsDto toUserPasswordsDto(SessionRequestContent sessionRequestContent) {
        var userId = (Long) sessionRequestContent.getSessionAttributeByName(USER_ID);
        var currentPassword = sessionRequestContent.getParameterByName(CURRENT_PASSWORD);
        var newPassword = sessionRequestContent.getParameterByName(NEW_PASSWORD);
        var repeatedNewPassword = sessionRequestContent.getParameterByName(REPEATED_PASSWORD);

        return UserPasswordsDto.builder()
                .userId(userId)
                .currentPassword(currentPassword)
                .newPassword(newPassword)
                .repeatedNewPassword(repeatedNewPassword)
                .build();
    }

    /**
     * Maps passed User entity to UserAuthorizedDto.
     *
     * @param user User
     * @return UserAuthorizedDto
     */
    @Override
    public UserAuthorizedDto toUserAuthorizedDto(User user) {
        return UserAuthorizedDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .discountStatus(user.getDiscountStatus())
                .userStatus(user.getUserStatus())
                .lastLogin(user.getLastLogin())
                .build();
    }
}
