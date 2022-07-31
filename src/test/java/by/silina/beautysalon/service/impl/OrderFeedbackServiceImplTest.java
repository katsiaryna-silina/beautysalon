package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderFeedbackDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.model.entity.OrderFeedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * The OrderFeedbackServiceImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderFeedbackServiceImplTest {
    @InjectMocks
    OrderFeedbackServiceImpl orderFeedbackService;
    @Mock
    OrderFeedbackDaoImpl orderFeedbackDao;

    /**
     * Tests updating feedback from valid dto.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void updateFeedbackFromValidDto() throws DaoException, ServiceException {
        var dto = OrderFeedbackDto.builder()
                .id(1L)
                .feedback("Feedback.")
                .mark((byte) 5)
                .isVerified(false)
                .build();

        Mockito.when(orderFeedbackDao.update(Mockito.any(OrderFeedback.class))).thenReturn(true);

        var actualErrorMap = orderFeedbackService.updateFeedback(dto);
        Assertions.assertTrue(actualErrorMap.isEmpty());

        Mockito.when(orderFeedbackDao.update(Mockito.any(OrderFeedback.class))).thenReturn(false);

        actualErrorMap = orderFeedbackService.updateFeedback(dto);
        Assertions.assertFalse(actualErrorMap.isEmpty());
    }

    /**
     * Tests updating feedback from invalid dto.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void updateFeedbackFromInvalidDto() throws ServiceException {
        var dto = OrderFeedbackDto.builder()
                .id(1L)
                .feedback(".")
                .mark((byte) -7)
                .isVerified(false)
                .build();

        var actualErrorMap = orderFeedbackService.updateFeedback(dto);
        Assertions.assertFalse(actualErrorMap.isEmpty());
    }

    /**
     * Tests updating feedback from null dto.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void updateFeedbackFromNullDto() throws ServiceException {
        var actualErrorMap = orderFeedbackService.updateFeedback(null);
        Assertions.assertFalse(actualErrorMap.isEmpty());
    }

    /**
     * Tests finding feedback by its id.
     * This test can be fully covered only by using integration test.s
     */
    @Test
    void findById() throws ServiceException, DaoException {
        var feedbackId = 1L;
        var feedbackText = "Feedback.";
        var mark = (byte) 4;
        var entity = OrderFeedback.builder()
                .id(feedbackId)
                .feedback(feedbackText)
                .mark(mark)
                .isVerified(false)
                .build();

        var dto = OrderFeedbackDto.builder()
                .id(feedbackId)
                .feedback(feedbackText)
                .mark(mark)
                .isVerified(false)
                .build();
        var expectedResult = Optional.of(dto);

        Mockito.when(orderFeedbackDao.findById(feedbackId)).thenReturn(Optional.of(entity));

        var actualResult = orderFeedbackService.findById(feedbackId);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
