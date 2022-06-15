package by.silina.beautysalon.validator;

import by.silina.beautysalon.model.dto.UserPasswordsDto;

import java.util.Map;

public interface UserPasswordsDtoValidator {
    Map<String, String> checkUserPasswordsDto(UserPasswordsDto userPasswordsDto);
}
