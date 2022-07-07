package by.silina.beautysalon.validator;

import by.silina.beautysalon.model.dto.OrderFeedbackDto;

import java.util.Map;

public interface OrderFeedbackDtoValidator {
    Map<String, String> checkOrderFeedbackDto(OrderFeedbackDto orderFeedbackDto);
}
