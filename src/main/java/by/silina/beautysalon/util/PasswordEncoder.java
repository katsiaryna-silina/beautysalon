package by.silina.beautysalon.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * The PasswordEncoder class that responsible for encode and verify passwords using BCrypt.
 *
 * @author Silina Katsiaryna
 */
public class PasswordEncoder {
    private static final int ENCODER_COST = 12;

    /**
     * Initializes a new PasswordEncoder.
     */
    private PasswordEncoder() {
    }

    /**
     * Encodes passed password using BCrypt.
     *
     * @return String
     */
    public static String encode(final String password) {
        return BCrypt.withDefaults().hashToString(ENCODER_COST, password.toCharArray());
    }

    /**
     * Verifies passwords from dto and database using BCrypt.
     *
     * @return boolean. True if passwords are the same; false otherwise.
     */
    public static boolean verifyPasswords(String passwordFromDto, String passwordFromDB) {
        BCrypt.Result result = BCrypt.verifyer().verify(passwordFromDto.toCharArray(), passwordFromDB);
        return result.verified;
    }
}
