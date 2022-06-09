package by.epam.silina.service;

import by.epam.silina.dto.UserLoginDto;
import by.epam.silina.dto.UserRegistrationDto;
import by.epam.silina.entity.User;
import by.epam.silina.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(UserLoginDto userLoginDto) throws ServiceException;

    Map<String, String> addUser(UserRegistrationDto userRegistrationDto) throws ServiceException;
}
