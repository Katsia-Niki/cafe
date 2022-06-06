<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.03.2022
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="title.header" var="title"/>
<fmt:message key="message.welcome" var="welcome"/>
<fmt:message key="message.cafe_info" var="cafe_info"/>
<fmt:message key="reference.menu" var="menu"/>
<html>
<head>
    <script>
        function preventBack() {
            window.history.forward();
        }
        setTimeout("preventBack()", 0);
        window.onunload = function() {
            null
        };
    </script>
    <meta charset="utf-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="${path}/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="header/header.jsp"/>
</header>
<br><br><br><br><br>
<div class="container text-secondary">
    <div class ="row">
        <div class="col mb-3 fw-bold">
            ${welcome}
        </div>
    </div>
    <div class ="row">
        <div class="col mb-3">
            ${cafe_info}
                <a class="link" href="${path}/controller?command=find_all_available_menu">
            ${menu.toLowerCase()}.
        </a>
        </div>
    </div>
</div>
<br><br><br><br><br><br>
<footer>
    <jsp:include page="footer/footer.jsp"/>
</footer>
</body>
</html>

