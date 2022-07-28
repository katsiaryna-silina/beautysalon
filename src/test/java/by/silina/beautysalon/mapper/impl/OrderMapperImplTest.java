package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.AttributeAndParameterName;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.dao.TableColumnName.EMAIL;
import static by.silina.beautysalon.dao.TableColumnName.FIRST_NAME;
import static by.silina.beautysalon.dao.TableColumnName.ID;
import static by.silina.beautysalon.dao.TableColumnName.LAST_NAME;
import static by.silina.beautysalon.dao.TableColumnName.PHONE_NUMBER;
import static by.silina.beautysalon.dao.TableColumnName.PRICE_WITH_DISCOUNT;
import static by.silina.beautysalon.dao.TableColumnName.STATUS;
import static by.silina.beautysalon.dao.TableColumnName.USERNAME;
import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The OrderMapperImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderMapperImplTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    OrderMapperImpl orderMapper = OrderMapperImpl.getInstance();
    @Mock
    SessionRequestContent sessionRequestContent;

    /**
     * Tests mapping Order entity to OrderForClientDto.
     */
    @Test
    void entityToOrderForClientDto() {
        var entity = Order.builder()
                .id(1L)
                .orderDateTime(LocalDateTime.now())
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:40:00"))
                .services(List.of(
                        Serv.builder().name("name1").build(),
                        Serv.builder().name("name2").build()))
                .priceWithDiscount(BigDecimal.valueOf(10.2))
                .orderStatus(OrderStatus.builder()
                        .status("status")
                        .description("description")
                        .build())
                .build();

        var expectedDto = OrderForClientDto.builder()
                .id(entity.getId())
                .orderDateTime(entity.getOrderDateTime())
                .visitDate(entity.getVisitDate())
                .visitBeginTime(entity.getVisitBeginTime())
                .visitEndTime(entity.getVisitEndTime())
                .serviceNames(
                        entity.getServices().stream()
                                .map(Serv::getName)
                                .toList())
                .priceWithDiscount(entity.getPriceWithDiscount())
                .status(entity.getOrderStatus().getStatus())
                .description(entity.getOrderStatus().getDescription())
                .build();

        var actualDto = orderMapper.toOrderForClientDto(entity);
        Assertions.assertEquals(expectedDto, actualDto);
    }

    /**
     * Tests mapping Order entity to OrderForAdminDto.
     */
    @Test
    void entityToOrderForAdminDto() {
        var user = User.builder()
                .username("username")
                .firstName("First")
                .lastName("Last")
                .email("client@gmail.com")
                .phoneNumber("+375622221")
                .build();

        var orderStatus = OrderStatus.builder()
                .status("status")
                .description("description")
                .build();

        var entity = Order.builder()
                .id(1L)
                .orderDateTime(LocalDateTime.now())
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:40:00"))
                .services(List.of(
                        Serv.builder().name("name1").build(),
                        Serv.builder().name("name2").build()))
                .priceWithDiscount(BigDecimal.valueOf(10.2))
                .user(user)
                .orderStatus(orderStatus)
                .build();

        var expectedDto = OrderForAdminDto.builder()
                .id(1L)
                .orderDateTime(entity.getOrderDateTime())
                .visitDate(entity.getVisitDate())
                .visitBeginTime(entity.getVisitBeginTime())
                .visitEndTime(entity.getVisitEndTime())
                .serviceNames(
                        entity.getServices().stream()
                                .map(Serv::getName)
                                .toList())
                .priceWithDiscount(entity.getPriceWithDiscount())
                .username(entity.getUser().getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(orderStatus.getStatus())
                .description(orderStatus.getDescription())
                .build();

        var actualDto = orderMapper.toOrderForAdminDto(entity);
        Assertions.assertEquals(expectedDto, actualDto);
    }

    /**
     * Tests mapping OrderFormDto to Order entity.
     */
    @Test
    void dtoToEntity() {
        var dto = OrderFormDto.builder()
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:40:00"))
                .servicesIds(List.of(1L, 2L))
                .timeSlotIds(List.of(4L))
                .priceWithDiscount(BigDecimal.valueOf(10.2))
                .userId(1L)
                .build();

        var expectedEntity = Order.builder()
                .visitDate(dto.getVisitDate())
                .visitBeginTime(dto.getVisitBeginTime())
                .visitEndTime(dto.getVisitEndTime())
                .services(
                        dto.getServicesIds()
                                .stream()
                                .map(el -> Serv.builder().id(el).build())
                                .toList())
                .timeSlots(
                        dto.getTimeSlotIds()
                                .stream()
                                .map(el -> VisitTime.builder().id(el).build())
                                .toList())
                .priceWithDiscount(dto.getPriceWithDiscount())
                .user(User.builder()
                        .id(dto.getUserId())
                        .build())
                .build();

        var actualEntity = orderMapper.toEntity(dto);
        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    /**
     * Tests mapping ResultSet to Order entity.
     */
    @Test
    void resultSetToEntity() throws SQLException {
        var orderStatus = OrderStatus.builder()
                .status("waiting")
                .description("Waiting for confirmation.")
                .build();

        var service = Serv.builder()
                .name("serv name1")
                .build();

        var user = User.builder()
                .username("client")
                .firstName("Cli")
                .lastName("Ent")
                .email("client@gmail.com")
                .phoneNumber("+1238882")
                .build();

        var expectedOrder = Order.builder()
                .id(3L)
                .orderDateTime(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:55:00"))
                .priceWithDiscount(BigDecimal.valueOf(76.30))
                .orderStatus(orderStatus)
                .user(user)
                .services(List.of(service))
                .build();

        //create MockResultSet
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

        result.add(
                context.newRecord(columnId, columnOrderDateTime,
                                columnVisitDate, columnVisitBeginTime, columnVisitEndTime, columnPriceWithDiscount,
                                columnOrderStatus, columnDescription,
                                columnUsername, columnFirstName, columnLastName, columnEmail, columnPhoneNumber,
                                columnServiceName)
                        .values(expectedOrder.getId(), expectedOrder.getOrderDateTime(),
                                expectedOrder.getVisitDate(), expectedOrder.getVisitBeginTime(), expectedOrder.getVisitEndTime(), expectedOrder.getPriceWithDiscount(),
                                orderStatus.getStatus(), orderStatus.getDescription(),
                                user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(),
                                service.getName())
        );
        ResultSet mockResultSet = new MockResultSet(result);
        mockResultSet.next();

        var actualEntity = orderMapper.toEntity(mockResultSet);
        Assertions.assertEquals(expectedOrder, actualEntity);
    }

    /**
     * Tests mapping SessionRequestContent to OrderFormDto.
     */
    @Test
    void sessionRequestContentToOrderFormDto() {
        var servicesIdsString = "1 2 3";
        var visitTimeIdsString = "4, 5";
        var date = String.valueOf(LocalDate.now());
        var userId = 2L;
        var visitTimeBegin = "09:00:00";
        var visitTimeEnd = "09:50:00";
        var price = "26.6";

        Mockito.when(sessionRequestContent.getParameterByName(SERVICES_IDS)).thenReturn(servicesIdsString);
        Mockito.when(sessionRequestContent.getParameterByName(VISIT_TIME_IDS)).thenReturn(visitTimeIdsString);
        Mockito.when(sessionRequestContent.getParameterByName(DATE)).thenReturn(date);
        Mockito.when(sessionRequestContent.getSessionAttributeByName(USER_ID)).thenReturn(userId);
        Mockito.when(sessionRequestContent.getParameterByName(VISIT_TIME_BEGIN)).thenReturn(visitTimeBegin);
        Mockito.when(sessionRequestContent.getParameterByName(VISIT_TIME_END)).thenReturn(visitTimeEnd);
        Mockito.when(sessionRequestContent.getParameterByName(AttributeAndParameterName.PRICE_WITH_DISCOUNT)).thenReturn(price);

        var expectedDto = OrderFormDto.builder()
                .visitDate(LocalDate.parse(date))
                .userId(userId)
                .timeSlotIds(List.of(4L, 5L))
                .visitBeginTime(LocalTime.parse(visitTimeBegin))
                .visitEndTime(LocalTime.parse(visitTimeEnd))
                .servicesIds(List.of(1L, 2L, 3L))
                .priceWithDiscount(BigDecimal.valueOf(Double.parseDouble(price)).setScale(2))
                .build();

        var actualDto = orderMapper.toOrderFormDto(sessionRequestContent);
        Assertions.assertEquals(expectedDto, actualDto);
    }
}
