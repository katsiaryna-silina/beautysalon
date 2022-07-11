package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.validator.OrderFeedbackDtoValidator;
import by.silina.beautysalon.validator.OrderFeedbackValidator;

import java.util.HashMap;
import java.util.Map;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.FEEDBACK_ERROR_MESSAGE;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.MARK_ERROR_MESSAGE;

public class OrderFeedbackDtoValidatorImpl implements OrderFeedbackDtoValidator {
    private static final OrderFeedbackDtoValidatorImpl instance = new OrderFeedbackDtoValidatorImpl();
    private final OrderFeedbackValidator feedbackValidator = OrderFeedbackValidatorImpl.getInstance();

    private OrderFeedbackDtoValidatorImpl() {
    }

    public static OrderFeedbackDtoValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public Map<String, String> checkOrderFeedbackDto(OrderFeedbackDto orderFeedbackDto) {
        Map<String, String> errorMap = new HashMap<>();

        byte mark = orderFeedbackDto.getMark();
        if (!feedbackValidator.checkMark(mark)) {
            errorMap.put(MARK_ERROR_MESSAGE, "Mark is not valid.");
        }

        var feedback = orderFeedbackDto.getFeedback();
        if (!feedbackValidator.checkFeedback(feedback)) {
            errorMap.put(FEEDBACK_ERROR_MESSAGE, "Feedback shouldn't be empty.");
        }
        return errorMap;
    }
}
