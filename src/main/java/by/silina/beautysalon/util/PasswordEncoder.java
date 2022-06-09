package by.silina.beautysalon.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordEncoder {
    private static final int ENCODER_COST = 12;

    private PasswordEncoder() {
    }

    public static String encode(final String password) {
        return BCrypt.withDefaults().hashToString(ENCODER_COST, password.toCharArray());
    }

    public static boolean verifyPasswords(String passwordFromDto, String passwordFromDB) {
        BCrypt.Result result = BCrypt.verifyer().verify(passwordFromDto.toCharArray(), passwordFromDB);
        return result.verified;
    }
}
