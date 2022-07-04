package by.silina.beautysalon.mapper;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.model.dto.OrderFormDto;

public interface OrderMapper {
    OrderFormDto toOrderFormDto(SessionRequestContent sessionRequestContent);
}
