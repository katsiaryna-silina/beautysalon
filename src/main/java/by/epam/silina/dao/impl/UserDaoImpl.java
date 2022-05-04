package by.epam.silina.dao.impl;

import by.epam.silina.dao.BaseDao;
import by.epam.silina.dao.UserDao;
import by.epam.silina.entity.User;
import by.epam.silina.exception.DaoException;
import by.epam.silina.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             //Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM USERS WHERE USERNAME = ?")) {
            /*Statement statement = connection.createStatement()) {
               String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = '" + login + "'";
               ResultSet resultSet = statement.executeQuery(sql);
                */
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            String passwordFromDB;
            if (resultSet.next()) {
                passwordFromDB = resultSet.getString(1);
                match = password.equals(passwordFromDB);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
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
