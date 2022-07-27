package by.silina.beautysalon.dao.impl;

import by.silina.beautysalon.connection.ConnectionPool;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.model.entity.*;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static by.silina.beautysalon.dao.TableColumnName.*;


/**
 * The OrderDaoImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderDaoImplTest {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @InjectMocks
    OrderDaoImpl orderDao;
    @Mock
    ConnectionPool mockConnectionPool;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    PreparedStatement mockSelectOrderId;
    @Mock
    PreparedStatement mockInsertFeedback;
    @Mock
    PreparedStatement mockInsertOrder;
    @Mock
    PreparedStatement mockInsertOrdersServices;
    @Mock
    PreparedStatement mockInsertOrdersVisitTimes;

    /**
     * Tests inserting new order method.
     */
    @Test
    void insert() throws DaoException, SQLException {
        //create MockResultSet to replace a result of mockSelectOrderId.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnOrderId = DSL.field(ORDER_ID, SQLDataType.BIGINT);

        var result = context.newResult(columnOrderId);
        result.add(context.newRecord(columnOrderId).values(20L));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.contains("SELECT NEXTVAL('ORDERS') AS ORDER_ID"))).thenReturn(mockSelectOrderId);
        Mockito.when(mockSelectOrderId.executeQuery()).thenReturn(mockResultSet);

        //create rowCountDML to replace a result of mockInsertFeedback.executeUpdate() and mockInsertOrder.executeUpdate()
        int rowCountDML = 1;
        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDER_FEEDBACKS"))).thenReturn(mockInsertFeedback);
        Mockito.when(mockInsertFeedback.executeUpdate()).thenReturn(rowCountDML);

        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDERS"))).thenReturn(mockInsertOrder);
        Mockito.when(mockInsertOrder.executeUpdate()).thenReturn(rowCountDML);

        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDERS_SERVICES"))).thenReturn(mockInsertOrdersServices);
        Mockito.when(mockInsertOrdersServices.executeUpdate()).thenReturn(rowCountDML);

        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDERS_VISIT_TIMES"))).thenReturn(mockInsertOrdersVisitTimes);
        Mockito.when(mockInsertOrdersVisitTimes.executeUpdate()).thenReturn(rowCountDML);

        //comparison two results
        boolean actualResult = orderDao.insert(createOrderForInsert());
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests inserting new order method when DaoException occurs.
     */
    @Test
    void insertWithException() throws SQLException {
        //create MockResultSet to replace a result of mockSelectOrderId.executeQuery()
        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnOrderId = DSL.field(ORDER_ID, SQLDataType.BIGINT);

        var result = context.newResult(columnOrderId);
        result.add(context.newRecord(columnOrderId).values(20L));
        ResultSet mockResultSet = new MockResultSet(result);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.contains("SELECT NEXTVAL('ORDERS') AS ORDER_ID"))).thenReturn(mockSelectOrderId);
        Mockito.when(mockSelectOrderId.executeQuery()).thenReturn(mockResultSet);

        //create rowCountDML to replace a result of executeUpdate()
        int rowCountDML = 1;
        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDER_FEEDBACKS"))).thenReturn(mockInsertFeedback);
        Mockito.when(mockInsertFeedback.executeUpdate()).thenReturn(rowCountDML);

        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDERS"))).thenReturn(mockInsertOrder);
        Mockito.when(mockInsertOrder.executeUpdate()).thenReturn(rowCountDML);

        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDERS_SERVICES"))).thenReturn(mockInsertOrdersServices);
        Mockito.when(mockInsertOrdersServices.executeUpdate()).thenReturn(rowCountDML);

        //create invalidRowCountDML to replace a result of executeUpdate()
        int invalidRowCountDML = 2;
        Mockito.when(mockConnection.prepareStatement(Mockito.contains("INSERT INTO ORDERS_VISIT_TIMES"))).thenReturn(mockInsertOrdersVisitTimes);
        Mockito.when(mockInsertOrdersVisitTimes.executeUpdate()).thenReturn(invalidRowCountDML);

        var exception = Assertions.assertThrows((DaoException.class), () -> orderDao.insert(createOrderForInsert()));

        var expectedMessage = "Cannot update ORDERS_VISIT_TIMES table";
        var actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests finding order by order id.
     */
    @Test
    void findById() throws SQLException, DaoException {
        //create expectedResult
        Optional<Order> expectedResult = Optional.of(createOrderWithoutTimeSlots1());
        Long orderId = 3L;

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        ResultSet mockResultSet = createMockedResultSetWithOrders(List.of(createOrderWithoutTimeSlots1()));

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        Optional<Order> actualResult = orderDao.findById(orderId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding number of all orders.
     */
    @Test
    void findNumberOfAllOrders() throws SQLException, DaoException {
        //create expectedResult
        List<Order> orders = List.of(createOrderWithTimeSlots1(), createOrderWithTimeSlots2());
        long expectedResult = orders.size();

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        ResultSet mockResultSet = createMockedResultSetOrdersNumber(orders);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        long actualResult = orderDao.findNumberOfOrders();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding number of user's orders.
     */
    @Test
    void findNumberOfUserOrders() throws SQLException, DaoException {
        //create expectedResult
        List<Order> orders = List.of(createOrderWithTimeSlots1());
        long expectedResult = orders.size();
        Assertions.assertEquals(1, expectedResult);
        var userId = 1L;

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        ResultSet mockResultSet = createMockedResultSetOrdersNumber(orders);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        long actualResult = orderDao.findNumberOfOrders(userId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding all paged orders.
     */
    @Test
    void findPagedAllOrders() throws SQLException, DaoException {
        //create expectedResult
        List<Order> expectedResult = List.of(createOrderWithoutTimeSlots1(), createOrderWithoutTimeSlots2());
        Long fromOrderId = 1L;
        Integer numberOfOrders = 5;

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        ResultSet mockResultSet = createMockedResultSetWithOrders(expectedResult);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        List<Order> actualResult = orderDao.findPagedOrders(fromOrderId, numberOfOrders);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding user's paged orders.
     */
    @Test
    void findPagedUserOrders() throws SQLException, DaoException {
        //create expectedResult
        List<Order> expectedResult = List.of(createOrderWithoutTimeSlots1(), createOrderWithoutTimeSlots2());
        Long fromOrderId = 1L;
        Integer numberOfOrders = 5;
        Long userId = 1L;

        //create MockResultSet to replace a result of mockPreparedStatement.executeQuery()
        ResultSet mockResultSet = createMockedResultSetWithOrders(expectedResult);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //comparison two results
        List<Order> actualResult = orderDao.findPagedOrders(fromOrderId, numberOfOrders, userId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests changing order status.
     */
    @Test
    void changeStatus() throws SQLException, DaoException {
        //create expectedResult
        var order = createOrderWithTimeSlots1();
        var orderId = order.getId();
        var currentOrderStatus = order.getOrderStatus();
        var newOrderStatus = OrderStatus.builder()
                .status("new status")
                .description("New status.")
                .build();
        Assertions.assertNotEquals(currentOrderStatus, newOrderStatus);

        Mockito.when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

        //create rowCountDML to replace a result of executeUpdate()
        var rowCountDML = 1;
        Mockito.when(mockPreparedStatement.executeUpdate()).thenAnswer(i -> {
            order.setOrderStatus(newOrderStatus);
            return rowCountDML;
        });

        //comparison two results
        boolean actualResult = orderDao.changeStatus(orderId, newOrderStatus.getStatus());
        Assertions.assertTrue(actualResult);
        Assertions.assertEquals(newOrderStatus, order.getOrderStatus());
    }

    /**
     * Creates order status.
     */
    OrderStatus createOrderStatus() {
        return OrderStatus.builder()
                .status("waiting")
                .description("Waiting for confirmation.")
                .build();
    }

    /**
     * Creates order.
     */
    Order createOrderForInsert() {
        return Order.builder()
                .id(3L)
                .orderDateTime(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:55:00"))
                .priceWithDiscount(BigDecimal.valueOf(76.30))
                .orderStatus(createOrderStatus())
                .user(User.builder()
                        .id(1L)
                        .username("client")
                        .firstName("Cli")
                        .lastName("Ent")
                        .email("client@gmail.com")
                        .phoneNumber("+1238882")
                        .build())
                .services(List.of(
                        Serv.builder()
                                .id(1L)
                                .name("serv name1")
                                .build(),
                        Serv.builder()
                                .id(2L)
                                .name("serv name2")
                                .build()
                ))
                .timeSlots(List.of(VisitTime.builder().id(2L).build()))
                .build();
    }


    /**
     * Creates service.
     */
    Serv createService1() {
        return Serv.builder()
                .name("serv name1")
                .build();
    }


    /**
     * Creates service.
     */
    Serv createService2() {
        return Serv.builder()
                .name("serv name2")
                .build();
    }

    /**
     * Creates order.
     */
    Order createOrderWithTimeSlots1() {
        return Order.builder()
                .id(1L)
                .orderDateTime(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("09:20:00"))
                .visitEndTime(LocalTime.parse("09:55:00"))
                .priceWithDiscount(BigDecimal.valueOf(76.30))
                .orderStatus(createOrderStatus())
                .user(User.builder()
                        .username("client")
                        .firstName("Cli")
                        .lastName("Ent")
                        .email("client@gmail.com")
                        .phoneNumber("+1238882")
                        .build())
                .services(List.of(createService1(), createService2()))
                .timeSlots(List.of(VisitTime.builder().id(2L).build()))
                .build();
    }

    /**
     * Creates order.
     */
    Order createOrderWithTimeSlots2() {
        return Order.builder()
                .id(2L)
                .orderDateTime(LocalDateTime.parse("2022-07-26 10:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("10:20:00"))
                .visitEndTime(LocalTime.parse("10:40:00"))
                .priceWithDiscount(BigDecimal.valueOf(30.30))
                .orderStatus(createOrderStatus())
                .user(User.builder()
                        .id(2L)
                        .username("user")
                        .firstName("Firstname")
                        .lastName("Lastname")
                        .email("user@gmail.com")
                        .phoneNumber("+1229110")
                        .build())
                .services(List.of(createService1(), createService2()))
                .timeSlots(List.of(VisitTime.builder().id(2L).build()))
                .build();
    }

    /**
     * Creates order.
     */
    Order createOrderWithoutTimeSlots1() {
        return Order.builder()
                .id(3L)
                .orderDateTime(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:55:00"))
                .priceWithDiscount(BigDecimal.valueOf(76.30))
                .orderStatus(createOrderStatus())
                .user(User.builder()
                        .username("client")
                        .firstName("Cli")
                        .lastName("Ent")
                        .email("client@gmail.com")
                        .phoneNumber("+1238882")
                        .build())
                .services(List.of(createService1(), createService2()))
                .build();
    }

    /**
     * Creates order.
     */
    Order createOrderWithoutTimeSlots2() {
        return Order.builder()
                .id(4L)
                .orderDateTime(LocalDateTime.parse("2022-07-25 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:55:00"))
                .priceWithDiscount(BigDecimal.valueOf(76.30))
                .orderStatus(createOrderStatus())
                .user(User.builder()
                        .username("client")
                        .firstName("Cli")
                        .lastName("Ent")
                        .email("client@gmail.com")
                        .phoneNumber("+1238882")
                        .build())
                .services(List.of(createService1(), createService2()))
                .build();
    }

    /**
     * Creates MockedResultSet.
     */
    ResultSet createMockedResultSetWithOrders(List<Order> orders) {
        var orderStatus = createOrderStatus();
        var service1 = createService1();
        var service2 = createService2();

        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnId = DSL.field(ID, SQLDataType.BIGINT);
        Field<LocalDateTime> columnOrderDateTime = DSL.field(ORDER_DATE_TIME, SQLDataType.LOCALDATETIME);
        Field<LocalDate> columnVisitDate = DSL.field(VISIT_DATE, SQLDataType.LOCALDATE);
        Field<LocalTime> columnVisitBeginTime = DSL.field(VISIT_BEGIN_TIME, SQLDataType.LOCALTIME);
        Field<LocalTime> columnVisitEndTime = DSL.field(VISIT_END_TIME, SQLDataType.LOCALTIME);
        Field<BigDecimal> columnPriceWithDiscount = DSL.field(PRICE_WITH_DISCOUNT, SQLDataType.DECIMAL(10, 2));
        Field<String> columnOrderStatus = DSL.field(STATUS, SQLDataType.VARCHAR(30));
        Field<String> columnDescription = DSL.field(DESCRIPTION, SQLDataType.VARCHAR(60));
        Field<String> columnUsername = DSL.field(USERNAME, SQLDataType.VARCHAR(30));
        Field<String> columnFirstName = DSL.field(FIRST_NAME, SQLDataType.VARCHAR(30));
        Field<String> columnLastName = DSL.field(LAST_NAME, SQLDataType.VARCHAR(30));
        Field<String> columnEmail = DSL.field(EMAIL, SQLDataType.VARCHAR(100));
        Field<String> columnPhoneNumber = DSL.field(PHONE_NUMBER, SQLDataType.VARCHAR(20));
        Field<String> columnServiceName = DSL.field(SERVICE_NAME, SQLDataType.VARCHAR(50));

        var result = context.newResult(columnId, columnOrderDateTime,
                columnVisitDate, columnVisitBeginTime, columnVisitEndTime, columnPriceWithDiscount,
                columnOrderStatus, columnDescription,
                columnUsername, columnFirstName, columnLastName, columnEmail, columnPhoneNumber,
                columnServiceName);

        orders.forEach(
                order -> {
                    var user = order.getUser();
                    result.add(
                            context.newRecord(columnId, columnOrderDateTime,
                                            columnVisitDate, columnVisitBeginTime, columnVisitEndTime, columnPriceWithDiscount,
                                            columnOrderStatus, columnDescription,
                                            columnUsername, columnFirstName, columnLastName, columnEmail, columnPhoneNumber,
                                            columnServiceName)
                                    .values(order.getId(), order.getOrderDateTime(),
                                            order.getVisitDate(), order.getVisitBeginTime(), order.getVisitEndTime(), order.getPriceWithDiscount(),
                                            orderStatus.getStatus(), orderStatus.getDescription(),
                                            user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(),
                                            service1.getName())
                    );
                    result.add(
                            context.newRecord(columnId, columnOrderDateTime,
                                            columnVisitDate, columnVisitBeginTime, columnVisitEndTime, columnPriceWithDiscount,
                                            columnOrderStatus, columnDescription,
                                            columnUsername, columnFirstName, columnLastName, columnEmail, columnPhoneNumber,
                                            columnServiceName)
                                    .values(order.getId(), order.getOrderDateTime(),
                                            order.getVisitDate(), order.getVisitBeginTime(), order.getVisitEndTime(), order.getPriceWithDiscount(),
                                            orderStatus.getStatus(), orderStatus.getDescription(),
                                            user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(),
                                            service2.getName())
                    );
                }
        );
        return new MockResultSet(result);
    }

    /**
     * Creates MockedResultSet.
     */
    ResultSet createMockedResultSetOrdersNumber(List<Order> orders) {
        var numberOfOrders = orders.size();

        DSLContext context = DSL.using(SQLDialect.DEFAULT);
        Field<Long> columnNumberOfOrders = DSL.field(NUMBER_OF_ORDERS, SQLDataType.BIGINT);

        var result = context.newResult(columnNumberOfOrders);

        result.add(context.newRecord(columnNumberOfOrders).values((long) numberOfOrders));
        return new MockResultSet(result);
    }
}
