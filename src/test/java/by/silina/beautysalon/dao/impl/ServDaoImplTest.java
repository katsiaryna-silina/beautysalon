package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.Serv;
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

@ExtendWith(MockitoExtension.class)
class ServDaoImplTest {
    @InjectMocks
    ServDaoImpl servDao;
    @Mock
    ConnectionPool mockConnectionPool;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    /**
     * Tests finding all services.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findServices() throws SQLException, DaoException {
        //create expectedResult list of services
        var service1 = createService1();
        var service2 = createService2();
        var expectedResult = List.of(service1, service2);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        var mockResultSet = createMockedResultSetWithServices(expectedResult);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        var actualResult = servDao.findServices(true);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding services by its name.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findServiceByName() throws SQLException, DaoException {
        //create expectedResult
        var service = createService1();
        var expectedResult = Optional.of(service);

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        var mockResultSet = createMockedResultSetWithServices(List.of(service));

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        var actualResult = servDao.findServiceByName(service.getName());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests number of services.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findNumberOfServices() throws SQLException, DaoException {
        //create expectedResult
        var services = List.of(createService1(), createService2());
        long expectedResult = services.size();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnNumberOfServices = DSL.field(NUMBER_OF_SERVICES, SQLDataType.BIGINT);

        var result = context.newResult(columnNumberOfServices);
        result.add(context.newRecord(columnNumberOfServices).values(expectedResult));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        long actualResult = servDao.findNumberOfServices();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding paged services.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findPagedServiceListWithAllDisplayed() throws SQLException, DaoException {
        //create expectedResult
        var allServices = List.of(createService1(), createService2());
        var fromServiceId = 1L;
        var numberOfServices = allServices.size();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            var servicesToDisplay = new ArrayList<>(allServices);
            return createMockedResultSetWithServices(servicesToDisplay);
        });

        //comparison two results
        var actualResult = servDao.findPagedServiceList(fromServiceId, numberOfServices);
        Assertions.assertEquals(allServices, actualResult);
    }

    /**
     * Tests finding paged services.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates MockResultSet in order to replace resultSet from database in PreparedStatement.executeQuery().
     */
    @Test
    void findPagedServiceListWithOneDisplayed() throws SQLException, DaoException {
        //create expectedResult
        var allServices = List.of(createService1(), createService2());
        var fromServiceId = 1L;
        var numberOfServices = 1;

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenAnswer(i -> {
            var servicesToDisplay = new ArrayList<>(allServices.subList(Math.toIntExact(fromServiceId), numberOfServices));
            return createMockedResultSetWithServices(servicesToDisplay);
        });

        //comparison two results
        var actualResult = servDao.findPagedServiceList(fromServiceId, numberOfServices);
        Assertions.assertEquals(allServices.subList(Math.toIntExact(fromServiceId), numberOfServices), actualResult);
    }

    /**
     * Tests updating service by its id method.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void updateById() throws SQLException, DaoException {
        var initialService = createService2();
        var serviceId = initialService.getId();
        var isServiceDeprecated = initialService.isDeprecated();
        Assertions.assertTrue(isServiceDeprecated);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

        //create rowCountDML to replace a result of executeUpdate()
        int rowCountDML = 1;
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            initialService.setDeprecated(false);
            return rowCountDML;
        });

        var isServiceUpdated = servDao.updateById(serviceId);
        Assertions.assertTrue(isServiceUpdated);

        isServiceDeprecated = initialService.isDeprecated();
        Assertions.assertFalse(isServiceDeprecated);
    }

    /**
     * Tests deleting the service from user's select.
     * This test can be fully covered only by using integration test.
     * Uses mockConnection, mockPreparedStatement.
     * Creates rowCountDML in order to replace a result from database in PreparedStatement.executeUpdate().
     */
    @Test
    void deleteById() throws SQLException, DaoException {
        var initialService = createService1();
        var serviceId = initialService.getId();
        var isServiceDeprecated = initialService.isDeprecated();
        Assertions.assertFalse(isServiceDeprecated);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

        //create rowCountDML to replace a result of executeUpdate()
        int rowCountDML = 1;
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            initialService.setDeprecated(true);
            return rowCountDML;
        });

        var isServiceUpdated = servDao.updateById(serviceId);
        Assertions.assertTrue(isServiceUpdated);

        isServiceDeprecated = initialService.isDeprecated();
        Assertions.assertTrue(isServiceDeprecated);
    }

    /**
     * Tests inserting new service method.
     */
    @Test
    void insert() {
        var service = Serv.builder().build();
        var exception = Assertions.assertThrows((UnsupportedOperationException.class), () -> servDao.insert(service));

        var expectedMessage = "Method insert() is unsupported.";
        var actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Creates Service.
     */
    Serv createService1() {
        return Serv.builder()
                .id(1L)
                .name("service1")
                .isComplex(false)
                .minutesNeeded(30)
                .description("service1 desc")
                .price(BigDecimal.valueOf(22.5))
                .isDeprecated(false)
                .build();
    }

    /**
     * Creates Service.
     */
    Serv createService2() {
        return Serv.builder()
                .id(2L)
                .name("service2")
                .isComplex(true)
                .minutesNeeded(60)
                .description("service2 desc")
                .price(BigDecimal.valueOf(45.7))
                .isDeprecated(true)
                .build();
    }

    /**
     * Creates MockedResultSet.
     */
    ResultSet createMockedResultSetWithServices(List<Serv> services) {
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<String> columnName = DSL.field(NAME, SQLDataType.VARCHAR(50));
        Field<String> columnIsComplex = DSL.field(IS_COMPLEX, SQLDataType.CHAR);
        Field<Integer> columnMinutesNeeded = DSL.field(MINUTES_NEEDED, SQLDataType.INTEGER);
        Field<String> columnDescription = DSL.field(DESCRIPTION, SQLDataType.VARCHAR);
        Field<BigDecimal> columnPrice = DSL.field(PRICE, SQLDataType.DECIMAL(10, 2));
        Field<String> columnIsDeprecated = DSL.field(IS_DEPRECATED, SQLDataType.CHAR);

        var result = context.newResult(columnId, columnName, columnIsComplex, columnMinutesNeeded, columnDescription, columnPrice, columnIsDeprecated);
        services.forEach(service -> {
                    if (service.isComplex() && service.isDeprecated()) {
                        result.add(context.newRecord(columnId, columnName, columnIsComplex, columnMinutesNeeded, columnDescription, columnPrice, columnIsDeprecated)
                                .values(service.getId(), service.getName(), "Y", service.getMinutesNeeded(), service.getDescription(), service.getPrice(), "Y"));
                    } else if (!service.isComplex() && service.isDeprecated()) {
                        result.add(context.newRecord(columnId, columnName, columnIsComplex, columnMinutesNeeded, columnDescription, columnPrice, columnIsDeprecated)
                                .values(service.getId(), service.getName(), "N", service.getMinutesNeeded(), service.getDescription(), service.getPrice(), "Y"));
                    } else if (service.isComplex() && !service.isDeprecated()) {
                        result.add(context.newRecord(columnId, columnName, columnIsComplex, columnMinutesNeeded, columnDescription, columnPrice, columnIsDeprecated)
                                .values(service.getId(), service.getName(), "Y", service.getMinutesNeeded(), service.getDescription(), service.getPrice(), "N"));
                    } else {
                        result.add(context.newRecord(columnId, columnName, columnIsComplex, columnMinutesNeeded, columnDescription, columnPrice, columnIsDeprecated)
                                .values(service.getId(), service.getName(), "N", service.getMinutesNeeded(), service.getDescription(), service.getPrice(), "N"));
                    }
                }
        );
        return new MockResultSet(result);
    }
}
