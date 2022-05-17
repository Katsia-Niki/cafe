<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 01.05.2022
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="title.login_form" var="login_form"/>
<fmt:message key="button.sign_in" var="sign_in"/>
<fmt:message key="placeholder.login" var="login"/>
<fmt:message key="placeholder.password" var="password"/>
<fmt:message key="reference.registration" var="registration"/>
<fmt:message key="reference.back_to_main" var="back_to_main"/>

<!DOCTYPE html>
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

    <meta charset="UTF-8">
    <title>${login_form}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"/>

</head>

<body>

<!DOCTYPE html>
<!--[if lt IE 7 ]>
<html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>
<html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>
<html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css"/>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="controller">
            <input type="hidden" name="command" value="login"/>
            <h1>${login_form}</h1>
            <div>
                <input type="text" name="login" value="" placeholder="${login}"/>
            </div>
            <div>
                <input type="password" name="pass" value="" placeholder="${password}"/>
            </div>
            <div>
                <input type="submit" value="${sign_in}">
                <a href="${path}/controller?command=go_to_registration_page">${registration}</a>
            </div>
        </form><!-- form -->
        <br>
        <div class="mb-3">
            <a class="link-secondary text-decoration-none" href="${path}/controller?command=go_to_main_page">
                ${back_to_main}</a>
        </div>
        <label class="form-label text-danger">
            ${login_msg}
        </label>
    </section><!-- content -->
</div><!-- container -->
</body>
</html>


</body>
</html>