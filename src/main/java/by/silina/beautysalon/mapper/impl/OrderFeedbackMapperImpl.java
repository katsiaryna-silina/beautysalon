package by.silina.beautysalon.mapper.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.dao.TableColumnName;
import by.silina.beautysalon.mapper.OrderFeedbackMapper;
import by.silina.beautysalon.model.dto.OrderFeedbackDto;
import by.silina.beautysalon.model.entity.OrderFeedback;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.ID;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.dao.TableColumnName.IS_VERIFIED;
import static by.silina.beautysalon.dao.TableColumnName.*;

/**
 * The OrderFeedbackMapperImpl class responsible for mapping OrderFeedback.
 *
 * @author Silina Katsiaryna
 */
public class OrderFeedbackMapperImpl implements OrderFeedbackMapper {
    private static final OrderFeedbackMapperImpl instance = new OrderFeedbackMapperImpl();

    /**
     * Initializes a new OrderFeedbackMapperImpl.
     */
    private OrderFeedbackMapperImpl() {
    }

    /**
     * Gets the single instance of OrderFeedbackMapperImpl.
     *
     * @return OrderFeedbackMapperImpl
     */
    public static OrderFeedbackMapperImpl getInstance() {
        return instance;
    }

    /**
     * Maps passed ResultSet to OrderFeedback entity.
     *
     * @param resultSet ResultSet
     * @return OrderFeedback
     * @throws SQLException if a sql exception occurs.
     */
    @Override
    public OrderFeedback toEntity(ResultSet resultSet) throws SQLException {
        return OrderFeedback.builder()
                .id(resultSet.getLong(TableColumnName.ID))
                .feedback(resultSet.getString(FEEDBACK))
                .mark(resultSet.getByte(MARK))
                .isVerified(resultSet.getBoolean(IS_VERIFIED))
                .build();
    }

    /**
     * Maps passed OrderFeedbackDto to OrderFeedback entity.
     *
     * @param feedbackDto OrderFeedbackDto
     * @return OrderFeedback
     */
    @Override
    public OrderFeedback toEntity(OrderFeedbackDto feedbackDto) {
        return OrderFeedback.builder()
                .id(feedbackDto.getId())
                .feedback(feedbackDto.getFeedback())
                .mark(feedbackDto.getMark())
                .build();
    }

    /**
     * Maps passed SessionRequestContent to OrderFeedbackDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return OrderFeedbackDto
     */
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

    /**
     * Maps passed OrderFeedback to OrderFeedbackDto.
     *
     * @param orderFeedback OrderFeedback
     * @return OrderFeedbackDto
     */
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
