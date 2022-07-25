package by.silina.beautysalon.validator;

/**
 * The OrderFeedbackValidator interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderFeedbackValidator {

    /**
     * Checks mark.
     *
     * @param mark byte
     * @return boolean. True if mark is correct; false otherwise.
     */
    boolean checkMark(byte mark);

    /**
     * Checks feedback.
     *
     * @param feedback String
     * @return boolean. True if feedback is correct; false otherwise.
     */
    boolean checkFeedback(String feedback);
}
