package by.jwd.cafe.controller;

import java.io.IOException;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.RequestParameter;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    public void init() {
        logger.log(Level.INFO, "Servlet init: " + this.getServletInfo());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); //todo убрать в фильтр
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.defineCommand(commandName);
        try {
            Router router = command.execute(request);
            String toPage = router.getPage();

            switch (router.getType()) {
                case FORWARD:
                    request.getRequestDispatcher(toPage).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(toPage);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (CommandException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    public void destroy() {
        logger.log(Level.INFO, "Servlet destroyed: " + this.getServletName());
    }
}