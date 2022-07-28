package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.model.dto.VisitTimeSlotDto;
import by.silina.beautysalon.model.entity.VisitTime;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.jdbc.MockResultSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The VisitTimeMapperImplTest test class.
 *
 * @author Silina Katsiaryna
 */
class VisitTimeMapperImplTest {
    VisitTimeMapperImpl visitTimeMapper = VisitTimeMapperImpl.getInstance();

    @Test
    void resultSetToEntity() throws SQLException {
        var expectedVisitTime = VisitTime.builder()
                .id(1L)
                .beginTime(LocalTime.parse("09:20:00"))
                .endTime(LocalTime.parse("09:40:00"))
                .build();

        //create MockResultSet
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<LocalTime> columnBeginTime = DSL.field(BEGIN_TIME, SQLDataType.LOCALTIME);
        Field<LocalTime> columnEndTime = DSL.field(END_TIME, SQLDataType.LOCALTIME);

        var result = context.newResult(columnId, columnBeginTime, columnEndTime);
        result.add(context.newRecord(columnId, columnBeginTime, columnEndTime)
                .values(expectedVisitTime.getId(), expectedVisitTime.getBeginTime(), expectedVisitTime.getEndTime()));
        MockResultSet mockResultSet = new MockResultSet(result);
        mockResultSet.next();

        var actualResult = visitTimeMapper.toEntity(mockResultSet);
        Assertions.assertEquals(expectedVisitTime, actualResult);
    }

    @Test
    void toDto() {
        var beginTime = LocalTime.parse("09:20:00");
        var endTime = LocalTime.parse("09:40:00");
        HashMap<Long, Long> idDurationMap = new HashMap<>();
        idDurationMap.put(1L, 20L);

        var expectedDto = VisitTimeSlotDto.builder()
                .beginTime(beginTime)
                .endTime(endTime)
                .visitTimeIdDuration(idDurationMap)
                .build();

        var actualResult = visitTimeMapper.toDto(beginTime, endTime, idDurationMap);
        Assertions.assertEquals(expectedDto, actualResult);
    }
}
