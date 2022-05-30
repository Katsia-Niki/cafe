<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 25.05.2022
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="message.admin" var="admin"/>
<fmt:message key="message.customer" var="customer"/>
<fmt:message key="reference.order_dish" var="order_dish"/>
<fmt:message key="reference.contact" var="contact"/>
<fmt:message key="reference.home" var="home"/>
<fmt:message key="reference.menu" var="menu"/>
<fmt:message key="reference.logout" var="logout"/>
<fmt:message key="title.account" var="account"/>
<fmt:message key="title.change_password" var="change_password"/>
<fmt:message key="title.header" var="title"/>
<fmt:message key="title.order_management" var="order_management"/>
<fmt:message key="title.orders" var="orders"/>
<fmt:message key="title.refill_balance" var="refill_balance"/>
<fmt:message key="title.review_management" var="review_management"/>
<fmt:message key="title.menu_item_management" var="menu_item_management"/>
<fmt:message key="title.user_management" var="user_management"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${path}/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap"
          rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font-awesome.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/templatemo-klassy-cafe.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl-carousel.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/lightbox.css">
    <title>
        ${title}
    </title>
</head>
<body>
<div class="row justify-content-between">
    <div class="col-auto">
        <ul class="nav" style="vertical-align: center">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle"  data-bs-toggle="dropdown"
                   href="${path}/controller?command=go_to_account_page" role="button" aria-expanded="false">
                    ${current_role eq 'ADMIN' ? admin : customer}: ${current_login_ses}
                </a>
                <c:if test="${current_role eq 'CUSTOMER'}">
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_account_page">
                                ${account}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_change_password_page">
                                ${change_password}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_refill_balance_page">
                                ${refill_balance}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_customer_orders_page">
                                ${orders}</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=logout">
                                ${logout}</a></li>
                    </ul>
                </c:if>
                <c:if test="${current_role eq 'ADMIN'}">
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_change_password_page">
                                ${change_password}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=find_all_users">
                                ${user_management}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_order_management_page">
                                ${order_management}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_menu_item_management_page">
                                ${menu_item_management}</a></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=go_to_review_management_page">
                                ${review_management}</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item text-secondary"
                               href="${path}/controller?command=logout">
                                ${logout}</a></li>
                    </ul>
                </c:if>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
