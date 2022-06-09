package by.epam.silina.validator;

public interface UserValidator {
    boolean checkUsername(String username);

    boolean checkPassword(String password);

    boolean checkEmail(String email);

    boolean checkFirstname(String firstName);

    boolean checkLastName(String lastName);

    boolean checkPhoneNumber(String phoneNumber);

}
