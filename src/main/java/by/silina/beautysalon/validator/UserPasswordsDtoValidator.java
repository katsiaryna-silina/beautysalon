package by.silina.beautysalon.validator;

import by.silina.beautysalon.model.dto.UserPasswordsDto;

import java.util.Map;

/**
 * The UserPasswordsDtoValidator interface.
 *
 * @author Silina Katsiaryna
 */
public interface UserPasswordsDtoValidator {

    /**
     * Checks UserPasswordsDto.
     *
     * @param userPasswordsDto UserPasswordsDto
     * @return Map of String. Error map.
     */
    Map<String, String> checkUserPasswordsDto(UserPasswordsDto userPasswordsDto);
}
