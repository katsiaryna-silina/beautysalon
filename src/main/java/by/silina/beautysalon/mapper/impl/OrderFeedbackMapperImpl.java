package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.OrderFeedbackMapper;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.model.entity.OrderFeedback;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.dao.TableColumnName.FEEDBACK;
import static by.silina.beautysalon.dao.TableColumnName.MARK;

public class OrderFeedbackMapperImpl implements OrderFeedbackMapper {
    private static final OrderFeedbackMapperImpl instance = new OrderFeedbackMapperImpl();

    private OrderFeedbackMapperImpl() {
    }

    public static OrderFeedbackMapperImpl getInstance() {
        return instance;
    }

    @Override
    public OrderFeedback toEntity(ResultSet resultSet) throws SQLException {
        return OrderFeedback.builder()
                .id(resultSet.getLong(TableColumnName.ID))
                .feedback(resultSet.getString(FEEDBACK))
                .mark(resultSet.getByte(MARK))
                .isVerified(resultSet.getBoolean(IS_VERIFIED))
                .build();
    }

    @Override
    public OrderFeedback toEntity(OrderFeedbackDto feedbackDto) {
        return OrderFeedback.builder()
                .id(feedbackDto.getId())
                .feedback(feedbackDto.getFeedback())
                .mark(feedbackDto.getMark())
                .build();
    }

    @Override
    public OrderFeedbackDto toDto(SessionRequestContent sessionRequestContent) {
        String newMarkString = sessionRequestContent.getParameterByName(NEW_MARK);
        byte mark;
        if (StringUtils.isEmpty(newMarkString)) {
            mark = -1;
        } else {
            mark = Byte.parseByte(newMarkString);
        }

        return OrderFeedbackDto.builder()
                .id(Long.valueOf(sessionRequestContent.getParameterByName(ID)))
                .feedback(sessionRequestContent.getParameterByName(NEW_FEEDBACK))
                .mark(mark)
                .build();
    }

    @Override
    public OrderFeedbackDto toDto(OrderFeedback orderFeedback) {
        return OrderFeedbackDto.builder()
                .id(orderFeedback.getId())
                .feedback(orderFeedback.getFeedback())
                .mark(orderFeedback.getMark())
                .isVerified(orderFeedback.isVerified())
                .build();
    }
}
