package by.silina.beautysalon.validator;

import by.silina.beautysalon.model.dto.UserRegistrationDto;

import java.util.Map;

public interface UserRegistrationDtoValidator {
    Map<String, String> checkUserRegistrationDto(UserRegistrationDto userRegistrationDto);
}
