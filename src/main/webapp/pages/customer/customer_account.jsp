<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03.05.2022
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="message.user" var="user"/>
<fmt:message key="reference.order_dish" var="order_dish"/>
<fmt:message key="reference.contact" var="contact"/>
<fmt:message key="reference.home" var="home"/>
<fmt:message key="reference.our_dishes" var="our_dishes"/>
<fmt:message key="reference.logout" var="logout"/>
<fmt:message key="title.account" var="account"/>
<fmt:message key="title.change_password" var="change_password"/>
<fmt:message key="title.header" var="title"/>
<fmt:message key="title.order_management" var="order_management"/>
<fmt:message key="title.orders" var="orders"/>
<fmt:message key="title.replenish_balance" var="replenish_balance"/>
<fmt:message key="title.review_management" var="review_management"/>
<fmt:message key="title.menu_item_management" var="menu_item_management"/>
<fmt:message key="title.user_management" var="user_management"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${path}/css/main.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>${title}</title>
</head>
<body>
<div class="row justify-content-between">
    <div class="col-auto">
        <ul class="nav bg-white">
            <a class="nav-link text-secondary" href="${path}/controller?command=go_to_home_page">${home}</a>
            <a class="nav-link text-secondary" href="${path}/controller?command=find_all_visible_rooms">${our_dishes}</a>
            <a class="nav-link text-secondary" href="${path}/controller?command=go_to_book_room_page">${order_dish}</a>
        </ul>
    </div>
    <div class="col-auto">
        <ul class="nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-white" data-bs-toggle="dropdown"
                   href="${path}/controller?command=go_to_account_page" role="button" aria-expanded="false">
                    ${user}: ${ema}
                </a>
                <c:if test="${current_role eq 'CUSTOMER'}">
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_account_page">
                                ${account}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_change_password_page">
                                ${change_password}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_replenish_balance_page">
                                ${replenish_balance}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_client_orders_page">
                                ${orders}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=sing_out">
                                ${sing_out}</a></li>
                    </ul>
                </c:if>
                <c:if test="${current_role eq 'ADMIN'}">
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_change_password_page">
                                ${change_password}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_user_management_page">
                                ${user_management}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_order_management_page">
                                ${order_management}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_room_management_page">
                                ${menu_item_management}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_review_management_page">
                                ${review_management}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=sing_out">
                                ${logout}</a></li>
                    </ul>
                </c:if>
            </li>
        </ul>
    </div>
</div>
</body>
</html>