package by.epam.silina.service.impl;

import by.epam.silina.dao.impl.UserDaoImpl;
import by.epam.silina.exception.DaoException;
import by.epam.silina.exception.ServiceException;
import by.epam.silina.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        //validate login, pass + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        boolean match = false;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
