package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.dto.UserRegistrationDto;
import by.silina.beautysalon.validator.UserRegistrationDtoValidator;
import by.silina.beautysalon.validator.UserValidator;
import by.silina.beautysalon.controller.command.AttributeAndParameterName;

import java.util.HashMap;
import java.util.Map;

public class UserRegistrationDtoValidatorImpl implements UserRegistrationDtoValidator {
    private static final UserRegistrationDtoValidatorImpl instance = new UserRegistrationDtoValidatorImpl();

    private UserRegistrationDtoValidatorImpl() {
    }

    public static UserRegistrationDtoValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public Map<String, String> checkUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        UserValidator userValidator = UserValidatorImpl.getInstance();

        Map<String, String> errorMap = new HashMap<>();

        var username = userRegistrationDto.getUsername();
        if (!userValidator.checkUsername(username)) {
            errorMap.put(AttributeAndParameterName.USERNAME_ERROR_MESSAGE, "Username is not valid, must be between 3 and 30 characters long. \n" +
                    "Use only Latin letters, numbers, underscore and minus sign.");
        }

        var password = userRegistrationDto.getPassword();
        var repeatedPassword = userRegistrationDto.getRepeatedPassword();
        if (password != null && !password.equals(repeatedPassword)) {
            errorMap.put(AttributeAndParameterName.PASSWORD_ERROR_MESSAGE, "Those passwords didn't match. Try again.");
        }
        if (!userValidator.checkPassword(password)) {
            errorMap.put(AttributeAndParameterName.PASSWORD_ERROR_MESSAGE, "Password is not valid, must be between 4 and 20 characters long. \n" +
                    "Use only Latin letters, numbers, underscore and minus sign.");
        }

        var email = userRegistrationDto.getEmail();
        if (!userValidator.checkEmail(email)) {
            errorMap.put(AttributeAndParameterName.EMAIL_ERROR_MESSAGE, "Email is not valid.");
        }

        var firstName = userRegistrationDto.getFirstName();
        if (!userValidator.checkFirstname(firstName)) {
            errorMap.put(AttributeAndParameterName.FIRST_NAME_ERROR_MESSAGE, "First name is not valid.");
        }

        var lastName = userRegistrationDto.getLastName();
        if (!userValidator.checkLastName(lastName)) {
            errorMap.put(AttributeAndParameterName.LAST_NAME_ERROR_MESSAGE, "Last name is not valid.");
        }

        var phoneNumber = userRegistrationDto.getPhoneNumber();
        if (!userValidator.checkPhoneNumber(phoneNumber)) {
            errorMap.put(AttributeAndParameterName.PHONE_NUMBER_ERROR_MESSAGE, "Phone number is not valid.");
        }

        return errorMap;
    }
}
