package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.dto.VisitTimeSlotDto;
import by.silina.beautysalon.model.entity.VisitTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * The VisitTimeMapperImpl class responsible for mapping VisitTime.
 *
 * @author Silina Katsiaryna
 */
public interface VisitTimeMapper {

    /**
     * Maps passed ResultSet to VisitTime entity.
     *
     * @param resultSet ResultSet
     * @return VisitTime
     * @throws SQLException if a sql exception occurs.
     */
    VisitTime toEntity(ResultSet resultSet) throws SQLException;

    /**
     * Maps passed SessionRequestContent to OrderFeedbackDto.
     *
     * @param beginTime     LocalTime
     * @param endTime       LocalTime
     * @param idDurationMap HashMap
     * @return VisitTimeSlotDto
     */
    VisitTimeSlotDto toDto(LocalTime beginTime, LocalTime endTime, HashMap<Long, Long> idDurationMap);
}
