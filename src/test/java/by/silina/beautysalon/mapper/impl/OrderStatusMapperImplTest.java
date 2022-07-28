package by.silina.beautysalon.mapper.impl;

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

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The OrderStatusDaoImplTest test class.
 *
 * @author Silina Katsiaryna
 */
class OrderStatusMapperImplTest {
    OrderStatusMapperImpl orderStatusMapper = OrderStatusMapperImpl.getInstance();

    /**
     * Tests mapping ResultSet to OrderStatus entity.
     */
    @Test
    void resultSetToEntity() throws SQLException {
        var expectedOrderStatus = OrderStatus.builder()
                .id(1L)
                .status("status1")
                .description("desc1")
                .accessibleTo(Role.CLIENT)
                .build();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(30));
        Field<String> columnDescription = DSL.field(DESCRIPTION, SQLDataType.VARCHAR(60));
        Field<String> columnAccessibleToRole = DSL.field(ROLE, SQLDataType.VARCHAR(10));

        var result = context.newResult(columnId, columnStatus, columnDescription, columnAccessibleToRole);
        result.add(context.newRecord(columnId, columnStatus, columnDescription, columnAccessibleToRole)
                .values(expectedOrderStatus.getId(), expectedOrderStatus.getStatus(), expectedOrderStatus.getDescription(), expectedOrderStatus.getAccessibleTo().name()));
        ResultSet mockResultSet = new MockResultSet(result);
        mockResultSet.next();

        var actualEntity = orderStatusMapper.toEntity(mockResultSet);
        Assertions.assertEquals(expectedOrderStatus, actualEntity);
    }
}
