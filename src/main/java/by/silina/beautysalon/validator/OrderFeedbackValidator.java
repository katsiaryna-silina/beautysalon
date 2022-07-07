package by.silina.beautysalon.validator;

public interface OrderFeedbackValidator {
    boolean checkMark(byte mark);

    boolean checkFeedback(String feedback);
}
