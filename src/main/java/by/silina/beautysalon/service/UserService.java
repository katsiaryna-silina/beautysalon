package by.silina.beautysalon.service;

import by.silina.beautysalon.dto.UserLoginDto;
import by.silina.beautysalon.dto.UserRegistrationDto;
import by.silina.beautysalon.entity.User;
import by.silina.beautysalon.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(UserLoginDto userLoginDto) throws ServiceException;

    Map<String, String> addUser(UserRegistrationDto userRegistrationDto) throws ServiceException;
}
