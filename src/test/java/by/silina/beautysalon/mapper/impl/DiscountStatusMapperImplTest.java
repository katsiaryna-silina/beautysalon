package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.model.entity.DiscountStatus;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.jdbc.MockResultSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The DiscountStatusMapperImplTest test class.
 *
 * @author Silina Katsiaryna
 */
class DiscountStatusMapperImplTest {
    DiscountStatusMapperImpl discountStatusMapper = DiscountStatusMapperImpl.getInstance();

    /**
     * Tests mapping ResultSet to DiscountStatus entity.
     */
    @Test
    void resultSetToEntity() throws SQLException {
        var expectedStatus = DiscountStatus.builder()
                .id(1L)
                .status("silver")
                .discount(BigDecimal.valueOf(2.0))
                .build();

        //create MockResultSet
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnStatus = DSL.field(STATUS, SQLDataType.VARCHAR(12));
        Field<BigDecimal> columnDiscount = DSL.field(DISCOUNT, SQLDataType.DECIMAL(4, 1));

        var result = context.newResult(columnId, columnStatus, columnDiscount);
        result.add(context.newRecord(columnId, columnStatus, columnDiscount).values(expectedStatus.getId(), expectedStatus.getStatus(), expectedStatus.getDiscount()));
        ResultSet mockResultSet = new MockResultSet(result);
        mockResultSet.next();

        var actualResult = discountStatusMapper.toEntity(mockResultSet);
        Assertions.assertEquals(expectedStatus, actualResult);
    }
}
