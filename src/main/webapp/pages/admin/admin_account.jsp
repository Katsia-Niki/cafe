<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03.05.2022
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Title</title>
</head>
<body>
It's admin account jsp.
</body>
</html>
