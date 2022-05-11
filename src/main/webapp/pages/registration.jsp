<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 26.04.2022
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="button.confirm" var="confirm"/>
<fmt:message key="field.email" var="email"/>
<fmt:message key="field.login" var="login"/>
<fmt:message key="field.first_name" var="first_name"/>
<fmt:message key="field.last_name" var="last_name"/>
<fmt:message key="field.password" var="password"/>
<fmt:message key="field.repeat_password" var="repeat_password"/>
<fmt:message key="message.email_rules" var="email_rules"/>
<fmt:message key="message.empty_field" var="empty_field"/>
<fmt:message key="message.incorrect_data" var="incorrect_data"/>
<fmt:message key="message.incorrect_email_exist" var="incorrect_email_exist"/>
<fmt:message key="message.incorrect_login_exist" var="incorrect_login_exist"/>
<fmt:message key="message.mismatch" var="mismatch"/>
<fmt:message key="message.name_rules" var="name_rules"/>
<fmt:message key="message.password_rules" var="password_rules"/>
<fmt:message key="message.complete" var="complete"/>
<fmt:message key="message.failed" var="failed"/>
<fmt:message key="reference.back_to_main" var="back_to_main"/>
<fmt:message key="title.create_new_account" var="title"/>


<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        ${title}
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>

<div class="container text-secondary text-center">
    <div class="mb-3 fw-bold">
        ${title}
    </div>
    <c:choose>
        <c:when test="${not empty registration_result}">
            ${registration_result eq true? complete: failed}
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col">
                </div>
                <div class="col">
                    <form method="post" action="${path}/controller" novalidate>
                        <input type="hidden" name="command" value="registration"/>
                        <div class="mb-3">
                            <label class="form-label">
                                    ${email}
                            </label>
                            <input type="text" maxlength="40"  name="email"
                                   value="${user_data_ses['email_ses'] eq 'wrong_data' ? empty_field : user_data_ses['email_ses']}"
                                   pattern="[\da-z]([\da-z_\-\.]*)[\da-z_\-]@[\da-z_\-]{2,}\.[a-z]{2,6}"
                                   required oninvalid="this.setCustomValidity('${email_rules}')"
                                   class="form-control">
                            <label class="form-label text-danger">
                                <c:if test="${user_data_ses['email_ses'] eq 'wrong_data'}">
                                    ${incorrect_data}
                                </c:if>
                                <c:if test="${not empty user_data_ses['wrong_email_exist_ses']}">
                                    ${incorrect_email_exist}
                                </c:if>
                            </label>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                                    ${login}
                            </label>
                            <input type="text" maxlength="45"  name="login"
                                   value="${user_data_ses['login_ses'] eq 'wrong_data' ? empty_field : user_data_ses['login_ses']}"
                                   class="form-control">
                            <label class="form-label text-danger">
                                <c:if test="${user_data_ses['login_ses'] eq 'wrong_data'}">
                                    ${incorrect_data}
                                </c:if>
                                <c:if test="${not empty user_data_ses['wrong_login_exist_ses']}">
                                    ${incorrect_login_exist}
                                </c:if>
                            </label>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                                    ${first_name}
                            </label>
                            <input type="text" name="first_name"
                                   value="${user_data_ses['first_name_ses'] eq 'wrong_data' ? empty_field : user_data_ses['first_name_ses']}"
                                   class="form-control">
                            <label class="form-label text-danger">
                                <c:if test="${user_data_ses['first_name_ses'] eq 'wrong_data'}">
                                    ${incorrect_data}
                                </c:if>
                            </label>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                                    ${last_name}
                            </label>
                            <input type="text" name="last_name"
                                   value="${user_data_ses['last_name_ses'] eq 'wrong_data' ? empty_field : user_data_ses['last_name_ses']}"
                                   class="form-control">
                            <label class="form-label text-danger">
                                <c:if test="${user_data_ses['last_name_ses'] eq 'wrong_data'}">
                                    ${incorrect_data}
                                </c:if>
                            </label>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                                    ${password}
                            </label>
                            <input type="password" minlength="4" maxlength="12" name="pass" id="pass"
                                   value="${user_data_ses['password_ses'] eq 'wrong_data' ? empty_field : user_data_ses['password_ses']}"
                                   pattern="[\da-zA-Z]+"
                                   required oninvalid="this.setCustomValidity('${password_rules} \'')"
                                   class="form-control">
                            <label class="form-label text-danger">
                                <c:if test="${user_data_ses['password_ses'] eq 'wrong_data'}">
                                    ${incorrect_data}</br>
                                </c:if>
                            </label>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                                    ${repeat_password}
                            </label>
                            <input type="password" minlength="4" maxlength="12" name="repeat_password"
                                   value="${user_data_ses['repeat_password_ses']}"
                                   pattern="[\da-zA-Z]+"
                                   required oninvalid="this.setCustomValidity('${password_rules} \'')"
                                   class="form-control">
                            <label class="form-label text-danger">
                                <c:if test="${not empty user_data_ses['wrong_mismatch_ses']}">
                                    ${mismatch}
                                </c:if>
                            </label>
                        </div>
                        <button type="submit" class="btn btn-secondary">
                                ${confirm}
                        </button>
                    </form>
                </div>
                <div class="col">
                </div>
            </div>
            <div class="mb-3">
                <a class="link-secondary text-decoration-none" href="${path}/controller?command=go_to_main_page">
                        ${back_to_main}</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
