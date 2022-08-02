package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;


/**
 * The DiscountStatusDao interface.
 *
 * @author Silina Katsiaryna
 */
public interface UserDao {

    /**
     * Finds an user by username.
     *
     * @param username String. The username.
     * @return Optional of User
     * @throws DaoException if a dao exception occurs.
     */
    Optional<User> findUserByUsername(String username) throws DaoException;

    /**
     * Finds not blocked user by username.
     *
     * @param username String. The username.
     * @return Optional of User
     * @throws DaoException if a dao exception occurs.
     */
    Optional<User> findNotBlockedUserByUsername(String username) throws DaoException;

    /**
     * Checks if username exists in the datasource.
     *
     * @param username String. The username.
     * @return boolean. True if this username exists; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean isUsernameExistInDB(String username) throws DaoException;

    /**
     * Checks if email exists in the datasource.
     *
     * @param email String. The email.
     * @return boolean. True if this username exists; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean isEmailExistInDB(String email) throws DaoException;

    /**
     * Finds user's password by user id.
     *
     * @param userId Long. User id.
     * @return Optional of String
     * @throws DaoException if a dao exception occurs.
     */
    Optional<String> findUserPasswordById(Long userId) throws DaoException;

    /**
     * Changes user's password by user id.
     *
     * @param userId      Long. User id.
     * @param newPassword String. New password.
     * @return boolean. True if this password is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean changeUserPassword(Long userId, String newPassword) throws DaoException;

    /**
     * Finds number of all users.
     *
     * @return long
     * @throws DaoException if a dao exception occurs.
     */
    long findNumberOfUsers() throws DaoException;

    /**
     * Finds paged users.
     *
     * @param fromUserId    Long. The first user to find.
     * @param numberOfUsers Integer. Number of users.
     * @return List of User
     * @throws DaoException if a dao exception occurs.
     */
    List<User> findPagedUsers(Long fromUserId, Integer numberOfUsers) throws DaoException;

    /**
     * Changes user's role.
     *
     * @param userId Long. The user id.
     * @param role   Role. New user's role.
     * @return boolean. True if user's role is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean changeUserRole(Long userId, Role role) throws DaoException;

    /**
     * Changes user's discount.
     *
     * @param userId             Long. The user id.
     * @param discountStatusName String. Name of a new discount status.
     * @return boolean. True if discount is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean changeDiscount(Long userId, String discountStatusName) throws DaoException;

    /**
     * Changes user's status.
     *
     * @param userId     Long. The user id.
     * @param userStatus UserStatus. New status.
     * @return boolean. True if this status is changed; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean changeUserStatus(Long userId, UserStatus userStatus) throws DaoException;
}
