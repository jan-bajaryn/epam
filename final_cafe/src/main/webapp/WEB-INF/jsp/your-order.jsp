<jsp:useBean id="order" scope="request" type="by.epam.cafe.entity.impl.Order"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="productMap" scope="request" type="java.util.Map<by.epam.cafe.entity.impl.Product,java.lang.Integer>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>


<!doctype html>
<html lang="en">
<head>
    <title>Your order</title>

    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/order-list/main.css' />">
</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>

<c:import url="fragments/navPanel.jsp"/>

<main class="container">
    <table class="table">
        <tr>
            <td>
                <fmt:message key="web.inputs.name" bundle="${ rb }"/>
            </td>
            <td>${order.clientName}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.tab.order-number" bundle="${ rb }"/>
            </td>
            <td>${order.id}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.inputs.street" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.street}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.inputs.house" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.house}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.inputs.room" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.room}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.inputs.porch" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.porch}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.inputs.floor" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.floor}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.inputs.email" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.email}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.inputs.phone" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.phone}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.tab.delivery-time" bundle="${ rb }"/>
            </td>
            <td>${order.deliveryInf.deliveryTime}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="web.tab.sum-order" bundle="${ rb }"/>
            </td>
            <td>${order.price}</td>
        </tr>
    </table>
    <button type="button" class="btn orange__bg" data-toggle="collapse" data-target="#demo">
        <fmt:message key="web.btn.products" bundle="${ rb }"/>
    </button>
    <div id="demo" class="collapse">
        <div class="product-list">
            <c:forEach var="product" items="${productMap}">
                <div class="product-item">
                    <div class="grid-part">
                        <div class="image-part">
                            <img src="<c:url value="/static/img/${product.key.productGroup.photoName}"/>" alt="">
                        </div>
                        <div class="product-name">
                                ${product.key.productGroup.name}
                        </div>
                        <div class="product-type text-muted">
                                ${product.key.weight} <fmt:message key="web.gram" bundle="${ rb }"/>.
                        </div>
                    </div>
                    <div class="flex-part">
                        <span class="text-danger">${product.value}</span>
                        <div class="prise mr-3 ml-5">
                                ${product.key.price*product.value/100} руб.
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<c:import url="fragments/footer.jsp"/>

<tag:impfoot/>

</body>
</html>