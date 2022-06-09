package by.epam.silina.dao;

import by.epam.silina.entity.User;
import by.epam.silina.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByUsername(String username) throws DaoException;

    boolean isUsernameExistInDB(String username) throws DaoException;

    boolean isEmailExistInDB(String email) throws DaoException;
}
