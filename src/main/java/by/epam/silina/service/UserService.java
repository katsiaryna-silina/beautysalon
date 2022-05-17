package by.epam.silina.service;

import by.epam.silina.entity.User;
import by.epam.silina.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsernameAndPassword(String login, String password) throws SecurityException, ServiceException;
}
