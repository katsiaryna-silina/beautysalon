package by.epam.silina.dao;

import by.epam.silina.entity.User;
import by.epam.silina.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException;
}
