package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.AttributeAndParameterName;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.model.entity.OrderFeedback;
import by.silina.beautysalon.model.entity.User;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.jdbc.MockResultSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.NEW_FEEDBACK;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.NEW_MARK;
import static by.silina.beautysalon.dao.TableColumnName.*;


/**
 * The OrderFeedbackMapperImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderFeedbackMapperImplTest {
    OrderFeedbackMapperImpl orderFeedbackMapper = OrderFeedbackMapperImpl.getInstance();
    @Mock
    SessionRequestContent sessionRequestContent;

    /**
     * Tests mapping ResultSet to OrderFeedback entity.
     */
    @Test
    void resultSetToEntity() throws SQLException {
        var expectedFeedback = OrderFeedback.builder()
                .id(1L)
                .mark((byte) 1)
                .feedback("F B.")
                .isVerified(false)
                .build();

        //create MockResultSet
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<Byte> columnMark = DSL.field(MARK, SQLDataType.TINYINT);
        Field<String> columnFeedback = DSL.field(FEEDBACK, SQLDataType.VARCHAR);
        Field<Boolean> columnIsVerified = DSL.field(IS_VERIFIED, SQLDataType.BOOLEAN);

        var result = context.newResult(columnId, columnMark, columnFeedback, columnIsVerified);
        result.add(context.newRecord(columnId, columnMark, columnFeedback, columnIsVerified).values(expectedFeedback.getId(), expectedFeedback.getMark(), expectedFeedback.getFeedback(), expectedFeedback.isVerified()));
        ResultSet mockResultSet = new MockResultSet(result);
        mockResultSet.next();

        var actualResult = orderFeedbackMapper.toEntity(mockResultSet);
        Assertions.assertEquals(expectedFeedback, actualResult);
    }

    /**
     * Tests mapping OrderFeedbackDto to OrderFeedback entity.
     */
    @Test
    void dtoToEntity() {
        var dto = OrderFeedbackDto.builder()
                .id(1L)
                .feedback("F B.")
                .mark((byte) 1)
                .isVerified(false)
                .build();

        var expectedEntity = OrderFeedback.builder()
                .id(dto.getId())
                .mark(dto.getMark())
                .feedback(dto.getFeedback())
                .build();

        var actualEntity = orderFeedbackMapper.toEntity(dto);
        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    /**
     * Tests mapping SessionRequestContent to OrderFeedbackDto.
     */
    @Test
    void sessionRequestContentToDto() {
        var feedbackId = "1";
        var feedbackText = "Some text.";
        var stringMark = "5";
        Mockito.when(sessionRequestContent.getParameterByName(AttributeAndParameterName.ID)).thenReturn(feedbackId);
        Mockito.when(sessionRequestContent.getParameterByName(NEW_FEEDBACK)).thenReturn(feedbackText);
        Mockito.when(sessionRequestContent.getParameterByName(NEW_MARK)).thenReturn(stringMark);

        var expectedDto = OrderFeedbackDto.builder()
                .id(Long.valueOf(feedbackId))
                .feedback(feedbackText)
                .mark(Byte.parseByte(stringMark))
                .build();

        var actualDto = orderFeedbackMapper.toDto(sessionRequestContent);
        Assertions.assertEquals(expectedDto, actualDto);
    }

    /**
     * Tests mapping OrderFeedback entity to OrderFeedbackDto.
     */
    @Test
    void entityToDto() {
        var entity = OrderFeedback.builder()
                .id(1L)
                .feedback("F B.")
                .mark((byte) 1)
                .isVerified(false)
                .user(User.builder().build())
                .build();

        var expectedDto = OrderFeedbackDto.builder()
                .id(entity.getId())
                .feedback(entity.getFeedback())
                .mark(entity.getMark())
                .isVerified(entity.isVerified())
                .build();

        var actualDto = orderFeedbackMapper.toDto(entity);
        Assertions.assertEquals(expectedDto, actualDto);
    }
}
