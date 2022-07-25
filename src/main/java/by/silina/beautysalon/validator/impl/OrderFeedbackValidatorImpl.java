package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.validator.OrderFeedbackValidator;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

/**
 * The OrderFeedbackValidatorImpl class that responsible for validation OrderFeedback.
 *
 * @author Silina Katsiaryna
 */
public class OrderFeedbackValidatorImpl implements OrderFeedbackValidator {
    private static final byte MARK_MIN = 1;
    private static final byte MARK_MAX = 5;
    private static OrderFeedbackValidatorImpl instance;

    /**
     * Initializes a new OrderFeedbackValidatorImpl.
     */
    private OrderFeedbackValidatorImpl() {
    }

    /**
     * Gets the single instance of OrderFeedbackValidatorImpl.
     *
     * @return OrderFeedbackValidatorImpl
     */
    public static OrderFeedbackValidatorImpl getInstance() {
        if (instance == null) {
            instance = new OrderFeedbackValidatorImpl();
        }
        return instance;
    }

    /**
     * Checks mark.
     *
     * @param mark byte
     * @return boolean. True if mark is correct; false otherwise.
     */
    @Override
    public boolean checkMark(byte mark) {
        return MARK_MIN <= mark && mark <= MARK_MAX;
    }

    /**
     * Checks feedback.
     *
     * @param feedback String
     * @return boolean. True if feedback is correct; false otherwise.
     */
    @Override
    public boolean checkFeedback(String feedback) {
        return !StringUtils.isBlank(feedback);
    }
}
