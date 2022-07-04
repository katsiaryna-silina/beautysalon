package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.ServDao;
import by.silina.beautysalon.dao.impl.ServDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.Serv;
import by.silina.beautysalon.service.ServService;

import java.util.List;
import java.util.Optional;

public class ServServiceImpl implements ServService {
    private static final ServServiceImpl instance = new ServServiceImpl();
    private static final ServDao serviceDao = ServDaoImpl.getInstance();

    private ServServiceImpl() {
    }

    public static ServServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Serv> findComplexServices() throws ServiceException {
        try {
            return serviceDao.findServices(true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serv> findNotComplexServices() throws ServiceException {
        try {
            return serviceDao.findServices(false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Serv> findServiceByName(String name) throws ServiceException {
        try {
            return serviceDao.findServiceByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
