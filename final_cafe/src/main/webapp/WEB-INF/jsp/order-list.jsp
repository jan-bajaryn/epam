<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<by.epam.cafe.entity.impl.Order>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>



<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>

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
                <th>
                    <fmt:message key="web.tab.add-product" bundle="${ rb }"/>
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
                    <td>
                        <a href="<c:url value="/page/add-products/${order.id}"/>">
                            <button class="btn orange__bg">
                                <fmt:message key="web.tab.add-product" bundle="${ rb }"/>
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
<tag:impfoot/>

</body>
</html>
