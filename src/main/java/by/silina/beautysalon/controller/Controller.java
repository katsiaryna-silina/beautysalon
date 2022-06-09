package by.silina.beautysalon.controller;

import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.CommandType;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    private static final String WRONG_ROUTER_TYPE = "Wrong Router type.";
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(request, response);
    }

    private void processCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE_TEXT_HTML);

        SessionRequestContent sessionRequestContent = new SessionRequestContent(request);
        String parameter = sessionRequestContent.getParameterByName(COMMAND);

        Command command = CommandType.of(parameter);

        try {
            Router router = command.execute(sessionRequestContent);
            switch (router.getType()) {
                case FORWARD -> {
                    //forward - pass request
                    sessionRequestContent.insertAttributes(request); //todo
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPage());
                    requestDispatcher.forward(request, response);//todo
                }
                case REDIRECT -> {
                    //sendRedirect - destroy request
                    sessionRequestContent.insertAttributes(request);
                    response.sendRedirect(router.getPage());
                }
                default -> {
                    log.error("Wrong Router type = {}", router.getType());
                    response.sendError(500, WRONG_ROUTER_TYPE);
                }
                //response.sendError(500, "text message"); //1
                //throw new ServletException("ass", e); //2
                    /* request.setAttribute("error_msg", e.getCause()); //3
                       request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response);*/
            }
        } catch (CommandException e) {
            log.error("", e);
            response.sendError(500, e.getMessage());
        }
    }
}
