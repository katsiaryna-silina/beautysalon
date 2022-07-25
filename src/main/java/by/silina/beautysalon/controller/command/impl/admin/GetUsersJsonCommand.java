package by.silina.beautysalon.controller.command.impl.admin;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.UserAuthorizedDto;
import by.silina.beautysalon.model.dto.UserListJsonDto;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.util.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.LIMIT;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.OFFSET;

/**
 * The GetUsersJsonCommand class for getting users json command by admin.
 *
 * @author Silina Katsiaryna
 */
public class GetUsersJsonCommand implements Command {

    /**
     * Executes get users json command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains jsonElement, type constant(JSON).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

        JsonElement userListJsonElement = null;
        try {
            long numberOfUsers = userService.findNumberOfUsers();
            if (numberOfUsers != 0L) {
                var numberOfUsersPerPage = Integer.valueOf(sessionRequestContent.getParameterByName(LIMIT));
                var fromUserId = Long.valueOf(sessionRequestContent.getParameterByName(OFFSET));
                List<UserAuthorizedDto> pagedUserDtoList = userService.findPagedUserDtoList(fromUserId, numberOfUsersPerPage);

                UserListJsonDto userListJsonDto = UserListJsonDto.builder()
                        .recordsTotal(numberOfUsers)
                        .recordsFiltered(numberOfUsers)
                        .rows(pagedUserDtoList)
                        .build();
                Gson gson = GsonLocalDateTimeAdapter.createGson();
                userListJsonElement = gson.toJsonTree(userListJsonDto);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(userListJsonElement, Router.Type.JSON);
    }
}
