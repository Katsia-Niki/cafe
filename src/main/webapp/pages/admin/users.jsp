<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 30.05.2022
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="message.admin" var="admin"/>
<fmt:message key="message.customer" var="customer"/>
<fmt:message key="title.header" var="title"/>
<fmt:message key="title.user_list" var="user_list"/>
<fmt:message key="reference.ban" var="ban"/>
<fmt:message key="reference.unban" var="unban"/>
<fmt:message key="field.action" var="action"/>
<fmt:message key="field.email" var="email"/>
<fmt:message key="field.login" var="login"/>
<fmt:message key="field.first_name" var="first_name"/>
<fmt:message key="field.last_name" var="last_name"/>
<fmt:message key="field.role" var="role"/>
<fmt:message key="field.user_id" var="user_id"/>
<fmt:message key="field.user_status" var="user_status"/>
<fmt:message key="field.user_active" var="user_active"/>
<fmt:message key="field.user_blocked" var="user_blocked"/>
<fmt:message key="message.complete_changing" var="complete_changing"/>
<fmt:message key="message.failed" var="failed"/>
<html>
<head>
    <script>
        function preventBack() {
            window.history.forward();
        }

        setTimeout("preventBack()", 0);
        window.onunload = function () {
            null
        };
    </script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${path}/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <title>${title}</title>
</head>
<body>
<header>
    <jsp:include page="../header/header.jsp"/>
    <div class="row">
        <div class="container">
            <br><br><br><br><br>
            <h3 class="text-center">${user_list} </h3>
            <hr>
            <br>
            <c:choose>
                <c:when test="${not empty update_user_status_result}">
                    ${update_user_status_result eq true ? complete_changing: failed}
                </c:when>
                <c:otherwise>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>${user_id} </th>
                            <th>${login} </th>
                            <th>${last_name} </th>
                            <th>${first_name} </th>
                            <th>${email}</th>
                            <th>${role} </th>
                            <th>${user_status} </th>
                            <th>${action} </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users_list}">
                            <tr>
                                <td><c:out value="${user.userId}"/></td>
                                <td><c:out value="${user.login}"/></td>
                                <td><c:out value="${user.lastName}"/></td>
                                <td><c:out value="${user.firstName}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.role eq 'ADMIN' ? admin : customer}"/></td>
                                <td><c:out value="${user.active eq true ? user_active : user_blocked}"/></td>
                                <td>
                                    <a href="${path}/controller?command=update_user_status&user_id=${user.userId}&user_is_active=${user.active}">
                                            ${user.active eq true ? ban : unban}
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <footer>
        <jsp:include page="../footer/footer.jsp"/>
    </footer>
</body>
</html>
