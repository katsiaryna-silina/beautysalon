package by.silina.beautysalon.validator.impl;

import by.silina.beautysalon.model.dto.UserRegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The UserRegistrationDtoValidatorImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class UserRegistrationDtoValidatorImplTest {
    @InjectMocks
    UserRegistrationDtoValidatorImpl userRegistrationDtoValidator;
    @Mock
    UserValidatorImpl userValidator;

    /**
     * Tests checking valid UserRegistrationDto.
     */
    @Test
    void checkValidUserRegistrationDto() {
        var dto = UserRegistrationDto.builder()
                .username("client")
                .password("12345")
                .repeatedPassword("12345")
                .email("client@gmail.com")
                .firstName("Cli")
                .lastName("Ent")
                .phoneNumber("+234781634")
                .build();

        Mockito.when(userValidator.checkUsername(Mockito.anyString())).thenReturn(true);
        Mockito.when(userValidator.checkPassword(Mockito.anyString())).thenReturn(true);
        Mockito.when(userValidator.checkEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(userValidator.checkFirstname(Mockito.anyString())).thenReturn(true);
        Mockito.when(userValidator.checkLastName(Mockito.anyString())).thenReturn(true);
        Mockito.when(userValidator.checkPhoneNumber(Mockito.anyString())).thenReturn(true);

        var actualErrorMap = userRegistrationDtoValidator.checkUserRegistrationDto(dto);
        Assertions.assertTrue(actualErrorMap.isEmpty());
    }

    /**
     * Tests checking invalid UserRegistrationDto.
     */
    @Test
    void checkInvalidUserRegistrationDto() {
        var dto = UserRegistrationDto.builder()
                .username("_")
                .password("78")
                .repeatedPassword("78")
                .email("mail")
                .firstName("A")
                .lastName("T")
                .phoneNumber("82")
                .build();

        Mockito.when(userValidator.checkUsername(Mockito.anyString())).thenReturn(false);
        Mockito.when(userValidator.checkPassword(Mockito.anyString())).thenReturn(false);
        Mockito.when(userValidator.checkEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userValidator.checkFirstname(Mockito.anyString())).thenReturn(false);
        Mockito.when(userValidator.checkLastName(Mockito.anyString())).thenReturn(false);
        Mockito.when(userValidator.checkPhoneNumber(Mockito.anyString())).thenReturn(false);

        var actualErrorMap = userRegistrationDtoValidator.checkUserRegistrationDto(dto);
        Assertions.assertFalse(actualErrorMap.isEmpty());
    }
}
