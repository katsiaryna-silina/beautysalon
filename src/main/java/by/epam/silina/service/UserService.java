package by.epam.silina.service;

import by.epam.silina.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws SecurityException, ServiceException;
}
