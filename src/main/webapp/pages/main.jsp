<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.03.2022
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hello = ${user}
<hr>
${filter_attribute}
<hr>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logOut"/>
</form>
</body>
</html>
