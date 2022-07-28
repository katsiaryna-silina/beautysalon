package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.util.PasswordEncoder;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.jdbc.MockResultSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.dao.TableColumnName.DISCOUNT;
import static by.silina.beautysalon.dao.TableColumnName.LAST_LOGIN;

/**
 * The UserMapperImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class UserMapperImplTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    UserMapperImpl userMapper = UserMapperImpl.getInstance();
    @Mock
    SessionRequestContent sessionRequestContent;

    /**
     * Tests mapping UserRegistrationDto to User entity.
     */
    @Test
    void dtoToEntity() {
        var dto = UserRegistrationDto.builder()
                .username("username2")
                .password("2222")
                .repeatedPassword("2222")
                .email("user22@gmail.com")
                .firstName("Userr")
                .lastName("Rusess")
                .phoneNumber("+28869432")
                .build();

        var expectedEntity = User.builder()
                .username(dto.getUsername())
                .password(PasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        var actualResult = userMapper.toEntity(dto);

        //comparing passwords
        boolean isPasswordsVerified = PasswordEncoder.verifyPasswords(dto.getPassword(), actualResult.getPassword());
        Assertions.assertTrue(isPasswordsVerified);

        //comparing other fields
        expectedEntity.setPassword(null);
        actualResult.setPassword(null);
        Assertions.assertEquals(expectedEntity, actualResult);
    }

    /**
     * Tests mapping ResultSet to User entity.
     */
    @Test
    void resultSetToEntity() throws SQLException {
        var expectedUser = User.builder()
                .id(1L)
                .username("username1")
                .password("$2a$12$ClJ4jihN1cEXRuPZlke4t.9SPWvzFikIi4TxqLoU9yXmvZHGJwV9O")
                .email("user@gmail.com")
                .firstName("Use")
                .lastName("Ruse")
                .phoneNumber("+21769432")
                .lastLogin(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .discountStatus(DiscountStatus.builder()
                        .discount(BigDecimal.valueOf(5.0))
                        .status("disc status")
                        .build())
                .role(Role.ADMIN)
                .userStatus(UserStatus.ACTIVE)
                .build();

        //create MockResultSet
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnUsername = DSL.field(USERNAME, SQLDataType.VARCHAR(30));
        Field<String> columnPassword = DSL.field(PASSWORD, SQLDataType.VARCHAR(72));
        Field<String> columnEmail = DSL.field(EMAIL, SQLDataType.VARCHAR(100));
        Field<String> columnFirstName = DSL.field(FIRST_NAME, SQLDataType.VARCHAR(30));
        Field<String> columnLastName = DSL.field(LAST_NAME, SQLDataType.VARCHAR(30));
        Field<String> columnPhoneNumber = DSL.field(PHONE_NUMBER, SQLDataType.VARCHAR(20));
        Field<LocalDateTime> columnLastLogin = DSL.field(LAST_LOGIN, SQLDataType.LOCALDATETIME);
        Field<String> columnDiscountStatus = DSL.field(DISCOUNT_STATUS, SQLDataType.VARCHAR(20));
        Field<BigDecimal> columnDiscount = DSL.field(DISCOUNT, SQLDataType.DECIMAL(4, 1));
        Field<String> columnRole = DSL.field(ROLE, SQLDataType.VARCHAR(10));
        Field<String> columnUserStatus = DSL.field(STATUS, SQLDataType.VARCHAR(20));

        var result = context.newResult(columnId, columnUsername, columnPassword,
                columnEmail, columnFirstName, columnLastName, columnPhoneNumber,
                columnLastLogin, columnDiscountStatus, columnDiscount, columnRole, columnUserStatus);

        result.add(
                context.newRecord(columnId, columnUsername, columnPassword,
                                columnEmail, columnFirstName, columnLastName, columnPhoneNumber,
                                columnLastLogin, columnDiscountStatus, columnDiscount, columnRole, columnUserStatus)
                        .values(expectedUser.getId(), expectedUser.getUsername(), expectedUser.getPassword(),
                                expectedUser.getEmail(), expectedUser.getFirstName(), expectedUser.getLastName(), expectedUser.getPhoneNumber(),
                                expectedUser.getLastLogin(), expectedUser.getDiscountStatus().getStatus(), expectedUser.getDiscountStatus().getDiscount(),
                                expectedUser.getRole().name(), expectedUser.getUserStatus().name()));
        ResultSet mockResultSet = new MockResultSet(result);
        mockResultSet.next();

        var actualResult = userMapper.toEntity(mockResultSet);
        Assertions.assertEquals(expectedUser, actualResult);
    }

    /**
     * Tests mapping SessionRequestContent to UserRegistrationDto.
     */
    @Test
    void sessionRequestContentToUserRegistrationDto() {
        var username = "username1";
        var password = "$2a$12$ClJ4jihN1cEXRuPZlke4t.9SPWvzFikIi4TxqLoU9yXmvZHGJwV9O";
        var email = "user@gmail.com";
        var firstName = "Use";
        var lastName = "Ruse";
        var phoneNumber = "+21769432";

        Mockito.when(sessionRequestContent.getParameterByName(USERNAME)).thenReturn(username);
        Mockito.when(sessionRequestContent.getParameterByName(PASSWORD)).thenReturn(password);
        Mockito.when(sessionRequestContent.getParameterByName(REPEATED_PASSWORD)).thenReturn(password);
        Mockito.when(sessionRequestContent.getParameterByName(EMAIL)).thenReturn(email);
        Mockito.when(sessionRequestContent.getParameterByName(FIRST_NAME)).thenReturn(firstName);
        Mockito.when(sessionRequestContent.getParameterByName(LAST_NAME)).thenReturn(lastName);
        Mockito.when(sessionRequestContent.getParameterByName(PHONE_NUMBER)).thenReturn(phoneNumber);

        var expectedDto = UserRegistrationDto.builder()
                .username(username)
                .password(password)
                .repeatedPassword(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();

        var actualResult = userMapper.toUserRegistrationDto(sessionRequestContent);
        Assertions.assertEquals(expectedDto, actualResult);
    }

    /**
     * Tests mapping SessionRequestContent to UserLoginDto.
     */
    @Test
    void sessionRequestContentToUserLoginDto() {
        var username = "username1";
        var password = "9990";

        Mockito.when(sessionRequestContent.getParameterByName(USERNAME)).thenReturn(username);
        Mockito.when(sessionRequestContent.getParameterByName(PASSWORD)).thenReturn(password);

        var expectedDto = UserLoginDto.builder()
                .username(username)
                .password(password)
                .build();

        var actualResult = userMapper.toUserLoginDto(sessionRequestContent);
        Assertions.assertEquals(expectedDto, actualResult);
    }

    /**
     * Tests mapping SessionRequestContent to UserPasswordsDto.
     */
    @Test
    void sessionRequestContentToUserPasswordsDto() {
        var userId = 1L;
        var currentPassword = "9990";
        var newPassword = "9123";
        var repeatedNewPassword = "9123";

        Mockito.when(sessionRequestContent.getSessionAttributeByName(USER_ID)).thenReturn(userId);
        Mockito.when(sessionRequestContent.getParameterByName(CURRENT_PASSWORD)).thenReturn(currentPassword);
        Mockito.when(sessionRequestContent.getParameterByName(NEW_PASSWORD)).thenReturn(newPassword);
        Mockito.when(sessionRequestContent.getParameterByName(REPEATED_PASSWORD)).thenReturn(repeatedNewPassword);

        var expectedDto = UserPasswordsDto.builder()
                .userId(userId)
                .currentPassword(currentPassword)
                .newPassword(newPassword)
                .repeatedNewPassword(repeatedNewPassword)
                .build();

        var actualResult = userMapper.toUserPasswordsDto(sessionRequestContent);
        Assertions.assertEquals(expectedDto, actualResult);
    }

    /**
     * Tests mapping User entity to UserAuthorizedDto.
     */
    @Test
    void entityToDto() {
        var entity = User.builder()
                .id(1L)
                .username("username1")
                .email("user@gmail.com")
                .firstName("Use")
                .lastName("Ruse")
                .phoneNumber("+21769432")
                .discountStatus(DiscountStatus.builder()
                        .discount(BigDecimal.valueOf(5.0))
                        .status("disc status")
                        .build())
                .role(Role.ADMIN)
                .userStatus(UserStatus.ACTIVE)
                .lastLogin(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .build();

        var expectedDto = UserAuthorizedDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .discountStatus(entity.getDiscountStatus())
                .userStatus(entity.getUserStatus())
                .lastLogin(entity.getLastLogin())
                .build();

        var actualResult = userMapper.toUserAuthorizedDto(entity);
        Assertions.assertEquals(expectedDto, actualResult);
    }
}
