package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.model.entity.OrderFeedback;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The OrderFeedbackMapperImpl class responsible for mapping OrderFeedback.
 *
 * @author Silina Katsiaryna
 */
public interface OrderFeedbackMapper {

    /**
     * Maps passed ResultSet to OrderFeedback entity.
     *
     * @param resultSet ResultSet
     * @return OrderFeedback
     * @throws SQLException if a sql exception occurs.
     */
    OrderFeedback toEntity(ResultSet resultSet) throws SQLException;

    /**
     * Maps passed OrderFeedbackDto to OrderFeedback entity.
     *
     * @param feedbackDto OrderFeedbackDto
     * @return OrderFeedback
     */
    OrderFeedback toEntity(OrderFeedbackDto feedbackDto);


    /**
     * Maps passed SessionRequestContent to OrderFeedbackDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return OrderFeedbackDto
     */
    OrderFeedbackDto toDto(SessionRequestContent sessionRequestContent);

    /**
     * Maps passed OrderFeedback to OrderFeedbackDto.
     *
     * @param orderFeedback OrderFeedback
     * @return OrderFeedbackDto
     */
    OrderFeedbackDto toDto(OrderFeedback orderFeedback);
}
