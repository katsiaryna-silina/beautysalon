package by.silina.beautysalon.validator;

import by.silina.beautysalon.model.dto.UserRegistrationDto;

import java.util.Map;

/**
 * The UserRegistrationDtoValidator interface.
 *
 * @author Silina Katsiaryna
 */
public interface UserRegistrationDtoValidator {

    /**
     * Checks UserRegistrationDto.
     *
     * @param userRegistrationDto UserRegistrationDto
     * @return Map of String. Error map.
     */
    Map<String, String> checkUserRegistrationDto(UserRegistrationDto userRegistrationDto);
}
