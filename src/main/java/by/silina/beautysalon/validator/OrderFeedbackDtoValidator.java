package by.silina.beautysalon.validator;

import by.silina.beautysalon.model.dto.OrderFeedbackDto;

import java.util.Map;

/**
 * The OrderFeedbackDtoValidator interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderFeedbackDtoValidator {

    /**
     * Checks OrderFeedbackDto.
     *
     * @param orderFeedbackDto OrderFeedbackDto
     * @return Map of String. Error map.
     */
    Map<String, String> checkOrderFeedbackDto(OrderFeedbackDto orderFeedbackDto);
}
