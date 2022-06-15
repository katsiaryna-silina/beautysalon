package by.silina.beautysalon.service;

import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserLoginDto;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.model.dto.UserRegistrationDto;
import by.silina.beautysalon.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(UserLoginDto userLoginDto) throws ServiceException;

    Map<String, String> addUser(UserRegistrationDto userRegistrationDto) throws ServiceException;

    Optional<String> findUserPasswordById(Long userId) throws ServiceException;

    Map<String, String> changePassword(UserPasswordsDto userPasswordsDto) throws ServiceException;

    long findNumberOfUsers() throws ServiceException;

    List<UserAuthorizedDto> findPagedUserDtoList(Long fromUserId, Integer numberOfUsers) throws ServiceException;
}
