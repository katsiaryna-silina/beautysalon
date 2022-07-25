package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The OrderMapperImpl class responsible for mapping Order.
 *
 * @author Silina Katsiaryna
 */
public interface OrderMapper {

    /**
     * Maps passed Order entity to OrderForClientDto.
     *
     * @param order Order
     * @return OrderForClientDto
     */
    OrderForClientDto toOrderForClientDto(Order order);

    /**
     * Maps passed Order entity to OrderForAdminDto.
     *
     * @param order Order
     * @return OrderForAdminDto
     */
    OrderForAdminDto toOrderForAdminDto(Order order);

    /**
     * Maps passed OrderFormDto to Order entity.
     *
     * @param orderFormDto OrderFormDto
     * @return Order
     */
    Order toEntity(OrderFormDto orderFormDto);

    /**
     * Maps passed ResultSet to Order entity.
     *
     * @param resultSet ResultSet
     * @return Order
     * @throws SQLException if a sql exception occurs.
     */
    Order toEntity(ResultSet resultSet) throws SQLException;

    /**
     * Maps passed SessionRequestContent to OrderFormDto.
     *
     * @param sessionRequestContent SessionRequestContent
     * @return OrderFormDto
     */
    OrderFormDto toOrderFormDto(SessionRequestContent sessionRequestContent);
}
