package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.VisitTimeMapper;
import by.silina.beautysalon.model.dto.VisitTimeSlotDto;
import by.silina.beautysalon.model.entity.VisitTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

public class VisitTimeMapperImpl implements VisitTimeMapper {
    private static final VisitTimeMapperImpl instance = new VisitTimeMapperImpl();

    private VisitTimeMapperImpl() {
    }

    public static VisitTimeMapperImpl getInstance() {
        return instance;
    }

    @Override
    public VisitTime toEntity(ResultSet resultSet) throws SQLException {
        return VisitTime.builder()
                .id(resultSet.getLong(TableColumnName.ID))
                .beginTime(LocalTime.parse(resultSet.getString(TableColumnName.BEGIN_TIME)))
                .endTime(LocalTime.parse(resultSet.getString(TableColumnName.END_TIME)))
                .build();
    }

    @Override
    public VisitTimeSlotDto toDto(LocalTime beginTime, LocalTime endTime, HashMap<Long, Long> idDurationMap) {
        return VisitTimeSlotDto.builder()
                .beginTime(beginTime)
                .endTime(endTime)
                .visitTimeIdDuration(idDurationMap)
                .build();
    }
}
