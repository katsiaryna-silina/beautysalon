package by.silina.beautysalon.controller;

import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.CommandType;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.COMMAND;
import static by.silina.beautysalon.controller.command.AttributeAndParameterName.CURRENT_PAGE;

/**
 * The Controller class.
 *
 * @author Silina Katsiaryna
 */
@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String WRONG_ROUTER_TYPE = "Wrong Router type.";

    /**
     * Does get.
     *
     * @param request  HttpServletRequest. The http servlet request.
     * @param response HttpServletResponse. The http servlet response.
     * @throws ServletException if a ServletException exception occurs.
     * @throws IOException      if a IOException exception occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(request, response);
    }

    /**
     * Does post.
     *
     * @param request  HttpServletRequest. The http servlet request.
     * @param response HttpServletResponse. The http servlet response.
     * @throws ServletException if a ServletException exception occurs.
     * @throws IOException      if a IOException exception occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(request, response);
    }

    /**
     * Processes command.
     *
     * @param request  HttpServletRequest. The http servlet request.
     * @param response HttpServletResponse. The http servlet response.
     * @throws ServletException if a ServletException exception occurs.
     * @throws IOException      if a IOException exception occurs.
     */
    private void processCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE_TEXT_HTML);

        SessionRequestContent sessionRequestContent = new SessionRequestContent(request);
        String parameter = sessionRequestContent.getParameterByName(COMMAND);

        Command command = CommandType.of(parameter);

        try {
            Router router = command.execute(sessionRequestContent);
            switch (router.getType()) {
                case REDIRECT -> {
                    var page = router.getPage();
                    sessionRequestContent.putSessionAttribute(CURRENT_PAGE, page);
                    sessionRequestContent.insertRequestAttributes(request);

                    response.sendRedirect(page);
                }
                case FORWARD -> {
                    var page = router.getPage();
                    sessionRequestContent.putSessionAttribute(CURRENT_PAGE, page);
                    sessionRequestContent.insertRequestAttributes(request);

                    var requestDispatcher = request.getRequestDispatcher(page);
                    requestDispatcher.forward(request, response);
                }
                case JSON -> {
                    String jsonString = router.getJsonElement().toString();
                    PrintWriter out = response.getWriter();
                    response.setContentType(CONTENT_TYPE_APPLICATION_JSON);
                    response.setCharacterEncoding(CHARACTER_ENCODING);
                    out.print(jsonString);
                    out.flush();
                }
                default -> {
                    log.error("Wrong Router type = {}", router.getType());
                    response.sendError(500, WRONG_ROUTER_TYPE);
                }
            }
        } catch (CommandException e) {
            log.error("", e);
            response.sendError(500, e.getMessage());
        }
    }
}
