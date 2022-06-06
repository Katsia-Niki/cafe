package by.jwd.cafe.command.impl.customer.to;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.service.MenuItemService;
import by.jwd.cafe.service.impl.MenuItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static by.jwd.cafe.command.SessionAttribute.*;

public class GoToPlaceOrderPageCommand implements Command {
    private static final long ORDER_NOT_EARLIER_TIME_IN_MINUTES = 30;
    private static final long ORDER_NOT_LATER_TIME_IN_HOURS = 72;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        BigDecimal cartSum = (BigDecimal) session.getAttribute(CART_SUM);
        MenuItemService service = MenuItemServiceImpl.getInstance();
        BigDecimal pointsForAccount = service.calculateLoyaltyPoints(cartSum, PaymentType.ACCOUNT);
        BigDecimal pointsForCash = service.calculateLoyaltyPoints(cartSum, PaymentType.CASH);
        session.setAttribute(POINTS_FOR_ACCOUNT, pointsForAccount);
        session.setAttribute(POINTS_FOR_CASH, pointsForCash);

        LocalDateTime now = LocalDateTime.now();
        now = now.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime minPickUpTime = now.plusMinutes(ORDER_NOT_EARLIER_TIME_IN_MINUTES);
        LocalDateTime maxPickUpTime = now.plusHours(ORDER_NOT_LATER_TIME_IN_HOURS);
        session.setAttribute(MIN_PICK_UP_TIME, minPickUpTime);
        session.setAttribute(MAX_PICK_UP_TIME, maxPickUpTime);
        session.setAttribute(CURRENT_PAGE, PagePath.PLACE_ORDER);

        return new Router(PagePath.PLACE_ORDER);
    }
}
