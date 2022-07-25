package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The UserService interface.
 *
 * @author Silina Katsiaryna
 */
public interface UserService {

    /**
     * Finds a user using data from dto.
     *
     * @param userLoginDto UserLoginDto
     * @return Optional of User
     * @throws ServiceException if a service exception occurs.
     */
    Optional<User> findUser(UserLoginDto userLoginDto) throws ServiceException;

    /**
     * Finds a user by username.
     *
     * @param username String
     * @return Optional of User
     * @throws ServiceException if a service exception occurs.
     */
    Optional<User> findUser(String username) throws ServiceException;

    /**
     * Adds a new user using data from dto.
     *
     * @param userRegistrationDto UserRegistrationDto
     * @return Map of Strings
     * @throws ServiceException if a service exception occurs.
     */
    Map<String, String> addUser(UserRegistrationDto userRegistrationDto) throws ServiceException;

    /**
     * Changes password using data from dto.
     *
     * @param userPasswordsDto UserPasswordsDto
     * @return Map of Strings
     * @throws ServiceException if a service exception occurs.
     */
    Map<String, String> changePassword(UserPasswordsDto userPasswordsDto) throws ServiceException;

    /**
     * Finds number of users.
     *
     * @return long. Number of users.
     * @throws ServiceException if a service exception occurs.
     */
    long findNumberOfUsers() throws ServiceException;

    /**
     * Finds paged users.
     *
     * @param fromUserId    Long. The first user to find.
     * @param numberOfUsers Integer. Number of users.
     * @return List of UserAuthorizedDto
     * @throws ServiceException if a service exception occurs.
     */
    List<UserAuthorizedDto> findPagedUserDtoList(Long fromUserId, Integer numberOfUsers) throws ServiceException;

    /**
     * Changes user's discount.
     *
     * @param userId             Long. User id.
     * @param discountStatusName String. Name of new discount status.
     * @return boolean. True if discount is changed; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean changeDiscount(Long userId, String discountStatusName) throws ServiceException;

    /**
     * Changes user's discount and role.
     *
     * @param userId             Long. User id.
     * @param role               Role. New user's role.
     * @param discountStatusName String. Name of new discount status.
     * @return boolean. True if discount is changed; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean changeUserRoleAndDiscount(Long userId, Role role, String discountStatusName) throws ServiceException;

    /**
     * Changes user's status.
     *
     * @param userId     Long. User id.
     * @param userStatus UserStatus. New user status.
     * @return boolean. True if discount is changed; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean changeUserStatus(Long userId, UserStatus userStatus) throws ServiceException;

    /**
     * Deletes user by id.
     *
     * @param id Long. User id.
     * @return boolean. True if user is deleted; false otherwise.
     * @throws ServiceException if a service exception occurs.
     */
    boolean deleteUser(Long id) throws ServiceException;
}
