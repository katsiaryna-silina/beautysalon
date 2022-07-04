package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.VisitTimeSlotDto;

import java.time.LocalDate;
import java.util.List;

public interface VisitTimeService {
    List<VisitTimeSlotDto> getVisitTimeSlotDtos(LocalDate visitDate, Integer neededMinutes) throws ServiceException;
}
