package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.jdbc.MockResultSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The UserDaoImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @InjectMocks
    UserDaoImpl userDao;
    @Mock
    ConnectionPool mockConnectionPool;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    /**
     * Tests finding user by its name.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findUserByUsername() throws SQLException, DaoException {
        //create expectedResult
        var user = createUser1();
        var expectedResult = Optional.of(user);
        var username = user.getUsername();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        ResultSet mockResultSet = createMockedResultSetWithUsers(List.of(user));

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        var actualResult = userDao.findUserByUsername(username);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests checking if username exists.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void isUsernameExistInDBPositive() throws SQLException, DaoException {
        //create expectedResult
        var user = createUser1();
        var allUsers = List.of(user, createUser2());
        var username = user.getUsername();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            boolean userNameExists = !allUsers.stream()
                    .map(User::getUsername)
                    .filter(username::equals)
                    .toList()
                    .isEmpty();

            DSLContext context = DSL.using(SQLDialect.DEFAULT);
            Field<String> columnUsername = DSL.field(USERNAME, SQLDataType.VARCHAR(30));
            var result = context.newResult(columnUsername);

            if (userNameExists) {
                result.add(context.newRecord(columnUsername).values(username));
            }
            return new MockResultSet(result);
        });

        var actualResult = userDao.isUsernameExistInDB(username);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking if username exists.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void isUsernameExistInDBNegative() throws SQLException, DaoException {
        var user = createUser1();
        var allUsers = List.of(user, createUser2());
        var username = "hot_username";

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            boolean userNameExists = !allUsers.stream()
                    .map(User::getUsername)
                    .filter(username::equals)
                    .toList()
                    .isEmpty();

            DSLContext context = DSL.using(SQLDialect.DEFAULT);
            Field<String> columnUsername = DSL.field(USERNAME, SQLDataType.VARCHAR(30));
            var result = context.newResult(columnUsername);

            if (userNameExists) {
                result.add(context.newRecord(columnUsername).values(username));
            }
            return new MockResultSet(result);
        });

        var actualResult = userDao.isUsernameExistInDB(username);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking if email exists.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void isEmailExistInDBPositive() throws SQLException, DaoException {
        var user = createUser1();
        var allUsers = List.of(user, createUser2());
        var email = user.getEmail();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            boolean userNameExists = !allUsers.stream()
                    .map(User::getEmail)
                    .filter(email::equals)
                    .toList()
                    .isEmpty();

            DSLContext context = DSL.using(SQLDialect.DEFAULT);
            Field<String> columnUsername = DSL.field(USERNAME, SQLDataType.VARCHAR(30));
            var result = context.newResult(columnUsername);

            if (userNameExists) {
                result.add(context.newRecord(columnUsername).values(email));
            }
            return new MockResultSet(result);
        });

        var actualResult = userDao.isUsernameExistInDB(email);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking if email exists.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void isEmailExistInDBNegative() throws SQLException, DaoException {
        var user = createUser1();
        var allUsers = List.of(user, createUser2());
        var email = "joke@gmail.com";

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            boolean userNameExists = !allUsers.stream()
                    .map(User::getEmail)
                    .filter(email::equals)
                    .toList()
                    .isEmpty();

            DSLContext context = DSL.using(SQLDialect.DEFAULT);
            Field<String> columnUsername = DSL.field(USERNAME, SQLDataType.VARCHAR(30));
            var result = context.newResult(columnUsername);

            if (userNameExists) {
                result.add(context.newRecord(columnUsername).values(email));
            }
            return new MockResultSet(result);
        });

        var actualResult = userDao.isUsernameExistInDB(email);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests finding password by user id.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findUserPasswordById() throws SQLException, DaoException {
        //create expectedResult
        var user = createUser1();
        var userId = user.getId();
        var password = user.getPassword();
        var expectedResult = Optional.of(password);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<String> columnPassword = DSL.field(PASSWORD, SQLDataType.VARCHAR(72));

        var result = context.newResult(columnPassword);
        result.add(context.newRecord(columnPassword).values(password));
        MockResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        var actualResult = userDao.findUserPasswordById(userId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests changing user password.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void changeUserPassword() throws SQLException, DaoException {
        var user = createUser1();
        var userId = user.getId();
        var password = user.getPassword();
        var newPassword = "$2a$12$ClJ4jihN1cEXRuPZlke4t.8SPWvzFikIi4TxqLoU9yXmvZHGJwV9O";
        Assertions.assertNotEquals(password, newPassword);

        //create rowCountDML to replace a result of mockPreparedStatement.executeUpdate()
        var rowCountDML = 1;
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            user.setPassword(newPassword);
            return rowCountDML;
        });

        var isPasswordUpdated = userDao.changeUserPassword(userId, newPassword);
        Assertions.assertTrue(isPasswordUpdated);

        var updatedPassword = user.getPassword();
        Assertions.assertEquals(updatedPassword, newPassword);
    }

    /**
     * Tests finding number of users.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findNumberOfUsers() throws SQLException, DaoException {
        //create expectedResult
        var users = List.of(createUser1(), createUser2());
        long expectedResult = users.size();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnNumberOfUsers = DSL.field(NUMBER_OF_USERS, SQLDataType.BIGINT);

        var result = context.newResult(columnNumberOfUsers);
        result.add(context.newRecord(columnNumberOfUsers).values(expectedResult));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        long actualResult = userDao.findNumberOfUsers();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding paged users.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findPagedUsersWithAllDisplayed() throws SQLException, DaoException {
        //create expectedResult
        var allUsers = List.of(createUser1(), createUser2());
        var fromUserId = 1L;
        var numberOfUsers = allUsers.size();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            var usersToDisplay = new ArrayList<>(allUsers);
            return createMockedResultSetWithUsers(usersToDisplay);
        });

        //comparison two results
        var actualResult = userDao.findPagedUsers(fromUserId, numberOfUsers);
        Assertions.assertEquals(allUsers, actualResult);
    }

    /**
     * Tests finding paged users.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findPagedUsersWithOneDisplayed() throws SQLException, DaoException {
        //create expectedResult
        var allUsers = List.of(createUser1(), createUser2());
        var fromUserId = 1L;
        var numberOfUsers = 1;

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            var usersToDisplay = new ArrayList<>(allUsers.subList(Math.toIntExact(fromUserId), numberOfUsers));
            return createMockedResultSetWithUsers(usersToDisplay);
        });

        //comparison two results
        var actualResult = userDao.findPagedUsers(fromUserId, numberOfUsers);
        Assertions.assertEquals(allUsers.subList(Math.toIntExact(fromUserId), numberOfUsers), actualResult);
    }

    /**
     * Tests changing user's role.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void changeUserRole() throws SQLException, DaoException {
        var user = createUser1();
        var userId = user.getId();
        var role = user.getRole();
        var newRole = Role.CLIENT;
        Assertions.assertNotEquals(role, newRole);

        //create rowCountDML to replace a result of mockPreparedStatement.executeUpdate()
        var rowCountDML = 1;
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            user.setRole(newRole);
            return rowCountDML;
        });

        var isRoleUpdated = userDao.changeUserRole(userId, newRole);
        Assertions.assertTrue(isRoleUpdated);

        var updatedRole = user.getRole();
        Assertions.assertEquals(newRole, updatedRole);
    }

    /**
     * Tests changing user's discount.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void changeDiscount() throws SQLException, DaoException {
        var user = createUser1();
        var userId = user.getId();
        var discountStatusName = user.getDiscountStatus().getStatus();
        var newDiscountStatus = DiscountStatus.builder().status("new status").build();
        var newDiscountStatusName = newDiscountStatus.getStatus();
        Assertions.assertNotEquals(discountStatusName, newDiscountStatusName);

        //create rowCountDML to replace a result of mockPreparedStatement.executeUpdate()
        var rowCountDML = 1;
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            user.setDiscountStatus(newDiscountStatus);
            return rowCountDML;
        });

        var isRoleUpdated = userDao.changeDiscount(userId, newDiscountStatusName);
        Assertions.assertTrue(isRoleUpdated);

        var updatedDiscountStatusName = user.getDiscountStatus().getStatus();
        Assertions.assertEquals(newDiscountStatusName, updatedDiscountStatusName);
    }

    /**
     * Tests changing user's status.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void changeUserStatus() throws SQLException, DaoException {
        var user = createUser1();
        var userId = user.getId();
        var userStatus = user.getUserStatus();
        var newUserStatus = UserStatus.BLOCKED;
        Assertions.assertNotEquals(userStatus, newUserStatus);

        //create rowCountDML to replace a result of mockPreparedStatement.executeUpdate()
        var rowCountDML = 1;
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            user.setUserStatus(newUserStatus);
            return rowCountDML;
        });

        var isRoleUpdated = userDao.changeUserStatus(userId, newUserStatus);
        Assertions.assertTrue(isRoleUpdated);

        var updatedUserStatus = user.getUserStatus();
        Assertions.assertEquals(newUserStatus, updatedUserStatus);
    }

    /**
     * Tests inserting a new user.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void insert() throws SQLException, DaoException {
        //create rowCountDML to replace a result of mockSelectOrderId.executeUpdate()
        int rowCountDML = 1;
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(rowCountDML);

        boolean isUserInserted = userDao.insert(createUserForInsert());
        Assertions.assertTrue(isUserInserted);
    }

    /**
     * Tests deleting user by user id.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void deleteById() throws SQLException, DaoException {
        var user1 = createUser1();
        AtomicReference<List<User>> allUsers = new AtomicReference<>(List.of(user1, createUser2()));
        var userId = user1.getId();

        //create rowCountDML to replace a result of mockSelectOrderId.executeUpdate()
        var rowCountDML = 1;
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            allUsers.set(allUsers.get().stream().filter(el -> !el.getId().equals(userId)).toList());
            return rowCountDML;
        });

        boolean isUserDeleted = userDao.deleteById(userId);
        Assertions.assertTrue(isUserDeleted);
        Assertions.assertFalse(allUsers.get().stream().anyMatch(el -> el.equals(user1)));
    }

    /**
     * Creates user.
     */
    User createUser1() {
        return User.builder()
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
    }

    /**
     * Creates user.
     */
    User createUser2() {
        return User.builder()
                .id(2L)
                .username("username2")
                .password("$2a$12$ClJ4jihN1cEXRuPZlke4t.9SPWvzFikIi4TxqLoU9yXmvZHGJwV9O")
                .email("user22@gmail.com")
                .firstName("Userr")
                .lastName("Rusess")
                .phoneNumber("+28869432")
                .lastLogin(LocalDateTime.parse("2022-07-28 20:20:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .discountStatus(DiscountStatus.builder()
                        .discount(BigDecimal.valueOf(5.0))
                        .status("disc status")
                        .build())
                .role(Role.ADMIN)
                .userStatus(UserStatus.ACTIVE)
                .build();
    }

    /**
     * Creates user.
     */
    User createUserForInsert() {
        return User.builder()
                .id(3L)
                .username("username3")
                .password("$2a$12$ClJ4jihN1cEXRuPZlke4t.9SPWvzFikIi4TxqLoU9yXmvZHGJwV9O")
                .email("user33@gmail.com")
                .firstName("Useert")
                .lastName("Rumm")
                .phoneNumber("+29029411")
                .build();
    }

    /**
     * Creates MockedResultSet.
     */
    ResultSet createMockedResultSetWithUsers(List<User> users) {
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

        users.forEach(user -> {
            result.add(
                    context.newRecord(columnId, columnUsername, columnPassword,
                                    columnEmail, columnFirstName, columnLastName, columnPhoneNumber,
                                    columnLastLogin, columnDiscountStatus, columnDiscount, columnRole, columnUserStatus)
                            .values(user.getId(), user.getUsername(), user.getPassword(),
                                    user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
                                    user.getLastLogin(), user.getDiscountStatus().getStatus(), user.getDiscountStatus().getDiscount(),
                                    user.getRole().name(), user.getUserStatus().name()));
        });
        return new MockResultSet(result);
    }
}
