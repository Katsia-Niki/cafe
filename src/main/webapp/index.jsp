<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.03.2022
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<br/>
<form action="controller" >
    <input type="hidden" name="command" value="login"/>
  Login:  <input type="text" name="login" value=""/>
    <br/>
  Password: <input type="password" name="pass" value=""/>
    <br/>
    <input type="submit" name="sub" value="Push"/>
    <br/>
    ${login_msg.toUpperCase()}
    <br/>
    ${filter_attribute}
    <br/>

</form>

</body>
</html>
