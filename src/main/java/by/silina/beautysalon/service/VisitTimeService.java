package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.VisitTimeSlotDto;

import java.time.LocalDate;
import java.util.List;

/**
 * The VisitTimeService interface.
 *
 * @author Silina Katsiaryna
 */
public interface VisitTimeService {

    /**
     * Finds visit time slots and maps them to dtos.
     *
     * @param visitDate     LocalDate. Date of visit.
     * @param neededMinutes Integer. Needed time for services in minutes.
     * @return List of VisitTimeSlotDto
     * @throws ServiceException if a service exception occurs.
     */
    List<VisitTimeSlotDto> getVisitTimeSlotDtos(LocalDate visitDate, Integer neededMinutes) throws ServiceException;

    /**
     * Checks if visit time slots free for the date.
     *
     * @param visitDate    LocalDate. Date of the visit.
     * @param visitTimeIds List of VisitTime id.
     * @return boolean.  True if time slots are free; false otherwise.
     * @throws ServiceException if a dao exception occurs.
     */
    boolean isVisitTimeSlotFree(LocalDate visitDate, List<Long> visitTimeIds) throws ServiceException;
}
