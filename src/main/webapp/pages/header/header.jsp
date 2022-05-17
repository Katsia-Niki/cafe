<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 02.05.2022
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="language.en" var="en"/>
<fmt:message key="language.ru" var="ru"/>
<fmt:message key="reference.login" var="login"/>
<fmt:message key="reference.registration" var="registration"/>
<fmt:message key="reference.menu" var="menu"/>
<fmt:message key="reference.contact" var="contact"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap"
          rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font-awesome.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/templatemo-klassy-cafe.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl-carousel.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/lightbox.css">
</head>
<body>
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a class="logo">
                        <img src="${pageContext.request.contextPath}/assets/images/red-logo.png">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_login_page">${login}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_registration_page">${registration}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_contact_page">${contact}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_menu_page">${menu}</a>
                        </li>
                        <li class="submenu">
                            <button class="btn btn-secondary btn-sm" type="button"
                                    aria-expanded="false">
                                ${locale=='ru_RU'?ru:en}
                            </button>
                            <ul>
                                <li><a href="${path}/controller?command=change_language&language=EN">${en}</a></li>
                                <li><a href="${path}/controller?command=change_language&language=RU">${ru}</a></li>
                            </ul>
                        </li>
                    </ul>
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>
</body>
</html>
