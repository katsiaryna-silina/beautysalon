package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.entity.Serv;
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
 * The ServiceMapperImplTest test class.
 *
 * @author Silina Katsiaryna
 */
class ServiceMapperImplTest {
    ServiceMapperImpl serviceMapper = ServiceMapperImpl.getInstance();

    /**
     * Tests mapping ResultSet to Service entity.
     */
    @Test
    void resultSetToEntity() throws SQLException {
        var expectedService = Serv.builder()
                .id(1L)
                .name("service1")
                .isComplex(false)
                .minutesNeeded(30)
                .description("service1 desc")
                .price(BigDecimal.valueOf(22.5))
                .isDeprecated(false)
                .build();

        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnName = DSL.field(NAME, SQLDataType.VARCHAR(50));
        Field<String> columnIsComplex = DSL.field(IS_COMPLEX, SQLDataType.CHAR);
        Field<Integer> columnMinutesNeeded = DSL.field(MINUTES_NEEDED, SQLDataType.INTEGER);
        Field<String> columnDescription = DSL.field(DESCRIPTION, SQLDataType.VARCHAR);
        Field<BigDecimal> columnPrice = DSL.field(PRICE, SQLDataType.DECIMAL(10, 2));
        Field<String> columnIsDeprecated = DSL.field(IS_DEPRECATED, SQLDataType.CHAR);

        var result = context.newResult(columnId, columnName, columnIsComplex, columnMinutesNeeded, columnDescription, columnPrice, columnIsDeprecated);
        result.add(context.newRecord(columnId, columnName, columnIsComplex, columnMinutesNeeded, columnDescription, columnPrice, columnIsDeprecated)
                .values(expectedService.getId(), expectedService.getName(), "N", expectedService.getMinutesNeeded(), expectedService.getDescription(), expectedService.getPrice(), "N"));
        ResultSet mockResultSet = new MockResultSet(result);
        mockResultSet.next();

        var actualResult = serviceMapper.toEntity(mockResultSet);
        Assertions.assertEquals(expectedService, actualResult);
    }

    /**
     * Tests mapping Service entity to ServiceDto.
     */
    @Test
    void entityToDto() {
        var entity = Serv.builder()
                .id(1L)
                .name("service1")
                .isComplex(false)
                .minutesNeeded(30)
                .description("service1 desc")
                .price(BigDecimal.valueOf(22.5))
                .isDeprecated(false)
                .build();

        var expectedDto = ServiceDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isComplex(entity.isComplex())
                .price(entity.getPrice())
                .isDeprecated(entity.isDeprecated())
                .minutesNeeded(entity.getMinutesNeeded())
                .build();

        var actualDto = serviceMapper.toDto(entity);
        Assertions.assertEquals(expectedDto, actualDto);
    }
}
