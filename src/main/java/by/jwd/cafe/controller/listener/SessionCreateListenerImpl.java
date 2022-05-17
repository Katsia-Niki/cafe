package by.jwd.cafe.controller.listener;

import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.SessionAttribute;
import by.jwd.cafe.pool.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
    static Logger logger = LogManager.getLogger();
    private static String LOCAL_ATTRIBUTE = "local";
    private static String DEFAULT_LOCAL = "en";
      @Override
    public void sessionCreated(HttpSessionEvent se) {
            HttpSession session = se.getSession();
            session.setAttribute(LOCAL_ATTRIBUTE, DEFAULT_LOCAL);
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.INDEX);
            logger.log(Level.INFO, "Session was created: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        logger.log(Level.INFO, "Session was destroyed: " + se.getSession().getId());
    }
}
