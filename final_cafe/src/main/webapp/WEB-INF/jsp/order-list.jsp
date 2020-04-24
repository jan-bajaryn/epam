<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<by.epam.cafe.entity.impl.Order>"/>


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

    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/order-list/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="fragments/navPanel.jsp"/>

<header class="container">
    <h1>
        <fmt:message key="web.links.orders" bundle="${ rb }"/>
    </h1>
</header>

<main>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>
                    <fmt:message key="web.tab.order-number" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.order-status" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.address-deliv" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.price-order" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.cancel" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.edit" bundle="${ rb }"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.status}</td>
                    <td>
                        <c:if test="${order.deliveryInf!=null}">
                            <span>${order.deliveryInf.street}</span>
                            &nbsp;
                            <span>${order.deliveryInf.house}</span>
                        </c:if>
                    </td>
                    <td>${order.price}</td>
                    <td>
                        <form action="<c:url value="/page/cancel_order"/>" method="post">
                            <input type="hidden" value="${order.id}" name="id">
                            <button class="btn orange__bg" type="submit">
                                <fmt:message key="web.tab.cancel" bundle="${ rb }"/>
                            </button>
                        </form>
                    </td>
                    <td>
                        <a href="<c:url value="/page/edit-order/${order.id}"/>">
                            <button class="btn orange__bg">
                                <fmt:message key="web.tab.edit" bundle="${ rb }"/>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</main>

<c:import url="fragments/footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
</body>
</html>
