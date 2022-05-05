package by.jwd.cafe.controller.filter;

import by.jwd.cafe.command.CommandType;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.RequestParameter;
import by.jwd.cafe.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.jwd.cafe.command.SessionAttribute.CURRENT_ROLE;

import java.io.IOException;
import java.util.EnumSet;

@WebFilter(filterName = "PreControllerFilter", urlPatterns = "/controller")
public class PreControllerFilter implements Filter {
    static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        String commandName = httpServletRequest.getParameter(RequestParameter.COMMAND);
        if (commandName == null) {
            httpServletResponse.sendRedirect(PagePath.INDEX);
        }
        try {
            CommandType command = CommandType.valueOf(commandName.toUpperCase());
            UserRole userRole = session.getAttribute(CURRENT_ROLE) == null
                    ? UserRole.GUEST
                    : UserRole.valueOf(session.getAttribute(CURRENT_ROLE).toString());
            EnumSet<UserRole> roles = command.getAcceptableRole();
            System.out.println(session.getAttribute(CURRENT_ROLE)); //fixme
            if (roles.contains(userRole)) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }
}
