package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.model.dto.UserPasswordsDto;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.util.MessageLocalizer;

import java.util.Map;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.LOCALE;
import static by.silina.beautysalon.controller.command.PagePath.CHANGE_PASSWORD;
import static by.silina.beautysalon.controller.command.PagePath.SUCCESS_CHANGE_PASSWORD;

/**
 * The ChangeUserPasswordCommand class for change user's password command.
 *
 * @author Silina Katsiaryna
 */
public class ChangeUserPasswordCommand implements Command {

    /**
     * Executes change user's password command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        UserPasswordsDto userPasswordsDto = userMapper.toUserPasswordsDto(sessionRequestContent);
        var page = CHANGE_PASSWORD;
        try {
            Map<String, String> errorMap = userService.changePassword(userPasswordsDto);
            if (errorMap.isEmpty()) {
                page = SUCCESS_CHANGE_PASSWORD;
            } else {
                var sessionLocale = (String) sessionRequestContent.getSessionAttributeByName(LOCALE);
                MessageLocalizer.localize(errorMap, sessionLocale);
                
                fillRequestAttributesFrom(errorMap, sessionRequestContent);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, Router.Type.FORWARD);
    }

    /**
     * Fills request attributes.
     *
     * @param errorMap              Map. Contains data of errors.
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     */
    private void fillRequestAttributesFrom(Map<String, String> errorMap, SessionRequestContent sessionRequestContent) {
        errorMap.forEach(sessionRequestContent::putRequestAttribute);
    }
}
