package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.UserDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.util.PasswordEncoder;
import by.silina.beautysalon.validator.UserPasswordsDtoValidator;
import by.silina.beautysalon.validator.UserRegistrationDtoValidator;
import by.silina.beautysalon.validator.UserValidator;
import by.silina.beautysalon.validator.impl.UserPasswordsDtoValidatorImpl;
import by.silina.beautysalon.validator.impl.UserRegistrationDtoValidatorImpl;
import by.silina.beautysalon.validator.impl.UserValidatorImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserValidator userValidator = UserValidatorImpl.getInstance();
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private final UserMapper userMapper = UserMapperImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    public Optional<User> findUser(UserLoginDto userLoginDto) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();

        var usernameFromDto = userLoginDto.getUsername();
        var passwordFromDto = userLoginDto.getPassword();

        if (userValidator.checkUsername(usernameFromDto) && userValidator.checkPassword(passwordFromDto)) {
            try {
                optionalUser = userDao.findUserByUsername(usernameFromDto);
                if (optionalUser.isPresent()) {
                    var passwordFromDB = optionalUser.get().getPassword();

                    if (!PasswordEncoder.verifyPasswords(passwordFromDto, passwordFromDB)) {
                        optionalUser = Optional.empty();
                    }
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUser(String username) throws ServiceException {
        try {
            return userDao.findUserByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<String, String> addUser(UserRegistrationDto userRegistrationDto) throws ServiceException {
        UserRegistrationDtoValidator userRegistrationDtoValidator = UserRegistrationDtoValidatorImpl.getInstance();
        Map<String, String> errorMap = userRegistrationDtoValidator.checkUserRegistrationDto(userRegistrationDto);

        if (errorMap.isEmpty()) {
            try {
                var username = userRegistrationDto.getUsername();
                if (userDao.isUsernameExistInDB(username)) {
                    //todo
                    // errorMap.put(USERNAME_ERROR_MESSAGE, true);
                    // true - has message
                    errorMap.put(USERNAME_ERROR_MESSAGE, "Username is already exist.");
                }

                var email = userRegistrationDto.getEmail();
                if (userDao.isEmailExistInDB(email)) {
                    errorMap.put(EMAIL_ERROR_MESSAGE, "Email with the same name is already registered.");
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }

        if (errorMap.isEmpty()) {
            User user = userMapper.toEntity(userRegistrationDto);
            try {
                if (!userDao.insert(user)) {
                    errorMap.put(CREATE_NEW_USER_ERROR_MESSAGE, "Cannot add new user.");
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return errorMap;
    }

    @Override
    public Map<String, String> changePassword(UserPasswordsDto userPasswordsDto) throws ServiceException {
        UserPasswordsDtoValidator userPasswordsDtoValidator = UserPasswordsDtoValidatorImpl.getInstance();

        Map<String, String> errorMap = userPasswordsDtoValidator.checkUserPasswordsDto(userPasswordsDto);

        if (errorMap.isEmpty()) {
            var userId = userPasswordsDto.getUserId();
            var currentPassword = userPasswordsDto.getCurrentPassword();
            try {
                var passwordFromDB = userDao.findUserPasswordById(userId);

                if (passwordFromDB.isEmpty()) {
                    errorMap.put(PASSWORD_ERROR_MESSAGE, "Cannot change password.");
                } else if (!PasswordEncoder.verifyPasswords(currentPassword, passwordFromDB.get())) {
                    errorMap.put(PASSWORD_ERROR_MESSAGE, "Password is not correct.");
                } else {
                    var newPassword = PasswordEncoder.encode(userPasswordsDto.getNewPassword());
                    boolean isPasswordChanged = userDao.changeUserPassword(userId, newPassword);
                    if (!isPasswordChanged) {
                        errorMap.put(PASSWORD_ERROR_MESSAGE, "Cannot change password.");
                    }
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return errorMap;
    }

    @Override
    public long findNumberOfUsers() throws ServiceException {
        try {
            return userDao.findNumberOfUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserAuthorizedDto> findPagedUserDtoList(Long fromUserId, Integer numberOfUsers) throws ServiceException {
        List<User> pagedUsers;
        try {
            pagedUsers = userDao.findPagedUsers(fromUserId, numberOfUsers);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return pagedUsers.stream()
                .map(userMapper::toUserAuthorizedDto)
                .toList();
    }

    @Override
    public boolean changeDiscount(Long userId, String discountStatusName) throws ServiceException {
        try {
            return userDao.changeDiscount(userId, discountStatusName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeUserRoleAndDiscount(Long userId, Role role, String discountStatusName) throws ServiceException {
        try {
            return userDao.changeUserRole(userId, role) && userDao.changeDiscount(userId, discountStatusName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeUserStatus(Long userId, UserStatus userStatus) throws ServiceException {
        try {
            return userDao.changeUserStatus(userId, userStatus);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteUser(Long id) throws ServiceException {
        try {
            return userDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
