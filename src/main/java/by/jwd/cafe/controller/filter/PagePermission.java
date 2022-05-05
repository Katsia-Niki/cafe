package by.jwd.cafe.controller.filter;

import java.util.Set;
import static by.jwd.cafe.command.PagePath.*;

public enum PagePermission {
    ADMIN(Set.of(MAIN, REGISTRATION, LOGIN)),
    CUSTOMER(Set.of(MAIN, REGISTRATION, LOGIN)),
    GUEST(Set.of(MAIN, REGISTRATION, LOGIN));
    private Set<String> pages;

    PagePermission(Set<String> pages) {
        this.pages = pages;
    }

    public Set<String> getPages() {
        return pages;
    }
}
