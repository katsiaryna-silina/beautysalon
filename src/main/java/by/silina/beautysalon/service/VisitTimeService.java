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
}
