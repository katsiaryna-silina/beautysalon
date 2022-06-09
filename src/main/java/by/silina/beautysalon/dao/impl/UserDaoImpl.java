package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.dao.BaseDao;
import by.silina.beautysalon.dao.UserDao;
import by.silina.beautysalon.entity.User;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String SELECT_USER_BY_USERNAME = """
            SELECT U.ID, U.USERNAME, U.PASSWORD, U.EMAIL, U.FIRST_NAME, U.LAST_NAME, U.PHONE_NUMBER, DS.STATUS DISCOUNT_STATUS, DS.DISCOUNT, UR.ROLE, US.STATUS
            FROM USERS U
            JOIN USER_ROLES UR ON UR.ID = U.ROLE_ID
            JOIN DISCOUNT_STATUSES DS ON DS.ID = U.DISCOUNT_STATUS_ID
            JOIN USER_STATUSES US ON US.ID = U.STATUS_ID
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
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final UserMapper userMapper = UserMapperImpl.getInstance();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User userFromResultSet = userMapper.toEntity(resultSet);
                optionalUser = Optional.of(userFromResultSet);
                log.debug("User was found.");
            } else {
                log.debug("User was not found.");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean isUsernameExistInDB(String username) throws DaoException {
        return isParameterExits(username, SELECT_USERNAME_IN_USERS);
    }

    @Override
    public boolean isEmailExistInDB(String email) throws DaoException {
        return isParameterExits(email, SELECT_EMAIL_IN_USERS);
    }

    private boolean isParameterExits(String parameter, String select) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setString(1, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                log.debug("Parameter={} was found in database.", parameter);
                return true;
            } else {
                log.debug("Parameter={} was not found in database.", parameter);
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean insert(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getPhoneNumber());

            int rowCountDML = preparedStatement.executeUpdate();

            if (rowCountDML == 1) {
                log.debug("User was inserted.");
                return true;
            } else {
                log.debug("User was not inserted.");
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("delete unsupported");
    }

    @Override
    public List<User> findAll() {
        //todo
        return null;
    }

    @Override
    public User update(User user) {
        //todo
        return null;
    }
}
