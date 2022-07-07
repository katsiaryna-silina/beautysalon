package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByUsername(String username) throws DaoException;

    boolean isUsernameExistInDB(String username) throws DaoException;

    boolean isEmailExistInDB(String email) throws DaoException;

    Optional<String> findUserPasswordById(Long userId) throws DaoException;

    boolean changeUserPassword(Long userId, String newPassword) throws DaoException;

    long findNumberOfUsers() throws DaoException;

    List<User> findPagedUsers(Long fromUserId, Integer numberOfUsers) throws DaoException;

    boolean changeUserRole(Long userId, Role role) throws DaoException;

    boolean changeDiscount(Long userId, String discountStatusName) throws DaoException;

    boolean changeUserStatus(Long userId, UserStatus userStatus) throws DaoException;
}
