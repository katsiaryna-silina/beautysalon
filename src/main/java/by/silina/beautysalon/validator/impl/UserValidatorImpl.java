package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.validator.UserValidator;

/**
 * The UserValidatorImpl class that responsible for validation User.
 *
 * @author Silina Katsiaryna
 */
public class UserValidatorImpl implements UserValidator {
    private static final String USERNAME_REGEX = "^[\\w-]{3,30}$";
    private static final String PASSWORD_REGEX = "^[\\w-]{4,20}$";
    private static final String EMAIL_REGEX = "^(\\w[^_])+([\\w-\\.]*)@([\\w-\\.]+)\\.[\\w[^_]]{2,6}$";
    private static final String FIRST_AND_LAST_NAME_REGEX = "^[^ \\-][a-zA-Z\\- ]{0,30}[^ \\-]$";
    private static final String PHONE_NUMBER_REGEX = "^\\+?\\d{7,20}$";
    private static UserValidatorImpl instance;

    /**
     * Initializes a new UserValidatorImpl.
     */
    private UserValidatorImpl() {
    }

    /**
     * Gets the single instance of UserValidatorImpl.
     *
     * @return UserValidatorImpl
     */
    public static UserValidatorImpl getInstance() {
        if (instance == null) {
            instance = new UserValidatorImpl();
        }
        return instance;
    }

    /**
     * Checks username.
     *
     * @param username String
     * @return boolean. True if username mark is correct; false otherwise.
     */
    @Override
    public boolean checkUsername(String username) {
        return username != null && username.matches(USERNAME_REGEX);
    }

    /**
     * Checks password.
     *
     * @param password String
     * @return boolean. True if password mark is correct; false otherwise.
     */
    @Override
    public boolean checkPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    /**
     * Checks email.
     *
     * @param email String
     * @return boolean. True if email mark is correct; false otherwise.
     */
    @Override
    public boolean checkEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    /**
     * Checks firstName.
     *
     * @param firstName String
     * @return boolean. True if firstName mark is correct; false otherwise.
     */
    @Override
    public boolean checkFirstname(String firstName) {
        return firstName != null && firstName.matches(FIRST_AND_LAST_NAME_REGEX);
    }

    /**
     * Checks lastName.
     *
     * @param lastName String
     * @return boolean. True if lastName mark is correct; false otherwise.
     */
    @Override
    public boolean checkLastName(String lastName) {
        return lastName != null && lastName.matches(FIRST_AND_LAST_NAME_REGEX);
    }

    /**
     * Checks phoneNumber.
     *
     * @param phoneNumber String
     * @return boolean. True if phoneNumber mark is correct; false otherwise.
     */
    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches(PHONE_NUMBER_REGEX);
    }
}
