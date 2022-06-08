package by.jwd.cafe.controller.filter;


import by.jwd.cafe.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static by.jwd.cafe.command.PagePath.*;
import static by.jwd.cafe.command.SessionAttribute.CURRENT_ROLE;

@WebFilter(urlPatterns = {"/pages/*"})
public class PageSecurityFilter implements Filter {
    static Logger logger = LogManager.getLogger();
    private Set<String> guestPages;
    private Set<String> customerPages;
    private Set<String> adminPages;
    private Set<String> allPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestPages = Set.of(LOGIN,
                MAIN,
                MENU,
                REGISTRATION,
                CONTACT);
        customerPages = Set.of(CONTACT,
                CONFIRMED_ORDER,
                CART,
                CUSTOMER_ACCOUNT,
                CHANGE_PASSWORD,
                HOME,
                LOGIN,
                MAIN,
                MENU,
                PLACE_ORDER,
                REGISTRATION,
                REFILL_BALANCE);
        adminPages = Set.of(ADMIN_ACCOUNT,
                CONTACT,
                CHANGE_PASSWORD,
                HOME,
                LOGIN,
                MAIN,
                MENU,
                REGISTRATION,
                USERS);
        allPages = new HashSet<>();
        allPages.addAll(guestPages);
        allPages.addAll(customerPages);
        allPages.addAll(adminPages);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Start PageSecurityFilter");//fixme
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getServletPath();
        boolean isPageExist = allPages.stream().anyMatch(requestURI::contains);

        if (isPageExist) {
            HttpSession session = request.getSession();
            UserRole role = session.getAttribute(CURRENT_ROLE) == null
                    ? UserRole.GUEST
                    : UserRole.valueOf(session.getAttribute(CURRENT_ROLE).toString());
            boolean isAcceptable =
                    switch (role) {
                        case CUSTOMER -> customerPages.stream().anyMatch(requestURI::contains);
                        case ADMIN -> adminPages.stream().anyMatch(requestURI::contains);
                        default -> guestPages.stream().anyMatch(requestURI::contains);
                    };
            if (!isAcceptable) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("End PageSecurityFilter");//fixme
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
