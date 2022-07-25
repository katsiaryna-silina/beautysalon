package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;

import java.util.Map;
import java.util.Optional;

/**
 * The OrderFeedbackService interface.
 *
 * @author Silina Katsiaryna
 */
public interface OrderFeedbackService {

    /**
     * Updates feedback from dto data.
     *
     * @param feedbackDto OrderFeedbackDto
     * @return Map of Strings
     * @throws ServiceException if a service exception occurs.
     */
    Map<String, String> updateFeedback(OrderFeedbackDto feedbackDto) throws ServiceException;

    /**
     * Finds feedback by its id and maps this feedback to dto.
     *
     * @param id Long. Feedback id.
     * @return Optional of OrderFeedbackDto
     * @throws ServiceException if a service exception occurs.
     */
    Optional<OrderFeedbackDto> findById(Long id) throws ServiceException;
}
