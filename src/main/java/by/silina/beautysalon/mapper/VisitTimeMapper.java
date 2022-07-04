package by.silina.beautysalon.mapper;

import by.silina.beautysalon.model.dto.VisitTimeSlotDto;
import by.silina.beautysalon.model.entity.VisitTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

public interface VisitTimeMapper {
    VisitTime toEntity(ResultSet resultSet) throws SQLException;

    VisitTimeSlotDto toDto(LocalTime beginTime, LocalTime endTime, HashMap<Long, Long> idDurationMap);
}
