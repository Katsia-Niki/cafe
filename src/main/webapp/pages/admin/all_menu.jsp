<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.05.2022
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="field.menu_item_price" var="menu_item_price"/>
<fmt:message key="field.menu_item_picture" var="menu_item_picture"/>
<fmt:message key="field.menu_item_description" var="menu_item_description"/>
<fmt:message key="field.add_menu_item" var="add_menu_item"/>
<fmt:message key="message.not_found_menu" var="not_found"/>
<fmt:message key="reference.previous" var="previous"/>
<fmt:message key="reference.next" var="next"/>
<fmt:message key="reference.edit" var="edit"/>
<fmt:message key="title.header" var="title"/>
<fmt:message key="title.menu" var="title_menu"/>

<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap"
        rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap"
        rel="stylesheet">

  <title>${title}</title>
  <!-- Additional CSS Files -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/templatemo-klassy-cafe.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style_counter.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/background.css">

  <script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/jquery.counterup.min.js"></script>
</head>
<body>
<div class="page">
  <header>
    <jsp:include page="../header/header.jsp"/>
  </header>
  <section class="section" id="offers">
    <div class="container text-center">
      <div class="row">
        <div class="col-lg-4 offset-lg-4 text-center">
          <div class="section-heading">
            <h2>${title_menu}</h2>
          </div>
        </div>
      </div>
      <c:choose>
        <c:when test="${empty menu_item_all_ses}">
          ${not_found}
        </c:when>
        <c:otherwise>

          <c:forEach var="menu" items="${menu_item_all_ses}">
            <div class="row">
              <div class="col-lg-12">
                <div class="col-lg-12">
                  <section class='tabs-content' style="width: 500px">
                    <article id='tabs-1'>
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="row">
                            <div class="list-group-item">
                              <div class="col-lg-12">
                                <div class="tab-item">
                                  <img src="${menu.image}" class="img-thumbnail">
                                  <h4>${menu.name}</h4>
                                  <p>${menu.description}</p>
                                  <div class="price">
                                    <h6>${menu.price}</h6>
                                  </div>
                                  <div>
                                    <c:if test="${current_role eq 'ADMIN'}">
                                      <form target="_blank" method="get" action="${path}/controller">
                                        <input type="hidden" name="command" value="go_to_edit_menu_page">
                                        <input type="hidden" name="menu_item_id_to_edit" value="${menu.menuItemId}">
                                        <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">${edit}</button>
                                      </form>
                                    </c:if>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </article>
                  </section>
                </div>
              </div>
            </div>
          </c:forEach>
          <div class="text-center justify-content-center">
            <div class="row">
              <div class="col mb-3">
                <c:if test="${pagination_ses['current_sheet_ses']>1}">
                  <a class="link-secondary"
                     href="${path}/controller?command=find_all_menu&direction=-1">
                    <img src="${pageContext.request.contextPath}/assets/images/prev.png"
                         alt=""/></a>
                </c:if>
              </div>
              <div class="col mb-3">
                <c:if test="${pagination_ses['current_sheet_ses']<pagination_ses['all_sheets_ses']}">
                  <a class="link-secondary text-decoration-none"
                     href="${path}/controller?command=find_all_menu&direction=1">
                    <img src="${pageContext.request.contextPath}/assets/images/next.png"
                         alt=""/></a>
                </c:if>
              </div>
            </div>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </section>
</div>
<footer>
  <jsp:include page="../footer/footer.jsp"/>
</footer>
</body>
</html>
