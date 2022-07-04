package by.silina.beautysalon.dao;

import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.VisitTime;

import java.time.LocalDate;
import java.util.List;

public interface VisitTimeDao {
    List<VisitTime> findFreeVisitTimeSlots(LocalDate visitDate) throws DaoException;
}
