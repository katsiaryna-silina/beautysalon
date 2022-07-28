package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.DiscountStatus;
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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The DiscountStatusDaoImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class DiscountStatusDaoImplTest {
    @InjectMocks
    DiscountStatusDaoImpl discountStatusDao;
    @Mock
    ConnectionPool mockConnectionPool;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    /**
     * Tests finding all discount statuses.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findAll() throws SQLException, DaoException {
        //create expectedResult list of DiscountStatus
        List<DiscountStatus> expectedResult = new ArrayList<>();
        var status1 = DiscountStatus.builder()
                .id(1L)
                .status("silver")
                .discount(BigDecimal.valueOf(2.0))
                .build();
        var status2 = DiscountStatus.builder()
                .id(2L)
                .status("gold")
                .discount(BigDecimal.valueOf(5.0))
                .build();
        var status3 = DiscountStatus.builder()
                .id(3L)
                .status("platinum")
                .discount(BigDecimal.valueOf(10.0))
                .build();
        expectedResult.add(status1);
        expectedResult.add(status2);
        expectedResult.add(status3);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(12));
        Field<BigDecimal> columnDiscount = DSL.field(DISCOUNT, SQLDataType.DECIMAL(4, 1));

        var result = context.newResult(columnId, columnStatus, columnDiscount);
        result.add(context.newRecord(columnId, columnStatus, columnDiscount).values(status1.getId(), status1.getStatus(), status1.getDiscount()));
        result.add(context.newRecord(columnId, columnStatus, columnDiscount).values(status2.getId(), status2.getStatus(), status2.getDiscount()));
        result.add(context.newRecord(columnId, columnStatus, columnDiscount).values(status3.getId(), status3.getStatus(), status3.getDiscount()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        List<DiscountStatus> actualResult = discountStatusDao.findAll();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding all discount statuses when DaoException occurs.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findAllWithDaoException() throws SQLException {
        var status1 = DiscountStatus.builder()
                .id(1L)
                .status("silver")
                .discount(BigDecimal.valueOf(2.0))
                .build();
        var status2 = DiscountStatus.builder()
                .id(2L)
                .status("gold")
                .discount(BigDecimal.valueOf(5.0))
                .build();
        var status3 = DiscountStatus.builder()
                .id(3L)
                .status("platinum")
                .discount(BigDecimal.valueOf(10.0))
                .build();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(12));
        Field<BigDecimal> columnDiscount = DSL.field("NON_EXISTENT_COLUMN", SQLDataType.DECIMAL(4, 1));

        var result = context.newResult(columnId, columnStatus, columnDiscount);
        result.add(context.newRecord(columnId, columnStatus, columnDiscount).values(status1.getId(), status1.getStatus(), status1.getDiscount()));
        result.add(context.newRecord(columnId, columnStatus, columnDiscount).values(status2.getId(), status2.getStatus(), status2.getDiscount()));
        result.add(context.newRecord(columnId, columnStatus, columnDiscount).values(status3.getId(), status3.getStatus(), status3.getDiscount()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        var exception = Assertions.assertThrows((DaoException.class), () -> discountStatusDao.findAll());

        var expectedMessage = "Unknown column label";
        var actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests inserting new discount status method.
     */
    @Test
    void insert() {
        var discountStatus = DiscountStatus.builder().build();
        var exception = Assertions.assertThrows((UnsupportedOperationException.class), () -> discountStatusDao.insert(discountStatus));

        var expectedMessage = "Method insert() is unsupported.";
        var actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests finding maximum discount status method.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findMaximum() throws SQLException, DaoException {
        //create expectedResult
        var status = DiscountStatus.builder()
                .status("platinum")
                .build();
        Optional<DiscountStatus> expectedResult = Optional.of(status);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(12));

        var result = context.newResult(columnStatus);
        result.add(context.newRecord(columnStatus).values(status.getStatus()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        Optional<DiscountStatus> actualResult = discountStatusDao.findMaximum();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding maximum discount status method when DaoException occurs.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findMaximumWithDaoException() throws SQLException {
        var status = DiscountStatus.builder()
                .status("platinum")
                .build();

        //create MockResultSet to replace a result of executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<String> columnStatus = DSL.field("NON_EXISTENT_COLUMN", SQLDataType.VARCHAR(12));

        var result = context.newResult(columnStatus);
        result.add(context.newRecord(columnStatus).values(status.getStatus()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        var exception = Assertions.assertThrows((DaoException.class), () -> discountStatusDao.findMaximum());
        var expectedMessage = "Unknown column label";
        var actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
