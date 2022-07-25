package by.silina.beautysalon.validator;

/**
 * The UserValidator interface.
 *
 * @author Silina Katsiaryna
 */
public interface UserValidator {

    /**
     * Checks username.
     *
     * @param username String
     * @return boolean. True if username mark is correct; false otherwise.
     */
    boolean checkUsername(String username);

    /**
     * Checks password.
     *
     * @param password String
     * @return boolean. True if password mark is correct; false otherwise.
     */
    boolean checkPassword(String password);

    /**
     * Checks email.
     *
     * @param email String
     * @return boolean. True if email mark is correct; false otherwise.
     */
    boolean checkEmail(String email);

    /**
     * Checks firstName.
     *
     * @param firstName String
     * @return boolean. True if firstName mark is correct; false otherwise.
     */
    boolean checkFirstname(String firstName);

    /**
     * Checks lastName.
     *
     * @param lastName String
     * @return boolean. True if lastName mark is correct; false otherwise.
     */
    boolean checkLastName(String lastName);

    /**
     * Checks phoneNumber.
     *
     * @param phoneNumber String
     * @return boolean. True if phoneNumber mark is correct; false otherwise.
     */
    boolean checkPhoneNumber(String phoneNumber);

}
