package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.OrderFeedback;
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
import java.util.Optional;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The OrderFeedbackDaoImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderFeedbackDaoImplTest {
    @InjectMocks
    OrderFeedbackDaoImpl orderFeedbackDao;
    @Mock
    ConnectionPool mockConnectionPool;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    /**
     * Tests inserting new order feedback method.
     */
    @Test
    void insert() {
        var feedback = OrderFeedback.builder().build();
        var exception = Assertions.assertThrows((UnsupportedOperationException.class), () -> orderFeedbackDao.insert(feedback));

        var expectedMessage = "Method insert() is unsupported.";
        var actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests updating feedback method.
     */
    @Test
    void update() throws SQLException, DaoException {
        var initFeedback = OrderFeedback.builder()
                .id(1L)
                .mark((byte) 1)
                .feedback("F B.")
                .isVerified(false)
                .build();
        var updatedFeedback = OrderFeedback.builder()
                .id(1L)
                .mark((byte) 3)
                .feedback("AAA.")
                .isVerified(false)
                .build();
        Assertions.assertNotEquals(initFeedback.getMark(), updatedFeedback.getMark());
        Assertions.assertNotEquals(initFeedback.getFeedback(), updatedFeedback.getFeedback());

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

        //create rowCountDML to replace a result of executeUpdate()
        int rowCountDML = 1;
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            initFeedback.setFeedback(updatedFeedback.getFeedback());
            initFeedback.setMark(updatedFeedback.getMark());
            return rowCountDML;
        });

        boolean actualResult = orderFeedbackDao.update(initFeedback);
        Assertions.assertTrue(actualResult);

        Assertions.assertEquals(initFeedback.getMark(), updatedFeedback.getMark());
        Assertions.assertEquals(initFeedback.getFeedback(), updatedFeedback.getFeedback());
    }

    /**
     * Tests finding feedback by id method.
     */
    @Test
    void findById() throws SQLException, DaoException {
        var feedback = OrderFeedback.builder()
                .id(1L)
                .mark((byte) 1)
                .feedback("F B.")
                .isVerified(false)
                .build();
        var expectedResult = Optional.of(feedback);

        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<Byte> columnMark = DSL.field(MARK, SQLDataType.TINYINT);
        Field<String> columnFeedback = DSL.field(FEEDBACK, SQLDataType.VARCHAR);
        Field<Boolean> columnIsVerified = DSL.field(IS_VERIFIED, SQLDataType.BOOLEAN);

        var result = context.newResult(columnId, columnMark, columnFeedback, columnIsVerified);
        result.add(context.newRecord(columnId, columnMark, columnFeedback, columnIsVerified).values(feedback.getId(), feedback.getMark(), feedback.getFeedback(), feedback.isVerified()));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        var actualResult = orderFeedbackDao.findById(feedback.getId());
        Assertions.assertEquals(expectedResult, actualResult);
    }
}