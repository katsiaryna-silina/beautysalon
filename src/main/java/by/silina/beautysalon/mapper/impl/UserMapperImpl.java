package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.AttributeAndParameterName;
import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.util.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.PASSWORD;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.USERNAME;

public class UserMapperImpl implements UserMapper {
    private static final UserMapperImpl instance = new UserMapperImpl();

    private UserMapperImpl() {
    }

    public static UserMapperImpl getInstance() {
        return instance;
    }

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
                        .discount(resultSet.getDouble(TableColumnName.DISCOUNT))
                        .status(resultSet.getString(TableColumnName.DISCOUNT_STATUS))
                        .build())
                .phoneNumber(resultSet.getString(TableColumnName.PHONE_NUMBER))
                .userStatus(UserStatus.valueOf(resultSet.getString(TableColumnName.STATUS).toUpperCase()))
                .build();
    }

    @Override
    public UserRegistrationDto toUserRegistrationDto(SessionRequestContent sessionRequestContent) {
        var username = sessionRequestContent.getParameterByName(AttributeAndParameterName.USERNAME).strip();
        var password = sessionRequestContent.getParameterByName(AttributeAndParameterName.PASSWORD).strip();
        var repeatedPassword = sessionRequestContent.getParameterByName(AttributeAndParameterName.REPEATED_PASSWORD).strip();
        var email = sessionRequestContent.getParameterByName(AttributeAndParameterName.EMAIL);
        var firstName = sessionRequestContent.getParameterByName(AttributeAndParameterName.FIRST_NAME);
        var lastName = sessionRequestContent.getParameterByName(AttributeAndParameterName.LAST_NAME);
        var phoneNumber = sessionRequestContent.getParameterByName(AttributeAndParameterName.PHONE_NUMBER);

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

    @Override
    public UserLoginDto toUserLoginDto(SessionRequestContent sessionRequestContent) {
        var username = sessionRequestContent.getParameterByName(USERNAME);
        var password = sessionRequestContent.getParameterByName(PASSWORD);
        return UserLoginDto.builder()
                .username(username)
                .password(password)
                .build();
    }
}
