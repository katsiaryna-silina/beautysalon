package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderFeedbackDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.OrderFeedbackMapper;
import by.silina.beautysalon.mapper.impl.OrderFeedbackMapperImpl;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.service.OrderFeedbackService;
import by.silina.beautysalon.validator.OrderFeedbackDtoValidator;
import by.silina.beautysalon.validator.impl.OrderFeedbackDtoValidatorImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.FEEDBACK_UPDATE_ERROR_MESSAGE;

/**
 * The OrderFeedbackServiceImpl class that responsible for processing OrderFeedback.
 *
 * @author Silina Katsiaryna
 */
public class OrderFeedbackServiceImpl implements OrderFeedbackService {
    private static final OrderFeedbackServiceImpl instance = new OrderFeedbackServiceImpl();
    private final OrderFeedbackDaoImpl orderFeedbackDao = OrderFeedbackDaoImpl.getInstance();
    private final OrderFeedbackMapper feedbackMapper = OrderFeedbackMapperImpl.getInstance();
    private final OrderFeedbackDtoValidator feedbackDtoValidator = OrderFeedbackDtoValidatorImpl.getInstance();

    /**
     * Initializes a new OrderFeedbackServiceImpl.
     */
    private OrderFeedbackServiceImpl() {
    }

    /**
     * Gets the single instance of OrderFeedbackServiceImpl.
     *
     * @return OrderFeedbackServiceImpl
     */
    public static OrderFeedbackServiceImpl getInstance() {
        return instance;
    }

    /**
     * Updates feedback from dto data.
     *
     * @param feedbackDto OrderFeedbackDto
     * @return Map of Strings
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public Map<String, String> updateFeedback(OrderFeedbackDto feedbackDto) throws ServiceException {
        Map<String, String> errorMap = new HashMap<>();

        if (feedbackDto == null) {
            errorMap.put(FEEDBACK_UPDATE_ERROR_MESSAGE, "Feedback cannot be updated.");
            return errorMap;
        }

        errorMap = feedbackDtoValidator.checkOrderFeedbackDto(feedbackDto);

        if (!errorMap.isEmpty()) {
            errorMap.put(FEEDBACK_UPDATE_ERROR_MESSAGE, "Feedback cannot be updated.");
        } else {
            var orderFeedback = feedbackMapper.toEntity(feedbackDto);
            try {
                if (!orderFeedbackDao.update(orderFeedback)) {
                    errorMap.put(FEEDBACK_UPDATE_ERROR_MESSAGE, "Feedback cannot be updated.");
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return errorMap;
    }

    /**
     * Finds feedback by its id and maps this feedback to dto.
     *
     * @param id Long. Feedback id.
     * @return Optional of OrderFeedbackDto
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public Optional<OrderFeedbackDto> findById(Long id) throws ServiceException {
        Optional<OrderFeedbackDto> feedbackDtoOptional = Optional.empty();
        try {
            var feedbackOptional = orderFeedbackDao.findById(id);
            if (feedbackOptional.isPresent()) {
                var orderFeedbackDto = feedbackMapper.toDto(feedbackOptional.get());
                feedbackDtoOptional = Optional.of(orderFeedbackDto);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return feedbackDtoOptional;
    }
}
