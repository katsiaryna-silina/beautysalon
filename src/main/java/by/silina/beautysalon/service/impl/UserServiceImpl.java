package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.UserDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.util.PasswordEncoder;
import by.silina.beautysalon.validator.UserRegistrationDtoValidator;
import by.silina.beautysalon.validator.UserValidator;
import by.silina.beautysalon.validator.impl.UserRegistrationDtoValidatorImpl;
import by.silina.beautysalon.validator.impl.UserValidatorImpl;

import java.util.Map;
import java.util.Optional;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.EMAIL_ERROR_MESSAGE;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.USERNAME_ERROR_MESSAGE;

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
                userDao.insert(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return errorMap;
    }
}
