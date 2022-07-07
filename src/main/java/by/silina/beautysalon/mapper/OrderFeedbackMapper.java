package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.model.entity.OrderFeedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderFeedbackMapper {
    OrderFeedback toEntity(ResultSet resultSet) throws SQLException;

    OrderFeedback toEntity(OrderFeedbackDto feedbackDto);

    OrderFeedbackDto toDto(SessionRequestContent sessionRequestContent);

    OrderFeedbackDto toDto(OrderFeedback orderFeedback);
}
