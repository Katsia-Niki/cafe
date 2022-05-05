<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 01.05.2022
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/main.css">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md6">
                <h1 class="display-3 text-center">Войти</h1>
                <form action="controller" >
                    <input type="hidden" name="command" value="login"/>
                    <h3>Login:</h3>
                    <input type="text" name="login" value=""placeholder="Введите логин" class="form-control form-control-lg"/>
                    <br/>
                    <h3>Password:</h3>
                    <input type="password" name="pass" value="" placeholder="Введите пароль" class="form-control form-control-lg"/>
                    <br/>
                    <input type="submit" name="sub" value="Push"/>

                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
