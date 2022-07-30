package by.silina.beautysalon.validator.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;


/**
 * The OrderFeedbackValidatorImplTest test class.
 *
 * @author Silina Katsiaryna
 */
class OrderFeedbackValidatorImplTest {
    OrderFeedbackValidatorImpl feedbackValidator = OrderFeedbackValidatorImpl.getInstance();

    /**
     * Creates valid marks.
     */
    static Stream<Arguments> createValidMarks() {
        return Stream.of(
                //byte
                arguments((byte) 1),
                arguments((byte) 2),
                arguments((byte) 3),
                arguments((byte) 4),
                arguments((byte) 5)
        );
    }

    /**
     * Creates invalid marks.
     */
    static Stream<Arguments> createInvalidMarks() {
        return Stream.of(
                //byte
                arguments((byte) 0),
                arguments((byte) -1),
                arguments((byte) 6),
                arguments((byte) 10),
                arguments((byte) -9)
        );
    }

    /**
     * Creates valid feedbacks.
     */
    static Stream<Arguments> createValidFeedbacks() {
        return Stream.of(
                arguments("Some feedback."),
                arguments("F B.")
        );
    }

    /**
     * Creates invalid feedbacks.
     */
    static Stream<Arguments> createInvalidFeedbacks() {
        return Stream.of(
                arguments(""),
                arguments("  ")
        );
    }

    /**
     * Tests checking valid mark.
     */
    @ParameterizedTest
    @MethodSource("createValidMarks")
    void checkValidMark(byte mark) {
        var actualResult = feedbackValidator.checkMark(mark);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid mark.
     */
    @ParameterizedTest
    @MethodSource("createInvalidMarks")
    void checkInvalidMark(byte mark) {
        var actualResult = feedbackValidator.checkMark(mark);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking valid feedback.
     */
    @ParameterizedTest
    @MethodSource("createValidFeedbacks")
    void checkValidFeedback(String feedback) {
        var actualResult = feedbackValidator.checkFeedback(feedback);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid feedback.
     */
    @ParameterizedTest
    @MethodSource("createInvalidFeedbacks")
    void checkInvalidFeedback(String feedback) {
        var actualResult = feedbackValidator.checkFeedback(feedback);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking null feedback.
     */
    @Test
    void checkNullFeedback() {
        var actualResult = feedbackValidator.checkFeedback(null);
        Assertions.assertFalse(actualResult);
    }
}
