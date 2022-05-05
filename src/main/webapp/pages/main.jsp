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


<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="reference.registration" var="registration"/>
<fmt:message key="reference.login" var="login"/>
<fmt:message key="reference.contact" var="contact"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${path}/css/main.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Main</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">YummyCafe</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${path}/controller?command=go_to_registration_page">${registration}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${path}/controller?command=go_to_login_page">${login}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${path}/controller?command=go_to_contact_page">${contact}</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<br/>
<div class="row row-cols-1 row-cols-md-2 g-4">
    <div class="col">
        <div class="card h-100">
            <img class="w-100" src="https://img.delo-vcusa.ru/2017/05/Blyuda-iz-molodoy-kartoshki-TOP-6-retseptov.jpg" class="card-img-top" alt="Potato">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <img class="w-100" src="https://km-doma.ru/assets/gallery_thumbnails/31/319484a4bb725e4eacab62c7f0c7f1ed.jpg" class="card-img-top" alt="Pizza">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <img class="w-100" src="https://e2.edimdoma.ru/data/posts/0002/3469/23469-ed4_wide.jpg?1631185572" class="card-img-top" alt="Soup">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <img class="w-100" src="https://img.povar.ru/main/fc/42/3e/01/shima_bliudo-446138.JPG" class="card-img-top" alt="smth">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
