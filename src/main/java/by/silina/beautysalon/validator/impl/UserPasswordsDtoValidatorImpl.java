package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.validator.UserPasswordsDtoValidator;
import by.silina.beautysalon.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.NEW_PASSWORD_ERROR_MESSAGE;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.PASSWORD_ERROR_MESSAGE;

/**
 * The UserPasswordsDtoValidatorImpl class that responsible for validation UserPasswordsDto.
 *
 * @author Silina Katsiaryna
 */
public class UserPasswordsDtoValidatorImpl implements UserPasswordsDtoValidator {
    private static final UserPasswordsDtoValidatorImpl instance = new UserPasswordsDtoValidatorImpl();

    /**
     * Initializes a new UserPasswordsDtoValidatorImpl.
     */
    private UserPasswordsDtoValidatorImpl() {
    }

    /**
     * Gets the single instance of UserPasswordsDtoValidatorImpl.
     *
     * @return UserPasswordsDtoValidatorImpl
     */
    public static UserPasswordsDtoValidatorImpl getInstance() {
        return instance;
    }

    /**
     * Checks UserPasswordsDto.
     *
     * @param userPasswordsDto UserPasswordsDto
     * @return Map of String. Error map.
     */
    @Override
    public Map<String, String> checkUserPasswordsDto(UserPasswordsDto userPasswordsDto) {
        UserValidator userValidator = UserValidatorImpl.getInstance();

        Map<String, String> errorMap = new HashMap<>();

        var currentPassword = userPasswordsDto.getCurrentPassword();
        var newPassword = userPasswordsDto.getNewPassword();
        var repeatedNewPassword = userPasswordsDto.getRepeatedNewPassword();

        if (!userValidator.checkPassword(currentPassword)) {
            errorMap.put(PASSWORD_ERROR_MESSAGE, "Password is not correct.");
            return errorMap;
        }

        if (currentPassword.equals(newPassword)) {
            errorMap.put(NEW_PASSWORD_ERROR_MESSAGE, "New password is the same as the old one.");
            return errorMap;
        }

        if (newPassword != null && !newPassword.equals(repeatedNewPassword)) {
            errorMap.put(NEW_PASSWORD_ERROR_MESSAGE, "Those passwords didn't match. Try again.");
        }
        if (!userValidator.checkPassword(newPassword) && !userValidator.checkPassword(repeatedNewPassword)) {
            errorMap.put(NEW_PASSWORD_ERROR_MESSAGE, "Password is not valid, must be between 4 and 20 characters long. \n" +
                    "Use only Latin letters, numbers, underscore and minus sign.");
        }
        return errorMap;
    }
}
