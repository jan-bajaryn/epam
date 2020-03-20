<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit single order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/edit-order/main.css' />">


</head>
<body>

<c:import url="fragments/navPanel.jsp"/>

<header class="container">
    <h1>Редактировать заказ</h1>
</header>

<main class="container">
    <c:if test="${order!=null}">
        <form action="<c:url value="/edit-order/confirm"/>" method="post">
            <div class="modal__main__content">
                <div class="body__form">
                    <div class="id__row">
                        <label for="id">Number of order</label>
                        <input type="number" value="${order.id}" class="form-control" id="id" name="id" readonly>
                    </div>

                    <div class="status__row">
                        <label for="status">Status</label>
                        <select class="form-control" id="status" name="status">
                            <option>${order.status}</option>
                            <c:forEach var="stat" items="${statuses}">
                                <option>${stat}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="payment_type__row">
                        <label for="payment_type">Payment type</label>
                        <select class="form-control" id="payment_type" name="payment_type">
                            <option>${order.paymentType}</option>
                            <c:forEach var="type" items="${types}">
                                <option>${type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="price__row">
                        <label for="price">Price</label>
                        <input type="number" value="${order.price}" class="form-control" id="price" name="price">
                    </div>
                    <div class="name__row">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" placeholder="Имя"
                               class="form-control" value="${order.deliveryInf.clientName}">
                    </div>
                    <div class="time__row">
                        <label for="time">Time:</label>
                        <input type="time" id="time" name="time"
                               placeholder="Желаемое время доставки"
                               class="form-control" value="${order.deliveryInf.deliveryTime}">
                    </div>
                    <div class="first__row">
                        <label for="street">Street: </label>
                        <input type="text" class="form-control" placeholder="Улица" id="street"
                               name="street" value="${order.deliveryInf.street}">
                        <label for="house">House</label>
                        <input type="text" class="form-control" placeholder="Дом" id="house" name="house"
                               value="${order.deliveryInf.house}">
                    </div>
                    <div class="sec__row">
                        <label for="room">Room</label>
                        <input type="text" class="form-control" placeholder="Квартира" id="room"
                               name="room" value="${order.deliveryInf.room}">
                        <label for="porch">Porch:</label>
                        <input type="text" class="form-control" placeholder="Подъезд" id="porch"
                               name="porch" value="${order.deliveryInf.porch}">
                        <label for="floor">Floor</label>
                        <input type="text" class="form-control" placeholder="Этаж" id="floor" name="floor"
                               value="${order.deliveryInf.floor}">
                    </div>
                    <div class="phone__row">
                        <label for="tel">Phone number:</label>
                        <input type="tel" class="form-control" placeholder="Телефон" id="tel" name="tel"
                               value="${order.deliveryInf.phone}">
                    </div>
                    <div class="email__row">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" placeholder="Email" id="email"
                               name="email" value="${order.deliveryInf.email}">
                    </div>
                    <div class="comments__row">
                        <label for="comments">Comments:</label>
                        <textarea class="form-control" id="comments" name="comments"
                                  placeholder="Комментарий к заказу">${order.deliveryInf.comments}</textarea>
                    </div>
                </div>
                <button class="btn orange__bg accept__btn" type="submit">Изменить</button>
            </div>
        </form>
    </c:if>
    <c:if test="${order==null}">
        <div>
            There no order with so number
        </div>
    </c:if>
</main>

<c:import url="fragments/footer.jsp"/>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>

</body>
</html>

