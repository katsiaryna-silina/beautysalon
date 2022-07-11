package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.validator.OrderFeedbackValidator;
import liquibase.util.StringUtil;

public class OrderFeedbackValidatorImpl implements OrderFeedbackValidator {
    private static final byte MARK_MIN = 1;
    private static final byte MARK_MAX = 5;
    private static OrderFeedbackValidatorImpl instance;

    private OrderFeedbackValidatorImpl() {
    }

    public static OrderFeedbackValidatorImpl getInstance() {
        if (instance == null) {
            instance = new OrderFeedbackValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean checkMark(byte mark) {
        return MARK_MIN <= mark && mark <= MARK_MAX;
    }

    @Override
    public boolean checkFeedback(String feedback) {
        return !StringUtil.isEmpty(feedback);
    }
}
