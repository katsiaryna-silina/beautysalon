package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.service.OrderFeedbackService;
import by.silina.beautysalon.service.impl.OrderFeedbackServiceImpl;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_FEEDBACK;
import static by.silina.beautysalon.controller.command.PagePath.UPDATE_FEEDBACK_FAILED;

/**
 * The ShowFeedbackCommand class for show feedback command.
 *
 * @author Silina Katsiaryna
 */
public class ShowFeedbackCommand implements Command {

    /**
     * Executes show feedback command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        OrderFeedbackService feedbackService = OrderFeedbackServiceImpl.getInstance();

        var feedbackId = Long.valueOf(sessionRequestContent.getParameterByName(ID));

        try {
            var feedbackDtoOptional = feedbackService.findById(feedbackId);
            if (feedbackDtoOptional.isEmpty()) {
                return new Router(UPDATE_FEEDBACK_FAILED, Router.Type.FORWARD);
            }
            var feedbackDto = feedbackDtoOptional.get();

            sessionRequestContent.putRequestAttribute(ID, feedbackId);
            sessionRequestContent.putRequestAttribute(CURRENT_FEEDBACK, feedbackDto.getFeedback());
            sessionRequestContent.putRequestAttribute(CURRENT_MARK, feedbackDto.getMark());

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(UPDATE_FEEDBACK, Router.Type.FORWARD);
    }
}
