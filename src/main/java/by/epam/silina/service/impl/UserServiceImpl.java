package by.epam.silina.service.impl;

import by.epam.silina.dao.impl.UserDaoImpl;
import by.epam.silina.dto.UserLoginDto;
import by.epam.silina.dto.UserRegistrationDto;
import by.epam.silina.entity.User;
import by.epam.silina.exception.DaoException;
import by.epam.silina.exception.ServiceException;
import by.epam.silina.mapper.UserMapper;
import by.epam.silina.mapper.impl.UserMapperImpl;
import by.epam.silina.service.UserService;
import by.epam.silina.util.PasswordEncoder;
import by.epam.silina.validator.UserRegistrationDtoValidator;
import by.epam.silina.validator.UserValidator;
import by.epam.silina.validator.impl.UserRegistrationDtoValidatorImpl;
import by.epam.silina.validator.impl.UserValidatorImpl;

import java.util.Map;
import java.util.Optional;

import static by.epam.silina.controller.command.AttributeAndParameterName.EMAIL_ERROR_MESSAGE;
import static by.epam.silina.controller.command.AttributeAndParameterName.USERNAME_ERROR_MESSAGE;

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
        //todo do we need validation  here?
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
