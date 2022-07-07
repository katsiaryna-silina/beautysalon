package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;

import java.util.Map;
import java.util.Optional;

public interface OrderFeedbackService {
    Map<String, String> updateFeedback(OrderFeedbackDto feedbackDto) throws ServiceException;

    Optional<OrderFeedbackDto> findById(Long id) throws ServiceException;
}
