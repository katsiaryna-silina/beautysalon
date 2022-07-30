package by.silina.beautysalon.validator.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * The UserValidatorImplTest test class.
 *
 * @author Silina Katsiaryna
 */
class UserValidatorImplTest {
    UserValidatorImpl userValidator = UserValidatorImpl.getInstance();

    /**
     * Creates valid usernames.
     */
    static Stream<Arguments> createValidUsernames() {
        return Stream.of(
                arguments("user"),
                arguments("user12345678912345678912345678"),
                arguments("murzic77"),
                arguments("cat_lover"),
                arguments("mrs-fur-owner")
        );
    }

    /**
     * Creates invalid usernames.
     */
    static Stream<Arguments> createInvalidUsernames() {
        return Stream.of(
                arguments("mr"),
                arguments("user123456789123456789123456789"),
                arguments("murzic.77"),
                arguments("cat_lover!"),
                arguments("mrs=fur=owner"),
                arguments("..."),
                arguments("    "),
                arguments(""),
                arguments("=_="),
                arguments("%^&@")
        );
    }

    /**
     * Creates valid passwords.
     */
    static Stream<Arguments> createValidPasswords() {
        return Stream.of(
                arguments("pass"),
                arguments("password123456789123"),
                arguments("pass_word"),
                arguments("pass_word-"),
                arguments("pass_123-word8"),
                arguments("645392938"),
                arguments("8371023-321")
        );
    }

    /**
     * Creates invalid passwords.
     */
    static Stream<Arguments> createInvalidPasswords() {
        return Stream.of(
                arguments("pw"),
                arguments("password1234567891234"),
                arguments("=_="),
                arguments("%^&@"),
                arguments("..."),
                arguments("    "),
                arguments("")
        );
    }

    /**
     * Creates valid email.
     */
    static Stream<Arguments> createValidEmails() {
        return Stream.of(
                arguments("cat@gmail.com"),
                arguments("koshak@mail.ru")
        );
    }

    /**
     * Creates invalid email.
     */
    static Stream<Arguments> createInvalidEmails() {
        return Stream.of(
                arguments("m"),
                arguments("email"),
                arguments("e@gmail.com"),
                arguments("email@gmail.comcomcom"),
                arguments("=_="),
                arguments("%^&@"),
                arguments("..."),
                arguments("    "),
                arguments("")
        );
    }

    /**
     * Creates valid first name.
     */
    static Stream<Arguments> createValidFirstName() {
        return Stream.of(
                arguments("Li"),
                arguments("Bob"),
                arguments("To Li"),
                arguments("Markus"),
                arguments("Anna-Maria"),
                arguments("la Roshe")
        );
    }

    /**
     * Creates invalid first name.
     */
    static Stream<Arguments> createInvalidFirstName() {
        return Stream.of(
                arguments("A"),
                arguments("A177"),
                arguments("Markusissimusmarmarmarkusisimusus"),
                arguments("=_="),
                arguments("%^&@"),
                arguments("..."),
                arguments("    "),
                arguments("")
        );
    }

    /**
     * Creates valid last name.
     */
    static Stream<Arguments> createValidLastName() {
        return Stream.of(
                arguments("Ho"),
                arguments("Car"),
                arguments("Un Mi"),
                arguments("Tarasova"),
                arguments("Ivanov-Cotov"),
                arguments("la Pari")
        );
    }

    /**
     * Creates invalid last name.
     */
    static Stream<Arguments> createInvalidLastName() {
        return Stream.of(
                arguments("V"),
                arguments("V78"),
                arguments("Torkusissitustartarmarkusisitusus"),
                arguments("=_="),
                arguments("%^&@"),
                arguments("..."),
                arguments("    "),
                arguments("")
        );
    }

    /**
     * Creates valid phone number.
     */
    static Stream<Arguments> createValidPhoneNumber() {
        return Stream.of(
                arguments("+1234567"),
                arguments("+12345678901234567890"),
                arguments("12345678901234567890"),
                arguments("3752988")
        );
    }

    /**
     * Creates invalid phone number.
     */
    static Stream<Arguments> createInvalidPhoneNumber() {
        return Stream.of(
                arguments("N"),
                arguments("N7"),
                arguments("+123"),
                arguments("+123456789012345678901"),
                arguments("1234567890123456789012"),
                arguments("%^&@"),
                arguments("..."),
                arguments("    "),
                arguments("")
        );
    }

    /**
     * Tests checking valid username.
     */
    @ParameterizedTest
    @MethodSource("createValidUsernames")
    void checkValidUsername(String username) {
        var actualResult = userValidator.checkUsername(username);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid username.
     */
    @ParameterizedTest
    @MethodSource("createInvalidUsernames")
    void checkInvalidUsername(String username) {
        var actualResult = userValidator.checkUsername(username);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking null username.
     */
    @Test
    void checkNullUsername() {
        var actualResult = userValidator.checkUsername(null);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking valid password.
     */
    @ParameterizedTest
    @MethodSource("createValidPasswords")
    void checkValidPassword(String password) {
        var actualResult = userValidator.checkPassword(password);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid password.
     */
    @ParameterizedTest
    @MethodSource("createInvalidPasswords")
    void checkInvalidPassword(String password) {
        var actualResult = userValidator.checkPassword(password);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking null password.
     */
    @Test
    void checkNullPassword() {
        var actualResult = userValidator.checkPassword(null);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking valid email.
     */
    @ParameterizedTest
    @MethodSource("createValidEmails")
    void checkValidEmail(String email) {
        var actualResult = userValidator.checkEmail(email);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid email.
     */
    @ParameterizedTest
    @MethodSource("createInvalidEmails")
    void checkInvalidEmail(String email) {
        var actualResult = userValidator.checkEmail(email);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking null email.
     */
    @Test
    void checkNullEmail() {
        var actualResult = userValidator.checkEmail(null);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking valid first name.
     */
    @ParameterizedTest
    @MethodSource("createValidFirstName")
    void checkValidFirstname(String firstName) {
        var actualResult = userValidator.checkFirstname(firstName);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid first name.
     */
    @ParameterizedTest
    @MethodSource("createInvalidFirstName")
    void checkInvalidFirstname(String firstName) {
        var actualResult = userValidator.checkFirstname(firstName);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking null first name.
     */
    @Test
    void checkNullFirstname() {
        var actualResult = userValidator.checkFirstname(null);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking valid last name.
     */
    @ParameterizedTest
    @MethodSource("createValidLastName")
    void checkValidLastName(String lastName) {
        var actualResult = userValidator.checkLastName(lastName);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid last name.
     */
    @ParameterizedTest
    @MethodSource("createInvalidLastName")
    void checkInvalidLastName(String lastName) {
        var actualResult = userValidator.checkLastName(lastName);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking null last name.
     */
    @Test
    void checkNullLastName() {
        var actualResult = userValidator.checkLastName(null);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking valid phone number.
     */
    @ParameterizedTest
    @MethodSource("createValidPhoneNumber")
    void checkValidPhoneNumber(String phoneNumber) {
        var actualResult = userValidator.checkPhoneNumber(phoneNumber);
        Assertions.assertTrue(actualResult);
    }

    /**
     * Tests checking invalid phone number.
     */
    @ParameterizedTest
    @MethodSource("createInvalidPhoneNumber")
    void checkInvalidPhoneNumber(String phoneNumber) {
        var actualResult = userValidator.checkPhoneNumber(phoneNumber);
        Assertions.assertFalse(actualResult);
    }

    /**
     * Tests checking null phone number.
     */
    @Test
    void checkNullPhoneNumber() {
        var actualResult = userValidator.checkPhoneNumber(null);
        Assertions.assertFalse(actualResult);
    }
}
