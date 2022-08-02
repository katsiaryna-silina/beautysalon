package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.VisitTime;

import java.time.LocalDate;
import java.util.List;

/**
 * The VisitTimeDao interface.
 *
 * @author Silina Katsiaryna
 */
public interface VisitTimeDao {

    /**
     * Finds free visit time slots for the date.
     *
     * @param visitDate LocalDate. Date of the visit.
     * @return List of VisitTime
     * @throws DaoException if a dao exception occurs.
     */
    List<VisitTime> findFreeVisitTimeSlots(LocalDate visitDate) throws DaoException;

    /**
     * Checks if visit time slots free for the date.
     *
     * @param visitDate    LocalDate. Date of the visit.
     * @param visitTimeIds List of VisitTime id.
     * @return boolean.  True if time slots are free; false otherwise.
     * @throws DaoException if a dao exception occurs.
     */
    boolean isVisitTimeSlotFree(LocalDate visitDate, List<Long> visitTimeIds) throws DaoException;
}
