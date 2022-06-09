package by.epam.silina.validator;

import by.epam.silina.dto.UserRegistrationDto;

import java.util.Map;

public interface UserRegistrationDtoValidator {
    Map<String, String> checkUserRegistrationDto(UserRegistrationDto userRegistrationDto);
}
