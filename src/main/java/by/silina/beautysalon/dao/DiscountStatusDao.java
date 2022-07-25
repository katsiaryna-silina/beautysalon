package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.DiscountStatus;

import java.util.Optional;

/**
 * The DiscountStatusDao interface.
 *
 * @author Silina Katsiaryna
 */
public interface DiscountStatusDao {

    /**
     * Finds maximum discount status.
     *
     * @return Optional of DiscountStatus.
     * @throws DaoException the dao exception
     */
    Optional<DiscountStatus> findMaximum() throws DaoException;
}
