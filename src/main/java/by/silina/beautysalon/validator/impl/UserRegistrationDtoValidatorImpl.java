package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.validator.UserRegistrationDtoValidator;
import by.silina.beautysalon.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;

/**
 * The UserRegistrationDtoValidatorImpl class that responsible for validation UserRegistrationDto.
 *
 * @author Silina Katsiaryna
 */
public class UserRegistrationDtoValidatorImpl implements UserRegistrationDtoValidator {
    private static final UserRegistrationDtoValidatorImpl instance = new UserRegistrationDtoValidatorImpl(UserValidatorImpl.getInstance());
    private static final String ERROR_MESSAGE_USERNAME_IS_NOT_VALID = "error.message.username.is.not.valid";
    private static final String ERROR_MESSAGE_PASSWORDS_DIDNT_MATCH = "error.message.passwords.didnt.match";
    private static final String ERROR_MESSAGE_PASSWORD_IS_NOT_VALID = "error.message.password.is.not.valid";
    private static final String ERROR_MESSAGE_EMAIL_IS_NOT_VALID = "error.message.email.is.not.valid";
    private static final String ERROR_MESSAGE_FIRST_NAME = "error.message.first.name";
    private static final String ERROR_MESSAGE_LAST_NAME = "error.message.last.name";
    private static final String ERROR_MESSAGE_PHONE_NUMBER = "error.message.phone.number";
    private final UserValidator userValidator;

    /**
     * Initializes a new UserRegistrationDtoValidatorImpl.
     */
    private UserRegistrationDtoValidatorImpl(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    /**
     * Gets the single instance of UserRegistrationDtoValidatorImpl.
     *
     * @return UserRegistrationDtoValidatorImpl
     */
    public static UserRegistrationDtoValidatorImpl getInstance() {
        return instance;
    }

    /**
     * Checks UserRegistrationDto.
     *
     * @param userRegistrationDto UserRegistrationDto
     * @return Map of String. Error map.
     */
    @Override
    public Map<String, String> checkUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        Map<String, String> errorMap = new HashMap<>();

        var username = userRegistrationDto.getUsername();
        if (!userValidator.checkUsername(username)) {
            errorMap.put(USERNAME_ERROR_MESSAGE, ERROR_MESSAGE_USERNAME_IS_NOT_VALID);
        }

        var password = userRegistrationDto.getPassword();
        var repeatedPassword = userRegistrationDto.getRepeatedPassword();
        if (password != null && !password.equals(repeatedPassword)) {
            errorMap.put(PASSWORD_ERROR_MESSAGE, ERROR_MESSAGE_PASSWORDS_DIDNT_MATCH);
        }
        if (!userValidator.checkPassword(password)) {
            errorMap.put(PASSWORD_ERROR_MESSAGE, ERROR_MESSAGE_PASSWORD_IS_NOT_VALID);
        }

        var email = userRegistrationDto.getEmail();
        if (!userValidator.checkEmail(email)) {
            errorMap.put(EMAIL_ERROR_MESSAGE, ERROR_MESSAGE_EMAIL_IS_NOT_VALID);
        }

        var firstName = userRegistrationDto.getFirstName();
        if (!userValidator.checkFirstname(firstName)) {
            errorMap.put(FIRST_NAME_ERROR_MESSAGE, ERROR_MESSAGE_FIRST_NAME);
        }

        var lastName = userRegistrationDto.getLastName();
        if (!userValidator.checkLastName(lastName)) {
            errorMap.put(LAST_NAME_ERROR_MESSAGE, ERROR_MESSAGE_LAST_NAME);
        }

        var phoneNumber = userRegistrationDto.getPhoneNumber();
        if (!userValidator.checkPhoneNumber(phoneNumber)) {
            errorMap.put(PHONE_NUMBER_ERROR_MESSAGE, ERROR_MESSAGE_PHONE_NUMBER);
        }

        return errorMap;
    }
}
