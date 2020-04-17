<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="<c:url value='/static/css/order/main.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/order/modals.css' />">
</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>

<main>
    <div class="main-container">
        <header class="mt-4">
            <h1>
                <%--                Корзина--%>
                <fmt:message key="web.links.basket" bundle="${ rb }"/>
            </h1>
        </header>
        <div class="promo">
            <form action="?">
                <div class="form-group promo-group">
                    <label for="promo"></label>
                    <input class="form-control" placeholder="Promo" type="text" id="promo">
                    <button class="btn white__bg__orange">
                        <%--                        Применить--%>
                        <fmt:message key="web.btn.execute" bundle="${ rb }"/>
                    </button>
                </div>
            </form>
        </div>

        <div class="product-list">

            <c:forEach var="product" items="${productMap}">
                <div class="product-item">
                    <div class="grid-part">
                        <div class="image-part">
                            <img src="../static/img/${product.key.productGroup.photoName}" alt="">
                        </div>
                        <div class="product-name">
                                ${product.key.productGroup.name}
                        </div>
                        <div class="product-type text-muted">
                                ${product.key.weight} гр.
                        </div>
                    </div>
                    <div class="flex-part">
                        <a href='<c:url value="page/minus?product_id=${product.key.id}"/>'>
                            <button class="btn mx-3 white__bg__black minus"> -</button>
                        </a>
                        <span>${product.value}</span>
                        <a href='<c:url value="page/plus?product_id=${product.key.id}"/>'>
                            <button class="btn mx-3 white__bg__black plus"> +</button>
                        </a>
                        <div class="prise mr-3">
                                ${String.format("%.2f",(product.key.price*product.value)/100)}
                            <fmt:message key="web.text.rub" bundle="${ rb }"/>
                                <%--                                    руб--%>
                            .
                        </div>
                        <form action="<c:url value="page/deleteAll"/>" method="get">
                            <button class="abc" type="submit">
                                <i class="fa fa-trash mr-3" aria-hidden="true"></i>
                            </button>
                            <label>
                                <input name="id" type="number" value="${product.key.id}"
                                       style="display: none;">
                            </label>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="sum">
            <div class="sum-text">
                <%--                Сумма заказа:--%>
                <fmt:message key="web.text.sum-order" bundle="${ rb }"/>
            </div>
            <div class="sum-price"> ${String.format("%.2f",sum/100)}
                <fmt:message key="web.text.rub" bundle="${ rb }"/>
            </div>
        </div>

        <div class="decision mb-5 mt-2">
            <a href="<c:url value="/"/>">
                <button class="btn orange__hover">
                    <fmt:message key="web.btn.return-menu" bundle="${ rb }"/>
                </button>
            </a>

            <button class="btn orange__bg myBtn">
                <fmt:message key="web.btn.make-order" bundle="${ rb }"/>
            </button>
            <div class="my__modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div class="modal__main__content">
                        <h3>
                            <%--                                Куда доставить?--%>
                            <fmt:message key="web.text.where-delivery" bundle="${ rb }"/>
                        </h3>
                        <div class="body__form">
                            <label for="street"></label>
                            <label for="comments"></label>
                            <label for="floor"></label>
                            <label for="porch"></label>
                            <label for="room"></label>
                            <label for="house"></label>
                            <label for="name"></label>
                            <label for="tel"></label>
                            <label for="email"></label>
                            <label for="time"></label>


                            <div class="name__row">
                                <input type="text" id="name" name="name"
                                       placeholder="<fmt:message key="web.inputs.your-name" bundle="${ rb }"/>"
                                       class="form-control">
                            </div>
                            <div class="time__row">
                                <input type="time" id="time" name="time"
                                       placeholder="<fmt:message key="web.inputs.date-deliver" bundle="${ rb }"/>"
                                       class="form-control">
                            </div>
                            <div class="first__row">
                                <input type="text" class="form-control"
                                       placeholder="<fmt:message key="web.inputs.street" bundle="${ rb }"/>"
                                       id="street"
                                       name="street">
                                <input type="text" class="form-control"
                                       placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                                       id="house" name="house">
                            </div>
                            <div class="sec__row">
                                <input type="text" class="form-control"
                                       placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"
                                       id="room"
                                       name="room">
                                <input type="text" class="form-control"
                                       placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"
                                       id="porch"
                                       name="porch">
                                <input type="text" class="form-control"
                                       placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                                       id="floor" name="floor">
                            </div>
                            <div class="phone__row">
                                <input type="tel" class="form-control"
                                       placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                                       id="tel" name="tel">
                            </div>
                            <div class="email__row">
                                <input type="email" class="form-control"
                                       placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                                       id="email"
                                       name="email">
                            </div>
                            <div class="comments__row">
                                    <textarea class="form-control" id="comments" name="comments"
                                              placeholder="<fmt:message key="web.inputs.comments" bundle="${ rb }"/>"></textarea>
                            </div>
                        </div>
                        <button class="btn orange__bg accept__btn" type="submit">
                            <fmt:message key="web.inputs.accept-order" bundle="${ rb }"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</main>
<%--<footer class="bg-dark">--%>
<%--    <div class="container">--%>
<%--        <a href="?">О нас</a>--%>
<%--        <a href="?">Почему нашу пиццу все любят</a>--%>
<%--        <a href="?">Наш блог</a>--%>
<%--        <a href="?">Наши спонсоры</a>--%>
<%--    </div>--%>
<%--</footer>--%>

<c:import url="fragments/footer.jsp"/>


<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>--%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>

<script src="/static/js/order/modals.js"></script>
<%--<script src="/static/js/order/plus_minus.js"></script>--%>


</body>
</html>