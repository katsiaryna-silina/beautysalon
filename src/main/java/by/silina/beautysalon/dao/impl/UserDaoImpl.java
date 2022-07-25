package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.UserDao;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.silina.beautysalon.dao.TableColumnName.PASSWORD;

/**
 * The UserDaoImpl class that responsible for getting data of users from datasource.
 *
 * @author Silina Katsiaryna
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String SELECT_USER_BY_USERNAME = """
            SELECT U.ID, U.USERNAME, U.PASSWORD, U.EMAIL, U.FIRST_NAME, U.LAST_NAME, U.PHONE_NUMBER, U.LAST_LOGIN, DS.STATUS DISCOUNT_STATUS, DS.DISCOUNT, UR.ROLE, US.STATUS
            FROM USERS U
            JOIN USER_ROLES UR ON UR.ID = U.ROLE_ID
            JOIN DISCOUNT_STATUSES DS ON DS.ID = U.DISCOUNT_STATUS_ID
            JOIN USER_STATUSES US ON US.ID = U.USER_STATUS_ID
            WHERE U.USERNAME = ?
            """;
    private static final String SELECT_USERNAME_IN_USERS = """
            SELECT USERNAME
            FROM USERS
            WHERE USERNAME = ?
            """;
    private static final String SELECT_EMAIL_IN_USERS = """
            SELECT EMAIL
            FROM USERS
            WHERE EMAIL = ?
            """;
    private static final String INSERT_USER = """
            INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER)
            VALUES (?, ?, ?, ?, ?, ?)
            """;
    private static final String SELECT_USER_PASSWORD = """
            SELECT PASSWORD
            FROM USERS
            WHERE ID = ?
            """;
    private static final String UPDATE_USER_PASSWORD = """
            UPDATE USERS
            SET PASSWORD = ?
            WHERE ID = ?
            """;
    private static final String SELECT_NUMBER_OF_USERS = """
            SELECT COUNT(ID)
            FROM USERS;
            """;
    private static final String SELECT_PAGED_USERS = """
            SELECT U.ID, U.USERNAME, U.PASSWORD, U.EMAIL, U.FIRST_NAME, U.LAST_NAME, U.PHONE_NUMBER, U.LAST_LOGIN, DS.STATUS DISCOUNT_STATUS, DS.DISCOUNT, UR.ROLE, US.STATUS
               FROM USERS U
               JOIN USER_ROLES UR ON UR.ID = U.ROLE_ID
               JOIN DISCOUNT_STATUSES DS ON DS.ID = U.DISCOUNT_STATUS_ID
               JOIN USER_STATUSES US ON US.ID = U.USER_STATUS_ID
               WHERE U.ID > ? LIMIT ?
               """;
    private static final String UPDATE_USER_ROLE = """
            UPDATE USERS U
            SET ROLE_ID = (
                SELECT UR.ID
                FROM USER_ROLES UR
                WHERE UR.ROLE = ?
                )
            WHERE U.ID = ?
            """;
    private static final String UPDATE_USER_DISCOUNT = """
            UPDATE USERS U
            SET DISCOUNT_STATUS_ID = (
                SELECT DS.ID
                FROM DISCOUNT_STATUSES DS
                WHERE DS.STATUS = ?
                )
            WHERE U.ID = ?
            """;
    private static final String UPDATE_USER_STATUS = """
            UPDATE USERS U
            SET USER_STATUS_ID = (
                SELECT US.ID
                FROM USER_STATUSES US
                WHERE US.STATUS = ?
                )
            WHERE U.ID = ?
            """;
    private static final String DELETE_USER = """
            DELETE 
            FROM USERS 
            WHERE ID = ?         
            """;
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final UserMapper userMapper = UserMapperImpl.getInstance();

    /**
     * Initializes a new UserDaoImpl.
     */
    private UserDaoImpl() {
    }

    /**
     * Gets the single instance of UserDaoImpl.
     *
     * @return DiscountStatusDaoImpl
     */
    public static UserDaoImpl getInstance() {
        return instance;
    }

    /**
     * Finds an user by username.
     *
     * @param username String. The username.
     * @return Optional of User
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public Optional<User> findUserByUsername(String username) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {

            preparedStatement.setString(1, username);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User userFromResultSet = userMapper.toEntity(resultSet);
                    optionalUser = Optional.of(userFromResultSet);
                    log.debug("User was found.");
                } else {
                    log.debug("User was not found.");
                }
            }
            return optionalUser;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Checks if username exists in the datasource.
     *
     * @param username String. The username.
     * @return boolean. True if this username exists; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean isUsernameExistInDB(String username) throws DaoException {
        return isParameterExits(username, SELECT_USERNAME_IN_USERS);
    }

    /**
     * Checks if email exists in the datasource.
     *
     * @param email String. The email.
     * @return boolean. True if this username exists; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean isEmailExistInDB(String email) throws DaoException {
        return isParameterExits(email, SELECT_EMAIL_IN_USERS);
    }

    /**
     * Checks if a passed parameter exists in the datasource.
     *
     * @param parameter String. Parameter to check.
     * @param select    String. Sql query.
     * @return boolean. True if this username exists; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    private boolean isParameterExits(String parameter, String select) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setString(1, parameter);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    log.debug("Parameter={} was found in database.", parameter);
                    return true;
                } else {
                    log.debug("Parameter={} was not found in database.", parameter);
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Finds user's password by user id.
     *
     * @param userId Long. User id.
     * @return Optional of String
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public Optional<String> findUserPasswordById(Long userId) throws DaoException {
        Optional<String> optionalPassword = Optional.empty();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_USER_PASSWORD)) {

            preparedStatement.setLong(1, userId);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    optionalPassword = Optional.of(resultSet.getString(PASSWORD));
                    log.debug("Password of user with id={} was found.", userId);
                } else {
                    log.debug("Password of user with id={} was not found.", userId);
                }
            }
            return optionalPassword;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Changes user's password by user id.
     *
     * @param userId      Long. User id.
     * @param newPassword String. New password.
     * @return boolean. True if this password is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean changeUserPassword(Long userId, String newPassword) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (var preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
                preparedStatement.setString(1, newPassword);
                preparedStatement.setLong(2, userId);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("Password of user with id={} was updated.", userId);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("Password of user with id={} was not updated. Transaction has being rolled back", userId);
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    /**
     * Finds number of all users.
     *
     * @return long
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public long findNumberOfUsers() throws DaoException {
        long numberOfUsers = 0L;
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_NUMBER_OF_USERS)) {

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    numberOfUsers = resultSet.getLong(1);
                }
            }
            return numberOfUsers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Finds paged users.
     *
     * @param fromUserId    Long. The first user to find.
     * @param numberOfUsers Integer. Number of users.
     * @return List of User
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public List<User> findPagedUsers(Long fromUserId, Integer numberOfUsers) throws DaoException {
        List<User> users = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_PAGED_USERS)) {

            preparedStatement.setLong(1, fromUserId);
            preparedStatement.setInt(2, numberOfUsers);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User userFromResultSet = userMapper.toEntity(resultSet);
                    users.add(userFromResultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    /**
     * Changes user's role.
     *
     * @param userId Long. The user id.
     * @param role   Role. New user's role.
     * @return boolean. True if user's role is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean changeUserRole(Long userId, Role role) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (var preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE)) {
                preparedStatement.setString(1, role.name().toLowerCase());
                preparedStatement.setLong(2, userId);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("User role with id={} was updated.", userId);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("User role with id={} was not updated. Transaction has being rolled back", userId);
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    /**
     * Changes user's discount.
     *
     * @param userId             Long. The user id.
     * @param discountStatusName String. Name of a new discount status.
     * @return boolean. True if discount is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean changeDiscount(Long userId, String discountStatusName) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (var preparedStatement = connection.prepareStatement(UPDATE_USER_DISCOUNT)) {
                preparedStatement.setString(1, discountStatusName);
                preparedStatement.setLong(2, userId);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("User discount status with id={} was updated.", userId);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("User discount status with id={} was not updated. Transaction has being rolled back", userId);
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    /**
     * Changes user's status.
     *
     * @param userId     Long. The user id.
     * @param userStatus UserStatus. New status.
     * @return boolean. True if this status is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean changeUserStatus(Long userId, UserStatus userStatus) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (var preparedStatement = connection.prepareStatement(UPDATE_USER_STATUS)) {
                preparedStatement.setString(1, userStatus.name());
                preparedStatement.setLong(2, userId);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("User status with id={} was updated.", userId);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("User status with id={} was not updated. Transaction has being rolled back", userId);
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    /**
     * Inserts a new user.
     *
     * @param user User. User to insert.
     * @return boolean. True if this user is inserted; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean insert(User user) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (var preparedStatement = connection.prepareStatement(INSERT_USER)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getFirstName());
                preparedStatement.setString(5, user.getLastName());
                preparedStatement.setString(6, user.getPhoneNumber());

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("User was inserted.");
                    return true;
                } else {
                    log.debug("User was not inserted.");
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    /**
     * Deletes user by user id.
     *
     * @param userId Long. The user id.
     * @return boolean. True if this user was deleted; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    @Override
    public boolean deleteById(Long userId) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (var preparedStatement = connection.prepareStatement(DELETE_USER)) {
                preparedStatement.setLong(1, userId);

                var rowCountDML = preparedStatement.executeUpdate();

                if (rowCountDML == 1) {
                    connection.commit();
                    log.debug("User with id={} was deleted.", userId);
                    return true;
                } else {
                    connection.rollback();
                    log.debug("User with id={} was not deleted. Transaction has being rolled back", userId);
                    return false;
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction.", ex);
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }
}
