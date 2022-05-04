package by.epam.silina.controller;

import by.epam.silina.command.Command;
import by.epam.silina.command.CommandType;
import by.epam.silina.command.Router;
import by.epam.silina.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static by.epam.silina.controller.Constant.*;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void init() {
        //ConnectionPool.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processCommand(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(request, response);
    }

    @Override
    public void destroy() {
        //ConnectionPool.getInstance().destroyPool();
    }

    private void processCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE_TEXT_HTML);

        String commandStr = request.getParameter(COMMAND);
        Command command = CommandType.define(commandStr);
        Router router;
        try {
            router = command.execute(request);

            switch (router.getType()) {
                case FORWARD:
                    //forward - pass request
                    request.getRequestDispatcher(router.getPage()).forward(request, response);
                    break;
                case REDIRECT:
                    //sendRedirect - destroy request
                    response.sendRedirect(router.getPage());
                    break;
                default:
                    log.error("Wrong Router type");
                    response.sendError(500, WRONG_ROUTER_TYPE); //1
                    //response.sendError(500, "text message"); //1
                    //throw new ServletException("ass", e); //2
                    /* request.setAttribute("error_msg", e.getCause()); //3
                       request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response);*/
            }
        } catch (CommandException e) {
            response.sendError(500, e.getMessage()); //1
        }
    }
}
