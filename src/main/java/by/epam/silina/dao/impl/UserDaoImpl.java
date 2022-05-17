package by.epam.silina.dao.impl;

import by.epam.silina.connection.ConnectionPool;
import by.epam.silina.dao.BaseDao;
import by.epam.silina.dao.UserDao;
import by.epam.silina.entity.User;
import by.epam.silina.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static by.epam.silina.dao.TableColumnName.*;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
            "SELECT U.ID, U.USERNAME, U.EMAIL, U.FIRST_NAME, U.LAST_NAME, U.DISCOUNT_STATUS_ID, U.ROLE_ID, U.STATUS_ID " +
                    "FROM USERS U " +
                    "WHERE U.USERNAME = ? AND U.PASSWORD = ?";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User userFromResultSet = getUserFromResultSet(resultSet);
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

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(ID))
                .username(resultSet.getString(USERNAME))
                .email(resultSet.getString(EMAIL))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .discountStatusId(resultSet.getLong(DISCOUNT_STATUS_ID))
                .roleId(resultSet.getLong(ROLE_ID))
                .build();
    }

    @Override
    public boolean insert(User user) {
        //todo
        return false;
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
