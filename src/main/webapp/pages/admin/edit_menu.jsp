<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 08.06.2022
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="title.header" var="title"/>
<fmt:message key="title.edit_menu" var="edit_menu"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="button.upload" var="upload"/>
<fmt:message key="field.menu_item_type" var="menu_item_type"/>
<fmt:message key="field.menu_item_name" var="menu_item_name"/>
<fmt:message key="field.menu_item_description" var="menu_item_description"/>
<fmt:message key="field.menu_item_price" var="menu_item_price"/>
<fmt:message key="field.menu_item_available" var="available"/>
<fmt:message key="message.complete_changing" var="complete_changing"/>
<fmt:message key="message.failed" var="failed"/>
<fmt:message key="message.not_found" var="not_found"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${path}/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap"
          rel="stylesheet">

    <title>${title}</title>
    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font-awesome.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/templatemo-klassy-cafe.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl-carousel.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/lightbox.css">

    <script>
        function preventBack() {
            window.history.forward();
        }

        setTimeout("preventBack()", 0);
        window.onunload = function () {
            null
        };
    </script>
</head>
<body>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<br><br><br><br><br>
<div class="container">
    <div class="row">
        <div class="col-lg-6 align-self-center">
            <div class="left-text-content">
                <div class="section-heading">
                    <h6>${edit_menu}</h6>
                    <br>
                </div>
            </div>
        </div>
    </div>

<c:choose>
    <c:when test="${not empty not_fount_ses}">
        ${not_found}
    </c:when>
    <c:when test="${not empty edit_menu_result}">
        ${edit_menu_result eq true? complete_changing: failed}
    </c:when>
    <c:otherwise>

        <div class="row justify-content-md-center text-left">
            <div class="col-6">
                <form method="post" action="${path}/controller">
                    <input type="hidden" name="command" value="edit_menu_item">
                    <div class="form-group row">
                        <label for="id" class="col col-form-label">Id</label>
                        <div class="col">
                            <input type="text" class="form-control" id="id"
                                   placeholder="${menu_item_data_ses['menu_item_id_ses']}" disabled>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="type" class="col col-form-label">${menu_item_type}</label>
                        <div class="col">
                            <input type="text" class="form-control" id="type"
                                   placeholder="${menu_item_data_ses['menu_item_type_ses']}" disabled>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="name" class="col col-form-label">${menu_item_name}</label>
                        <div class="col">
                            <input type="text" class="form-control" id="name" name="menu_item_name"
                                   value="${menu_item_data_ses['menu_item_name_ses']}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="description" class="col col-form-label">${menu_item_description}</label>
                        <div class="col">
                        <textarea id="description" class="form-control" id="description" rows="4" cols="50"
                                  name="menu_item_description">${menu_item_data_ses['menu_item_description_ses']}</textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="price" class="col col-form-label">${menu_item_price}</label>
                        <div class="col">
                            <input required type="number" min="0.50" max="100" step="0.1" name="menu_item_price"
                                   class="form-control"
                                   id="price" value="${menu_item_data_ses['menu_item_price_ses']}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="available" class="col col-form-label">${available}</label>
                        <div class="col">
                            <select id="available" class="form-control" required name="menu_item_is_available">
                                <option selected>${menu_item_data_ses['menu_item_available_ses']}</option>
                                <c:if test="${menu_item_data_ses['menu_item_available_ses']}">
                                    <option value="false">false</option>
                                </c:if>
                                <c:if test="${!menu_item_data_ses['menu_item_available_ses']}">
                                    <option value="true">true</option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row text-center">
                        <div class="col">
                        </div>
                        <div class="col">
                            <button class="btn btn-sm btn-outline-danger" type="submit">${update}</button>
                        </div>
                    </div>
                </form>
                <form method="post" action="${path}/controller" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="upload_image"/>
                    <input type="hidden" name="menu_item_id" value="${menu_item_data_ses['menu_item_id_ses']}"/>
                    <div class="input-group mb-3">
                        <input type="file" name="image" class="form-control">
                        <button type="submit" class="btn btn-outline-secondary">${upload}</button>
                    </div>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</div>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</body>
</html>
