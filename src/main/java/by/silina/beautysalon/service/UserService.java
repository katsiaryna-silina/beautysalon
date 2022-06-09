package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(UserLoginDto userLoginDto) throws ServiceException;

    Map<String, String> addUser(UserRegistrationDto userRegistrationDto) throws ServiceException;
}
