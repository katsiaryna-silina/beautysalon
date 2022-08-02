package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.UserMapper;
import by.silina.beautysalon.mapper.impl.UserMapperImpl;
import by.silina.beautysalon.service.UserService;
import by.silina.beautysalon.service.impl.UserServiceImpl;
import by.silina.beautysalon.util.MessageLocalizer;

import java.util.Map;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.INDEX;
import static by.silina.beautysalon.controller.command.PagePath.REGISTRATION;

/**
 * The RegistrationCommand class for registration command.
 *
 * @author Silina Katsiaryna
 */
public class RegistrationCommand implements Command {
    private static final String HI_PROPERTY_KEY = "hi";
    private static final String DESCRIPTION_USER_REGISTERED_PROPERTY_KEY = "description.user.registered";
    private static final String EXCLAMATION = "! ";
    private static final String DESCRIPTION_LOGIN_KEY = "description.login";

    /**
     * Executes registration command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        UserMapper userMapper = UserMapperImpl.getInstance();

        var userRegistrationDto = userMapper.toUserRegistrationDto(sessionRequestContent);
        var page = REGISTRATION;
        Router.Type routerType;

        try {
            var errorMap = userService.addUser(userRegistrationDto);
            var sessionLocale = (String) sessionRequestContent.getSessionAttributeByName(LOCALE);

            if (errorMap.isEmpty()) {
                var message = new StringBuilder();
                var hiMessage = MessageLocalizer.getLocalizedMessage(HI_PROPERTY_KEY, sessionLocale);
                var userRegisteredMessage = MessageLocalizer.getLocalizedMessage(DESCRIPTION_USER_REGISTERED_PROPERTY_KEY, sessionLocale);
                message.append(hiMessage)
                        .append(userRegistrationDto.getFirstName())
                        .append(EXCLAMATION)
                        .append(userRegisteredMessage);
                sessionRequestContent.putSessionAttribute(WELCOME_MESSAGE, message.toString());

                var loginNeeded = MessageLocalizer.getLocalizedMessage(DESCRIPTION_LOGIN_KEY, sessionLocale);
                sessionRequestContent.putSessionAttribute(LOGIN_NEEDED_MESSAGE, loginNeeded);

                page = INDEX;
                routerType = Router.Type.REDIRECT;
            } else {
                MessageLocalizer.getLocalizedMessage(errorMap, sessionLocale);

                fillRequestAttributesFrom(errorMap, sessionRequestContent);
                sessionRequestContent.putRequestAttribute(USERNAME, userRegistrationDto.getFirstName());
                sessionRequestContent.putRequestAttribute(EMAIL, userRegistrationDto.getEmail());
                sessionRequestContent.putRequestAttribute(FIRST_NAME, userRegistrationDto.getFirstName());
                sessionRequestContent.putRequestAttribute(LAST_NAME, userRegistrationDto.getLastName());
                sessionRequestContent.putRequestAttribute(PHONE_NUMBER, userRegistrationDto.getPhoneNumber());
                routerType = Router.Type.FORWARD;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, routerType);
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
