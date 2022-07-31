package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.UserDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.User;
import by.silina.beautysalon.model.entity.UserStatus;
import by.silina.beautysalon.util.PasswordEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static by.silina.beautysalon.mapper.impl.OrderMapperImpl.DATE_TIME_FORMAT;

/**
 * The UserServiceImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserDaoImpl userDao;

    /**
     * Tests finding user using UserLoginDto.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findUser() throws DaoException, ServiceException {
        var dto = UserLoginDto.builder()
                .username("kitty")
                .password("1234")
                .build();

        var user = User.builder()
                .username(dto.getUsername())
                .password(PasswordEncoder.encode(dto.getPassword()))
                .build();
        var expectedResult = Optional.of(user);

        Mockito.when(userDao.findUserByUsername(dto.getUsername())).thenReturn(expectedResult);

        var actualResult = userService.findUser(dto);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding user by its username.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findUserByUsername() throws DaoException, ServiceException {
        var username = "kitty";

        var user = User.builder()
                .username(username)
                .password(PasswordEncoder.encode("1111"))
                .build();
        var expectedResult = Optional.of(user);

        Mockito.when(userDao.findUserByUsername(username)).thenReturn(expectedResult);

        var actualResult = userService.findUser(username);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests adding new user.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void addUser() throws DaoException, ServiceException {
        var dto = UserRegistrationDto.builder()
                .username("client")
                .password("12345")
                .repeatedPassword("12345")
                .email("client@gmail.com")
                .firstName("Cli")
                .lastName("Ent")
                .phoneNumber("+234781634")
                .build();

        Mockito.when(userDao.insert(Mockito.any(User.class))).thenReturn(true);

        var actualErrorMap = userService.addUser(dto);
        Assertions.assertTrue(actualErrorMap.isEmpty());
    }

    /**
     * Tests changing password.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void changePassword() throws DaoException, ServiceException {
        var userId = 5L;
        var currentPassword = "1234";
        var newPassword = "1111";
        var dto = UserPasswordsDto.builder()
                .userId(userId)
                .currentPassword(currentPassword)
                .newPassword(newPassword)
                .repeatedNewPassword(newPassword)
                .build();

        var encodedPassword = PasswordEncoder.encode(currentPassword);

        Mockito.when(userDao.findUserPasswordById(userId)).thenReturn(Optional.of(encodedPassword));
        Mockito.when(userDao.changeUserPassword(Mockito.eq(userId), Mockito.anyString())).thenReturn(true);

        var actualErrorMap = userService.changePassword(dto);
        Assertions.assertTrue(actualErrorMap.isEmpty());
    }

    /**
     * Tests finding number of users.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findNumberOfUsers() throws DaoException, ServiceException {
        var users = List.of(User.builder().build(),
                User.builder().build());
        long expectedResult = users.size();

        Mockito.when(userDao.findNumberOfUsers()).thenReturn(expectedResult);

        var actualResult = userService.findNumberOfUsers();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding paged UserAuthorizedDto list.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findPagedUserDtoList() throws DaoException, ServiceException {
        Long fromUserId = 1L;
        var user1 = User.builder()
                .id(fromUserId)
                .username("username1")
                .email("user1@gmail.com")
                .firstName("User")
                .lastName("Resu")
                .phoneNumber("+27364012")
                .role(Role.CLIENT)
                .discountStatus(DiscountStatus.builder().status("status").build())
                .userStatus(UserStatus.ACTIVE)
                .lastLogin(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .build();

        var user2 = User.builder()
                .id(2L)
                .username("username2")
                .email("user2@gmail.com")
                .firstName("Usue")
                .lastName("Reir")
                .phoneNumber("+27377012")
                .role(Role.CLIENT)
                .discountStatus(DiscountStatus.builder().status("status").build())
                .userStatus(UserStatus.ACTIVE)
                .lastLogin(LocalDateTime.parse("2022-07-30 16:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .build();

        var dto1 = UserAuthorizedDto.builder()
                .id(user1.getId())
                .username(user1.getUsername())
                .email(user1.getEmail())
                .firstName(user1.getFirstName())
                .lastName(user1.getLastName())
                .phoneNumber(user1.getPhoneNumber())
                .role(user1.getRole())
                .discountStatus(user1.getDiscountStatus())
                .userStatus(user1.getUserStatus())
                .lastLogin(user1.getLastLogin())
                .build();

        var dto2 = UserAuthorizedDto.builder()
                .id(user2.getId())
                .username(user2.getUsername())
                .email(user2.getEmail())
                .firstName(user2.getFirstName())
                .lastName(user2.getLastName())
                .phoneNumber(user2.getPhoneNumber())
                .role(user2.getRole())
                .discountStatus(user2.getDiscountStatus())
                .userStatus(user2.getUserStatus())
                .lastLogin(user2.getLastLogin())
                .build();
        var numberOfUsers = 2;
        var users = List.of(user1, user2);
        var expectedResult = List.of(dto1, dto2);

        Mockito.when(userDao.findPagedUsers(fromUserId, numberOfUsers)).thenReturn(users);

        var actualResult = userService.findPagedUserDtoList(fromUserId, numberOfUsers);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests changing discount.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void changeDiscount() throws DaoException, ServiceException {
        var userId = 1L;
        var discountName = "disc name";

        Mockito.when(userDao.changeDiscount(userId, discountName)).thenReturn(true);

        var isDiscountChanged = userService.changeDiscount(userId, discountName);
        Assertions.assertTrue(isDiscountChanged);
    }

    /**
     * Tests changing user role and discount.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void changeUserRoleAndDiscount() throws DaoException, ServiceException {
        var userId = 1L;
        var discountName = "disc name";
        var role = Role.ADMIN;

        Mockito.when(userDao.changeUserRole(userId, role)).thenReturn(true);
        Mockito.when(userDao.changeDiscount(userId, discountName)).thenReturn(true);

        var isDiscountAndRoleChanged = userService.changeUserRoleAndDiscount(userId, role, discountName);
        Assertions.assertTrue(isDiscountAndRoleChanged);
    }

    /**
     * Tests changing user status.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void changeUserStatus() throws DaoException, ServiceException {
        var userId = 1L;
        var userStatus = UserStatus.BLOCKED;

        Mockito.when(userDao.changeUserStatus(userId, userStatus)).thenReturn(true);

        var isStatusChanged = userService.changeUserStatus(userId, userStatus);
        Assertions.assertTrue(isStatusChanged);
    }

    /**
     * Tests deleting.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void deleteUser() throws DaoException, ServiceException {
        var userId = 1L;

        Mockito.when(userDao.deleteById(userId)).thenReturn(true);

        var isUserDeleted = userService.deleteUser(userId);
        Assertions.assertTrue(isUserDeleted);
    }
}
