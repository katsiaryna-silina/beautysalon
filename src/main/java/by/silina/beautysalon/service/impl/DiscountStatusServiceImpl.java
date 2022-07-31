package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.DiscountStatusDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.service.DiscountStatusService;

import java.util.List;
import java.util.Optional;

/**
 * The DiscountStatusServiceImpl class that responsible for processing DiscountStatus.
 *
 * @author Silina Katsiaryna
 */
public class DiscountStatusServiceImpl implements DiscountStatusService {
    private static final DiscountStatusServiceImpl instance = new DiscountStatusServiceImpl(DiscountStatusDaoImpl.getInstance());
    private final DiscountStatusDaoImpl discountStatusDao;

    /**
     * Initializes a new DiscountStatusServiceImpl.
     */
    private DiscountStatusServiceImpl(DiscountStatusDaoImpl discountStatusDao) {
        this.discountStatusDao = discountStatusDao;
    }

    /**
     * Gets the single instance of DiscountStatusServiceImpl.
     *
     * @return DiscountStatusServiceImpl
     */
    public static DiscountStatusServiceImpl getInstance() {
        return instance;
    }

    /**
     * Finds all discount statuses.
     *
     * @return List of DiscountStatus. List of discount statuses.
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public List<DiscountStatus> findAll() throws ServiceException {
        try {
            return discountStatusDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds maximum discount status.
     *
     * @return Optional of DiscountStatus
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public Optional<DiscountStatus> findMaximum() throws ServiceException {
        try {
            return discountStatusDao.findMaximum();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
