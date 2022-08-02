package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.OrderFeedbackMapper;
import by.silina.beautysalon.mapper.impl.OrderFeedbackMapperImpl;
import by.silina.beautysalon.service.OrderFeedbackService;
import by.silina.beautysalon.service.impl.OrderFeedbackServiceImpl;

import java.util.Map;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.*;

/**
 * The UpdateFeedbackCommand class for update feedback command.
 *
 * @author Silina Katsiaryna
 */
public class UpdateFeedbackCommand implements Command {

    /**
     * Executes update feedback command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderFeedbackService feedbackService = OrderFeedbackServiceImpl.getInstance();
        OrderFeedbackMapper orderFeedbackMapper = OrderFeedbackMapperImpl.getInstance();

        var feedbackId = Long.valueOf(sessionRequestContent.getParameterByName(ID));

        Router router;
        try {
            var feedbackDtoOptional = feedbackService.findById(feedbackId);
            if (feedbackDtoOptional.isPresent()) {
                var newFeedbackDto = orderFeedbackMapper.toDto(sessionRequestContent);

                Map<String, String> errorMap = feedbackService.updateFeedback(newFeedbackDto);
                if (!errorMap.isEmpty()) {
                    var feedbackDto = feedbackDtoOptional.get();

                    fillRequestAttributesFrom(errorMap, sessionRequestContent);
                    sessionRequestContent.putRequestAttribute(CURRENT_FEEDBACK, feedbackDto.getFeedback());
                    sessionRequestContent.putRequestAttribute(CURRENT_MARK, feedbackDto.getMark());

                    router = new Router(UPDATE_FEEDBACK, Router.Type.FORWARD);
                } else {
                    router = new Router(UPDATE_FEEDBACK_SUCCESS, Router.Type.REDIRECT);
                }
            } else {
                router = new Router(UPDATE_FEEDBACK_FAILED, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
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
