package by.jwd.cafe.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "PreControllerFilter", urlPatterns = "/controller")
public class PreControllerFilter implements Filter {
    static Logger logger = LogManager.getLogger();
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        logger.log(Level.INFO, "++++++++-Session in PreControllerFilter: " + session != null ? session.getId() : "Session not created");
        chain.doFilter(request, response);
    }

    public void destroy() {
    }


}
