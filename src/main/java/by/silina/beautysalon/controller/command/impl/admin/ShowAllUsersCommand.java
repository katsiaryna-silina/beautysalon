package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.util.PageCounter;

import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.ALL_USERS;

public class ShowAllUsersCommand implements Command {
    private static final int DEFAULT_USERS_NUMBER_PER_PAGE = 10;

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

        Integer numberOfUsersPerPage = (Integer) sessionRequestContent.getRequestAttributeByName(NUMBER_OF_USERS_PER_PAGE);
        if (numberOfUsersPerPage == null) {
            numberOfUsersPerPage = DEFAULT_USERS_NUMBER_PER_PAGE;
        }

        Long fromUserId = (Long) sessionRequestContent.getRequestAttributeByName(FROM_USER_ID);
        if (fromUserId == null) {
            fromUserId = 0L;
        }

        try {
            long numberOfUsers = userService.findNumberOfUsers();
            if (numberOfUsers != 0L) {
                int numberOfPages = PageCounter.getNumberOfPages(numberOfUsers, numberOfUsersPerPage);
                sessionRequestContent.putRequestAttribute(NUMBER_OF_PAGES, numberOfPages);

                List<UserAuthorizedDto> pagedUserDtoList = userService.findPagedUserDtoList(fromUserId, numberOfUsersPerPage);
                sessionRequestContent.putRequestAttribute(USERS, pagedUserDtoList);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(ALL_USERS, Router.Type.FORWARD);
    }
}
