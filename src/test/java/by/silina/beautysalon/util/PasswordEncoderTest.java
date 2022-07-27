package by.silina.beautysalon.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The PasswordEncoderTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class PasswordEncoderTest {

    /**
     * Tests encoding password using BCrypt.
     */
    @Test
    void encode() {
        var password = "2222";
        String encodedPassword = PasswordEncoder.encode(password);
        Assertions.assertTrue(BCrypt.verifyer().verify(password.toCharArray(), encodedPassword).verified);
    }

    /**
     * Tests verifying passwords.
     */
    @Test
    void verifyPasswords() {
        var password = "2222";
        var encodedPassword = PasswordEncoder.encode(password);
        var encodedInvalidPassword = PasswordEncoder.encode("3333");

        boolean result = PasswordEncoder.verifyPasswords(password, encodedPassword);
        Assertions.assertTrue(result);

        result = PasswordEncoder.verifyPasswords(password, encodedInvalidPassword);
        Assertions.assertFalse(result);
    }
}