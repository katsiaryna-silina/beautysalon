package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByUsername(String username) throws DaoException;

    boolean isUsernameExistInDB(String username) throws DaoException;

    boolean isEmailExistInDB(String email) throws DaoException;
}
