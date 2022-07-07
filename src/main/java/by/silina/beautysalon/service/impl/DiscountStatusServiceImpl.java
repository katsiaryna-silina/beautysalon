package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.DiscountStatusDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.service.DiscountStatusService;

import java.util.List;
import java.util.Optional;

public class DiscountStatusServiceImpl implements DiscountStatusService {
    private static final DiscountStatusServiceImpl instance = new DiscountStatusServiceImpl();
    private final DiscountStatusDaoImpl discountStatusDao = DiscountStatusDaoImpl.getInstance();

    private DiscountStatusServiceImpl() {
    }

    public static DiscountStatusServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<DiscountStatus> findAll() throws ServiceException {
        try {
            return discountStatusDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<DiscountStatus> findMaximum() throws ServiceException {
        try {
            return discountStatusDao.findMaximum();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
