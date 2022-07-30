package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.validator.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The UserPasswordsDtoValidatorImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class UserPasswordsDtoValidatorImplTest {
    @InjectMocks
    UserPasswordsDtoValidatorImpl userPasswordsDtoValidator;
    @Mock
    UserValidator userValidator;

    /**
     * Tests checking valid UserPasswordsDto.
     */
    @Test
    void checkValidUserPasswordsDto() {
        var dto = UserPasswordsDto.builder()
                .currentPassword("1234")
                .newPassword("1111")
                .repeatedNewPassword("1111")
                .build();

        Mockito.when(userValidator.checkPassword(Mockito.anyString())).thenReturn(true);

        var actualErrorMap = userPasswordsDtoValidator.checkUserPasswordsDto(dto);
        Assertions.assertTrue(actualErrorMap.isEmpty());
    }

    /**
     * Tests checking invalid UserPasswordsDto.
     */
    @Test
    void checkInvalidUserPasswordsDto() {
        var dto = UserPasswordsDto.builder()
                .currentPassword("1")
                .newPassword("_")
                .repeatedNewPassword("_")
                .build();

        Mockito.when(userValidator.checkPassword(Mockito.anyString())).thenReturn(false);

        var actualErrorMap = userPasswordsDtoValidator.checkUserPasswordsDto(dto);
        Assertions.assertFalse(actualErrorMap.isEmpty());
    }
}
