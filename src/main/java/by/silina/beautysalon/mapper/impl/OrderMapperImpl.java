package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.PRICE_WITH_DISCOUNT;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.dao.TableColumnName.EMAIL;
import static by.silina.beautysalon.dao.TableColumnName.FIRST_NAME;
import static by.silina.beautysalon.dao.TableColumnName.ID;
import static by.silina.beautysalon.dao.TableColumnName.LAST_NAME;
import static by.silina.beautysalon.dao.TableColumnName.PHONE_NUMBER;
import static by.silina.beautysalon.dao.TableColumnName.STATUS;
import static by.silina.beautysalon.dao.TableColumnName.USERNAME;
import static by.silina.beautysalon.dao.TableColumnName.*;

public class OrderMapperImpl implements OrderMapper {
    public static final String DIGITS_REGEX = "\\d+";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final OrderMapperImpl instance = new OrderMapperImpl();

    private OrderMapperImpl() {
    }

    public static OrderMapperImpl getInstance() {
        return instance;
    }

    @Override
    public OrderForClientDto toOrderForClientDto(Order order) {
        List<String> serviceNames = order.getServices().stream()
                .map(Serv::getName).toList();

        return OrderForClientDto.builder()
                .id(order.getId())
                .orderDateTime(order.getOrderDateTime())
                .visitDate(order.getVisitDate())
                .visitBeginTime(order.getVisitBeginTime())
                .visitEndTime(order.getVisitEndTime())
                .serviceNames(serviceNames)
                .priceWithDiscount(order.getPriceWithDiscount())
                .status(order.getOrderStatus().getStatus())
                .description(order.getOrderStatus().getDescription())
                .build();
    }

    @Override
    public OrderForAdminDto toOrderForAdminDto(Order order) {
        List<String> serviceNames = order.getServices().stream()
                .map(Serv::getName).toList();

        return OrderForAdminDto.builder()
                .id(order.getId())
                .orderDateTime(order.getOrderDateTime())
                .visitDate(order.getVisitDate())
                .visitBeginTime(order.getVisitBeginTime())
                .visitEndTime(order.getVisitEndTime())
                .serviceNames(serviceNames)
                .priceWithDiscount(order.getPriceWithDiscount())
                .username(order.getUser().getUsername())
                .firstName(order.getUser().getFirstName())
                .lastName(order.getUser().getLastName())
                .email(order.getUser().getEmail())
                .phoneNumber(order.getUser().getPhoneNumber())
                .status(order.getOrderStatus().getStatus())
                .description(order.getOrderStatus().getDescription())
                .build();
    }

    @Override
    public Order toEntity(OrderFormDto orderFormDto) {
        List<Serv> services = new ArrayList<>();
        orderFormDto.getServicesIds().forEach(el ->
                services.add(Serv.builder().id(el).build()));

        List<VisitTime> timeSlots = new ArrayList<>();
        orderFormDto.getTimeSlotIds().forEach(el ->
                timeSlots.add(VisitTime.builder().id(el).build()));

        return Order.builder()
                .visitDate(orderFormDto.getVisitDate())
                .visitBeginTime(orderFormDto.getVisitBeginTime())
                .visitEndTime(orderFormDto.getVisitEndTime())
                .services(services)
                .priceWithDiscount(orderFormDto.getPriceWithDiscount())
                .timeSlots(timeSlots)
                .user(User.builder()
                        .id(orderFormDto.getUserId())
                        .build())
                .build();
    }

    @Override
    public Order toEntity(ResultSet resultSet) throws SQLException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        String orderDateTimeString = resultSet.getString(ORDER_DATE_TIME);
        LocalDateTime orderDateTime = LocalDateTime.parse(orderDateTimeString, dateTimeFormat);

        List<Serv> services = new ArrayList<>();
        services.add(Serv.builder()
                .name(resultSet.getString(SERVICE_NAME))
                .build());

        return Order.builder()
                .id(resultSet.getLong(ID))
                .orderDateTime(orderDateTime)
                .visitDate(LocalDate.parse(resultSet.getString(VISIT_DATE)))
                .visitBeginTime(LocalTime.parse(resultSet.getString(VISIT_BEGIN_TIME)))
                .visitEndTime(LocalTime.parse(resultSet.getString(VISIT_END_TIME)))
                .services(services)
                .priceWithDiscount(resultSet.getBigDecimal(TableColumnName.PRICE_WITH_DISCOUNT))
                .user(User.builder()
                        .username(resultSet.getString(USERNAME))
                        .firstName(resultSet.getString(FIRST_NAME))
                        .lastName(resultSet.getString(LAST_NAME))
                        .email(resultSet.getString(EMAIL))
                        .phoneNumber(resultSet.getString(PHONE_NUMBER))
                        .build())
                .orderStatus(OrderStatus.builder()
                        .status(resultSet.getString(STATUS))
                        .description(resultSet.getString(DESCRIPTION))
                        .build())
                .build();
    }

    @Override
    public OrderFormDto toOrderFormDto(SessionRequestContent sessionRequestContent) {
        var servicesIdsString = sessionRequestContent.getParameterByName(SERVICES_IDS);
        List<Long> servicesIds = getIds(servicesIdsString);

        var visitTimeIdsString = sessionRequestContent.getParameterByName(VISIT_TIME_IDS);
        List<Long> timeSlotsIds = getIds(visitTimeIdsString);

        return OrderFormDto.builder()
                .visitDate(LocalDate.parse(sessionRequestContent.getParameterByName(DATE)))
                .userId((Long) sessionRequestContent.getSessionAttributeByName(USER_ID))
                .timeSlotIds(timeSlotsIds)
                .visitBeginTime(LocalTime.parse(sessionRequestContent.getParameterByName(VISIT_TIME_BEGIN)))
                .visitEndTime(LocalTime.parse(sessionRequestContent.getParameterByName(VISIT_TIME_END)))
                .servicesIds(servicesIds)
                .priceWithDiscount(BigDecimal.valueOf(Double.parseDouble(sessionRequestContent.getParameterByName(PRICE_WITH_DISCOUNT))).setScale(2))
                .build();
    }

    private List<Long> getIds(String visitTimeIdsString) {
        List<Long> ids = new ArrayList<>();

        Pattern integerPattern = Pattern.compile(DIGITS_REGEX);
        Matcher matcher = integerPattern.matcher(visitTimeIdsString);

        while (matcher.find()) {
            Long id = Long.valueOf(matcher.group());
            ids.add(id);
        }
        return ids;
    }
}
