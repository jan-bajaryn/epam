<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <link rel="stylesheet" href="<c:url value='static/css/order/main.css' />">
    <link rel="stylesheet" href="<c:url value='static/css/order/modals.css' />">
</head>
<body>


<main>
    <div class="main-container">
        <header class="mt-4">
            <h1>
                Корзина
            </h1>
        </header>
        <div class="promo">
            <form action="?">
                <div class="form-group promo-group">
                    <label for="promo"></label>
                    <input class="form-control" placeholder="Promo" type="text" id="promo">
                    <button class="btn white__bg__orange">Применить</button>
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
                        <a href='<c:url value="/minus?product_id=${product.key.id}"/>'>
                            <button class="btn mx-3 white__bg__black minus"> -</button>
                        </a>
                        <span>${product.value}</span>
                        <a href='<c:url value="/plus?product_id=${product.key.id}"/>'>
                            <button class="btn mx-3 white__bg__black plus"> +</button>
                        </a>
                        <div class="prise mr-3">
                                ${String.format("%.2f",(product.key.price*product.value)/100)} руб.
                        </div>
                        <form action="${pageContext.request.contextPath}/deleteAll" method="get">
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
            <div class="sum-text">Сумма заказа:</div>
            <div class="sum-price"> ${String.format("%.2f",sum/100)} руб.</div>
        </div>

        <div class="decision mb-5 mt-2">
            <a href="<c:url value="/"/>">
                <button class="btn orange__hover">Вернуться в меню</button>
            </a>

            <button class="btn orange__bg myBtn">Заказать</button>
            <div class="my__modal">
                <form action="${pageContext.request.contextPath}/make_order" method="post">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <div class="modal__main__content">
                            <h3>Куда доставить?</h3>
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
                                    <input type="text" id="name" name="name" placeholder="Ваше имя"
                                           class="form-control">
                                </div>
                                <div class="time__row">
                                    <input type="time" id="time" name="time"
                                           placeholder="Желаемое время доставки"
                                           class="form-control">
                                </div>
                                <div class="first__row">
                                    <input type="text" class="form-control" placeholder="Улица" id="street"
                                           name="street">
                                    <input type="text" class="form-control" placeholder="Дом" id="house" name="house">
                                </div>
                                <div class="sec__row">
                                    <input type="text" class="form-control" placeholder="Квартира" id="room"
                                           name="room">
                                    <input type="text" class="form-control" placeholder="Подъезд" id="porch"
                                           name="porch">
                                    <input type="text" class="form-control" placeholder="Этаж" id="floor" name="floor">
                                </div>
                                <div class="phone__row">
                                    <input type="tel" class="form-control" placeholder="Телефон" id="tel" name="tel">
                                </div>
                                <div class="email__row">
                                    <input type="email" class="form-control" placeholder="Email" id="email"
                                           name="email">
                                </div>
                                <div class="comments__row">
                                    <textarea class="form-control" id="comments" name="comments"
                                              placeholder="Комментарий к заказу"></textarea>
                                </div>
                            </div>
                            <button class="btn orange__bg accept__btn" type="submit">Подтвердить адрес</button>
                        </div>
                    </div>
                </form>
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

<script src="static/js/order/modals.js"></script>
<%--<script src="static/js/order/plus_minus.js"></script>--%>


</body>
</html>