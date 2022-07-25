package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.DiscountStatus;

import java.util.List;
import java.util.Optional;

/**
 * The DiscountStatusService interface.
 *
 * @author Silina Katsiaryna
 */
public interface DiscountStatusService {

    /**
     * The DiscountStatusServiceImpl class that responsible for processing DiscountStatus.
     *
     * @author Silina Katsiaryna
     */
    List<DiscountStatus> findAll() throws ServiceException;

    /**
     * Finds maximum discount status.
     *
     * @return Optional of DiscountStatus
     * @throws ServiceException if a service exception occurs.
     */
    Optional<DiscountStatus> findMaximum() throws ServiceException;
}
