package by.epam.silina.dao;

import by.epam.silina.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
}
