package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.model.entity.Role;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The OrderStatusDaoImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderStatusDaoImplTest {
    @InjectMocks
    OrderStatusDaoImpl orderStatusDao;
    @Mock
    ConnectionPool mockConnectionPool;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    /**
     * Tests finding all order statuses.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findAll() throws SQLException, DaoException {
        //create expectedResult list of OrderStatus
        var orderStatus1 = OrderStatus.builder()
                .id(1L)
                .status("status1")
                .description("desc1")
                .accessibleTo(Role.CLIENT)
                .build();
        var orderStatus2 = OrderStatus.builder()
                .id(2L)
                .status("status2")
                .description("desc2")
                .accessibleTo(Role.ADMIN)
                .build();
        var expectedResult = List.of(orderStatus1, orderStatus2);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(30));
        Field<String> columnDescription = DSL.field(DESCRIPTION, SQLDataType.VARCHAR(60));
        Field<String> columnAccessibleToRole = DSL.field(ROLE, SQLDataType.VARCHAR(10));

        var result = context.newResult(columnId, columnStatus, columnDescription, columnAccessibleToRole);
        result.add(context.newRecord(columnId, columnStatus, columnDescription, columnAccessibleToRole)
                .values(orderStatus1.getId(), orderStatus1.getStatus(), orderStatus1.getDescription(), orderStatus1.getAccessibleTo().name()));
        result.add(context.newRecord(columnId, columnStatus, columnDescription, columnAccessibleToRole)
                .values(orderStatus2.getId(), orderStatus2.getStatus(), orderStatus2.getDescription(), orderStatus2.getAccessibleTo().name()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        List<OrderStatus> actualResult = orderStatusDao.findAll();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests inserting new order status method.
     */
    @Test
    void insert() {
        var orderStatus = OrderStatus.builder().build();
        var exception = Assertions.assertThrows((UnsupportedOperationException.class), () -> orderStatusDao.insert(orderStatus));

        var expectedMessage = "Method insert() is unsupported.";
        var actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests finding order status by its name method.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findByName() throws SQLException, DaoException {
        //create expectedResult
        var orderStatus = OrderStatus.builder()
                .id(1L)
                .status("status1")
                .description("desc1")
                .accessibleTo(Role.CLIENT)
                .build();
        var expectedResult = Optional.of(orderStatus);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(30));
        Field<String> columnDescription = DSL.field(DESCRIPTION, SQLDataType.VARCHAR(60));
        Field<String> columnAccessibleToRole = DSL.field(ROLE, SQLDataType.VARCHAR(10));

        var result = context.newResult(columnId, columnStatus, columnDescription, columnAccessibleToRole);
        result.add(context.newRecord(columnId, columnStatus, columnDescription, columnAccessibleToRole)
                .values(orderStatus.getId(), orderStatus.getStatus(), orderStatus.getDescription(), orderStatus.getAccessibleTo().name()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        var actualResult = orderStatusDao.findByName(orderStatus.getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding default order status method.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findDefault() throws SQLException, DaoException {
        //create expectedResult
        var orderStatus = OrderStatus.builder()
                .id(1L)
                .status("status1")
                .description("desc1")
                .accessibleTo(Role.CLIENT)
                .build();
        var expectedResult = Optional.of(orderStatus);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(30));
        Field<String> columnDescription = DSL.field(DESCRIPTION, SQLDataType.VARCHAR(60));
        Field<String> columnAccessibleToRole = DSL.field(ROLE, SQLDataType.VARCHAR(10));

        var result = context.newResult(columnId, columnStatus, columnDescription, columnAccessibleToRole);
        result.add(context.newRecord(columnId, columnStatus, columnDescription, columnAccessibleToRole)
                .values(orderStatus.getId(), orderStatus.getStatus(), orderStatus.getDescription(), orderStatus.getAccessibleTo().name()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        var actualResult = orderStatusDao.findDefault();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
