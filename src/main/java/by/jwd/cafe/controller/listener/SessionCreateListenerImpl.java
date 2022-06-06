package by.jwd.cafe.controller.listener;

import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.entity.MenuItem;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static by.jwd.cafe.command.SessionAttribute.LOCALE;
import static by.jwd.cafe.command.SessionAttribute.CURRENT_PAGE;
import static by.jwd.cafe.command.SessionAttribute.CART;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
    private static String DEFAULT_LOCALE = "ru_RU";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Map<MenuItem, Integer> cart = new HashMap<>();
        session.setAttribute(CART, cart);
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(CURRENT_PAGE, PagePath.INDEX);
    }
}
