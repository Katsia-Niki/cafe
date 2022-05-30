<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.03.2022
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="reference.login" var="login"/>
<fmt:message key="reference.menu" var="menu"/>
<fmt:message key="reference.order_dish" var="order_dish"/>
<fmt:message key="reference.contact" var="contact"/>
<fmt:message key="reference.home" var="home"/>
<fmt:message key="reference.our_dishes" var="our_dishes"/>
<fmt:message key="reference.logout" var="logout"/>
<fmt:message key="title.account" var="account"/>
<fmt:message key="title.change_password" var="change_password"/>
<fmt:message key="title.header" var="title"/>
<fmt:message key="title.order_management" var="order_management"/>
<fmt:message key="title.orders" var="orders"/>
<fmt:message key="title.refill_balance" var="refill_balance"/>
<fmt:message key="title.review_management" var="review_management"/>
<fmt:message key="title.menu_item_management" var="menu_item_management"/>
<fmt:message key="title.user_management" var="user_management"/>

<!DOCTYPE html>
<html lang="en">

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

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap" rel="stylesheet">

    <title>Yummy Cafe</title>
    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font-awesome.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/templatemo-klassy-cafe.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl-carousel.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/lightbox.css">

</head>

<body>



<!-- ***** Header Area Start ***** -->
<header>
    <jsp:include page="header/header.jsp"/>
</header>
<!-- ***** Header Area End ***** -->


<!-- ***** About Area Starts ***** -->
<%--<section class="section" id="about">--%>
<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--            <div class="col-lg-6 col-md-6 col-xs-12">--%>
<%--                <div class="left-text-content">--%>
<%--                    <div class="section-heading">--%>
<%--                        <h6>About Us</h6>--%>
<%--                        <h2>We Leave A Delicious Memory For You</h2>--%>
<%--                    </div>--%>
<%--                    <p>Yummy Cafe is one of the best.</p>--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-4">--%>
<%--                            <img src="${pageContext.request.contextPath}/assets/images/about-thumb-01.jpg" alt="">--%>
<%--                        </div>--%>
<%--                        <div class="col-4">--%>
<%--                            <img src="${pageContext.request.contextPath}/assets/images/about-thumb-02.jpg" alt="">--%>
<%--                        </div>--%>
<%--                        <div class="col-4">--%>
<%--                            <img src="${pageContext.request.contextPath}/assets/images/about-thumb-03.jpg" alt="">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-6 col-md-6 col-xs-12">--%>
<%--                <div class="right-content">--%>
<%--                    <div class="thumb">--%>
<%--                        <a rel="nofollow" href="http://youtube.com"><i class="fa fa-play"></i></a>--%>
<%--                        <img src="${pageContext.request.contextPath}/assets/images/about-video-bg.jpg" alt="">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>
<!-- ***** About Area Ends ***** -->

<!-- ***** Menu Area Starts ***** -->
<!-- ***** Menu Area Ends ***** -->



<!-- ***** Menu Area Starts ***** -->
<section class="section" id="offers">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 offset-lg-4 text-center">
                <div class="section-heading">
                    <h2>Our menu</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                    <div class="col-lg-12">
                        <section class='tabs-content'>
                            <article id='tabs-1'>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="row">
                                            <div class="left-list">
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-01.png" alt="">
                                                        <h4>Fresh Chicken Salad</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$10.50</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-02.png" alt="">
                                                        <h4>Orange Juice</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$8.50</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-03.png" alt="">
                                                        <h4>Fruit Salad</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$9.90</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="row">
                                            <div class="right-list">
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-04.png" alt="">
                                                        <h4>Eggs Omelette</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$6.50</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-05.png" alt="">
                                                        <h4>Dollma Pire</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$5.00</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-06.png" alt="">
                                                        <h4>Omelette & Cheese</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$4.10</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article id='tabs-2'>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="row">
                                            <div class="left-list">
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-04.png" alt="">
                                                        <h4>Eggs Omelette</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$14</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-05.png" alt="">
                                                        <h4>Dollma Pire</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$18</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-06.png" alt="">
                                                        <h4>Omelette & Cheese</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$22</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="row">
                                            <div class="right-list">
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-01.png" alt="">
                                                        <h4>Fresh Chicken Salad</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$10</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-02.png" alt="">
                                                        <h4>Orange Juice</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$20</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-03.png" alt="">
                                                        <h4>Fruit Salad</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$30</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article id='tabs-3'>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="row">
                                            <div class="left-list">
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-05.png" alt="">
                                                        <h4>Eggs Omelette</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$14</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-03.png" alt="">
                                                        <h4>Orange Juice</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$18</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-02.png" alt="">
                                                        <h4>Fruit Salad</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$10</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="row">
                                            <div class="right-list">
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-06.png" alt="">
                                                        <h4>Fresh Chicken Salad</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$8.50</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-01.png" alt="">
                                                        <h4>Dollma Pire</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$9</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="tab-item">
                                                        <img src="${pageContext.request.contextPath}/assets/images/tab-item-04.png" alt="">
                                                        <h4>Omelette & Cheese</h4>
                                                        <p>Lorem ipsum dolor sit amet, consectetur koit adipiscing elit, sed do.</p>
                                                        <div class="price">
                                                            <h6>$11</h6>
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
        </div>
    </div>
</section>
<!-- ***** Chefs Area Ends ***** -->

<!-- ***** Footer Start ***** -->
<footer>
    <jsp:include page="footer/footer.jsp"/>
</footer>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.min.js"></script>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/assets/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

<!-- Plugins -->
<script src="${pageContext.request.contextPath}/assets/js/owl-carousel.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/accordions.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/datepicker.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/scrollreveal.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.counterup.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/imgfix.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/slick.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/lightbox.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/isotope.js"></script>

<!-- Global Init -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<%--<script>--%>

<%--    $(function() {--%>
<%--        var selectedClass = "";--%>
<%--        $("p").click(function(){--%>
<%--            selectedClass = $(this).attr("data-rel");--%>
<%--            $("#portfolio").fadeTo(50, 0.1);--%>
<%--            $("#portfolio div").not("."+selectedClass).fadeOut();--%>
<%--            setTimeout(function() {--%>
<%--                $("."+selectedClass).fadeIn();--%>
<%--                $("#portfolio").fadeTo(50, 1);--%>
<%--            }, 500);--%>

<%--        });--%>
<%--    });--%>

<%--</script>--%>
</body>
</html>