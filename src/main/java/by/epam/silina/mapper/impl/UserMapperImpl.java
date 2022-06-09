package by.epam.silina.mapper.impl;

import by.epam.silina.controller.SessionRequestContent;
import by.epam.silina.controller.command.AttributeAndParameterName;
import by.epam.silina.dao.TableColumnName;
import by.epam.silina.dto.UserRegistrationDto;
import by.epam.silina.entity.DiscountStatus;
import by.epam.silina.entity.Role;
import by.epam.silina.entity.User;
import by.epam.silina.entity.UserStatus;
import by.epam.silina.mapper.UserMapper;
import by.epam.silina.util.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public UserRegistrationDto toDto(User user) {
        return null;
    }

    @Override
    public UserRegistrationDto toDto(SessionRequestContent sessionRequestContent) {
        String username = sessionRequestContent.getParameterByName(AttributeAndParameterName.USERNAME).strip();
        String password = sessionRequestContent.getParameterByName(AttributeAndParameterName.PASSWORD).strip();
        String repeatedPassword = sessionRequestContent.getParameterByName(AttributeAndParameterName.REPEATED_PASSWORD).strip();
        String email = sessionRequestContent.getParameterByName(AttributeAndParameterName.EMAIL);
        String firstName = sessionRequestContent.getParameterByName(AttributeAndParameterName.FIRST_NAME);
        String lastName = sessionRequestContent.getParameterByName(AttributeAndParameterName.LAST_NAME);
        String phoneNumber = sessionRequestContent.getParameterByName(AttributeAndParameterName.PHONE_NUMBER);

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
}
