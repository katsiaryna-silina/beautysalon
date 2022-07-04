package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.mapper.OrderMapper;
import by.silina.beautysalon.model.dto.OrderFormDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;

public class OrderMapperImpl implements OrderMapper {
    public static final String DIGITS_REGEX = "\\d+";
    private static final OrderMapperImpl instance = new OrderMapperImpl();

    private OrderMapperImpl() {
    }

    public static OrderMapperImpl getInstance() {
        return instance;
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
