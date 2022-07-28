package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.VisitTime;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.jdbc.MockResultSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The VisitTimeDaoImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class VisitTimeDaoImplTest {
    @InjectMocks
    VisitTimeDaoImpl visitTimeDao;
    @Mock
    ConnectionPool mockConnectionPool;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    /**
     * Tests finding free visit time slots.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findFreeVisitTimeSlots() throws SQLException, DaoException {
        var visitTime = VisitTime.builder()
                .id(1L)
                .beginTime(LocalTime.parse("09:20:00"))
                .endTime(LocalTime.parse("09:40:00"))
                .build();
        var expectedResult = List.of(visitTime);

        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<LocalTime> columnBeginTime = DSL.field(BEGIN_TIME, SQLDataType.LOCALTIME);
        Field<LocalTime> columnEndTime = DSL.field(END_TIME, SQLDataType.LOCALTIME);

        var result = context.newResult(columnId, columnBeginTime, columnEndTime);
        result.add(context.newRecord(columnId, columnBeginTime, columnEndTime)
                .values(visitTime.getId(), visitTime.getBeginTime(), visitTime.getEndTime()));
        MockResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        List<VisitTime> actualResult = visitTimeDao.findFreeVisitTimeSlots(LocalDate.now());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests inserting new visit time method.
     */
    @Test
    void insert() {
        var visitTime = VisitTime.builder().build();
        var exception = Assertions.assertThrows((UnsupportedOperationException.class), () -> visitTimeDao.insert(visitTime));

        var expectedMessage = "Method insert() is unsupported.";
        var actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
