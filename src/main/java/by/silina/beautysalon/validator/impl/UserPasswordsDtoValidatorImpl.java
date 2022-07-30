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
    private static final UserPasswordsDtoValidatorImpl instance = new UserPasswordsDtoValidatorImpl(UserValidatorImpl.getInstance());
    private static final String ERROR_MESSAGE_PASSWORD_IS_NOT_CORRECT = "error.message.password.is.not.correct";
    private static final String ERROR_MESSAGE_PASSWORD_SAME = "error.message.password.same";
    private static final String ERROR_MESSAGE_PASSWORDS_DIDNT_MATCH = "error.message.passwords.didnt.match";
    private static final String ERROR_MESSAGE_PASSWORD_IS_NOT_VALID = "error.message.password.is.not.valid";
    private final UserValidator userValidator;

    /**
     * Initializes a new UserPasswordsDtoValidatorImpl.
     */
    private UserPasswordsDtoValidatorImpl(UserValidator userValidator) {
        this.userValidator = userValidator;
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
        Map<String, String> errorMap = new HashMap<>();

        var currentPassword = userPasswordsDto.getCurrentPassword();
        var newPassword = userPasswordsDto.getNewPassword();
        var repeatedNewPassword = userPasswordsDto.getRepeatedNewPassword();

        if (!userValidator.checkPassword(currentPassword)) {
            errorMap.put(PASSWORD_ERROR_MESSAGE, ERROR_MESSAGE_PASSWORD_IS_NOT_CORRECT);
            return errorMap;
        }

        if (currentPassword.equals(newPassword)) {
            errorMap.put(NEW_PASSWORD_ERROR_MESSAGE, ERROR_MESSAGE_PASSWORD_SAME);
            return errorMap;
        }

        if (newPassword != null && !newPassword.equals(repeatedNewPassword)) {
            errorMap.put(NEW_PASSWORD_ERROR_MESSAGE, ERROR_MESSAGE_PASSWORDS_DIDNT_MATCH);
        }
        if (!userValidator.checkPassword(newPassword) && !userValidator.checkPassword(repeatedNewPassword)) {
            errorMap.put(NEW_PASSWORD_ERROR_MESSAGE, ERROR_MESSAGE_PASSWORD_IS_NOT_VALID);
        }
        return errorMap;
    }
}
