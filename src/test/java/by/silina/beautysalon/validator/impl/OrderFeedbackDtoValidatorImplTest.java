package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.validator.OrderFeedbackValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The OrderFeedbackDtoValidatorImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderFeedbackDtoValidatorImplTest {
    @InjectMocks
    OrderFeedbackDtoValidatorImpl feedbackDtoValidator;
    @Mock
    OrderFeedbackValidator feedbackValidator;

    /**
     * Tests checking valid OrderFeedbackDto.
     */
    @Test
    void checkValidOrderFeedbackDto() {
        var feedbackDto = OrderFeedbackDto.builder()
                .feedback("Feedback text.")
                .mark((byte) 4)
                .build();

        Mockito.when(feedbackValidator.checkMark(Mockito.anyByte())).thenReturn(true);
        Mockito.when(feedbackValidator.checkFeedback(Mockito.anyString())).thenReturn(true);

        var actualErrorMap = feedbackDtoValidator.checkOrderFeedbackDto(feedbackDto);
        Assertions.assertTrue(actualErrorMap.isEmpty());
    }

    /**
     * Tests checking invalid OrderFeedbackDto.
     */
    @Test
    void checkInvalidOrderFeedbackDto() {
        var feedbackDto = OrderFeedbackDto.builder()
                .feedback(" ")
                .mark((byte) 7)
                .build();

        Mockito.when(feedbackValidator.checkMark(Mockito.anyByte())).thenReturn(false);
        Mockito.when(feedbackValidator.checkFeedback(Mockito.anyString())).thenReturn(false);

        var actualErrorMap = feedbackDtoValidator.checkOrderFeedbackDto(feedbackDto);
        Assertions.assertFalse(actualErrorMap.isEmpty());
    }
}
