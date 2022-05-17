package by.epam.silina.service.impl;

import by.epam.silina.dao.impl.UserDaoImpl;
import by.epam.silina.entity.User;
import by.epam.silina.exception.DaoException;
import by.epam.silina.exception.ServiceException;
import by.epam.silina.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws ServiceException {
        //validate login, pass + md5
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = userDao.findUserByUsernameAndPassword(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }
}
