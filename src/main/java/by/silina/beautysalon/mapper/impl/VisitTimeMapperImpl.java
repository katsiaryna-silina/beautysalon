package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.VisitTimeMapper;
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
public class VisitTimeMapperImpl implements VisitTimeMapper {
    private static final VisitTimeMapperImpl instance = new VisitTimeMapperImpl();

    /**
     * Initializes a new VisitTimeMapperImpl.
     */
    private VisitTimeMapperImpl() {
    }

    /**
     * Gets the single instance of VisitTimeMapperImpl.
     *
     * @return VisitTimeMapperImpl
     */
    public static VisitTimeMapperImpl getInstance() {
        return instance;
    }

    /**
     * Maps passed ResultSet to VisitTime entity.
     *
     * @param resultSet ResultSet
     * @return VisitTime
     * @throws SQLException if a sql exception occurs.
     */
    @Override
    public VisitTime toEntity(ResultSet resultSet) throws SQLException {
        return VisitTime.builder()
                .id(resultSet.getLong(TableColumnName.ID))
                .beginTime(resultSet.getTime(TableColumnName.BEGIN_TIME).toLocalTime())
                .endTime(resultSet.getTime(TableColumnName.END_TIME).toLocalTime())
                .build();
    }

    /**
     * Maps passed SessionRequestContent to OrderFeedbackDto.
     *
     * @param beginTime     LocalTime
     * @param endTime       LocalTime
     * @param idDurationMap HashMap
     * @return VisitTimeSlotDto
     */
    @Override
    public VisitTimeSlotDto toDto(LocalTime beginTime, LocalTime endTime, HashMap<Long, Long> idDurationMap) {
        return VisitTimeSlotDto.builder()
                .beginTime(beginTime)
                .endTime(endTime)
                .visitTimeIdDuration(idDurationMap)
                .build();
    }
}
