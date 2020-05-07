<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.epam.cafe.entity.enums.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <title>Nav Panel</title>
</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>

<nav>
    <div class="nav-container">
        <div class="list-nav">
            <div class="icon">
                <i class='fas fa-pizza-slice'></i>
            </div>
            <ul>
                <li>
                    <a href="<c:url value="/page/?type=PIZZA"/>">
                        <fmt:message key="web.links.pizza" bundle="${ rb }"/>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/page/?type=SNACK"/>">
                        <fmt:message key="web.links.snack" bundle="${ rb }"/>
                    </a>
                </li>
                <li><a href="<c:url value="/page/?type=DRINK"/>">
                    <fmt:message key="web.links.drink" bundle="${ rb }"/>
                </a></li>
                <li><a href="<c:url value="/page/?type=DESSERT"/>">
                    <fmt:message key="web.links.dessert" bundle="${ rb }"/>
                </a></li>
                <c:if test="${role eq 'ANON'}">
                    <li><a href="<c:url value="/page/login"/>">
                        <fmt:message key="web.links.login" bundle="${ rb }"/>
                    </a></li>
                </c:if>
                <c:if test="${!(role eq 'ANON')}">
                    <li><a href="<c:url value="/page/cabinet"/>">
                        <fmt:message key="web.links.cabinet" bundle="${ rb }"/>
                    </a></li>
                </c:if>
                <c:if test="${role eq 'ADMIN'}">
                    <li><a href="<c:url value="/page/admin/user-list?pagination=1"/>">
                        <fmt:message key="web.links.user-list" bundle="${ rb }"/>
                    </a></li>
                    <li><a href="<c:url value="/page/admin/product-list?pagination=1"/>">
                        <fmt:message key="web.links.products" bundle="${ rb }"/>
                    </a></li>
                    <li><a href="<c:url value="/page/admin/product-group-list?pagination=1"/>">
                        <fmt:message key="web.links.product-groups" bundle="${ rb }"/>
                    </a></li>
                </c:if>
                <c:if test="${role eq 'OPERATOR'}">
                    <li><a href="<c:url value="/page/order-list?pagination=1"/>">
                        <fmt:message key="web.links.orders" bundle="${ rb }"/>
                    </a></li>
                </c:if>
                <c:if test="${!(role eq 'ANON')}">
                    <li><a href="<c:url value="/page/logout"/>">
                        <fmt:message key="web.links.logout" bundle="${ rb }"/>
                    </a></li>
                </c:if>
            </ul>
        </div>
        <div class="basket">
            <a href="<c:url value="/page/order"/>">
                <button class="btn">
                <span class="border-right">
                    <fmt:message key="web.links.basket" bundle="${ rb }"/>
                </span>
                    <span>
                        <ctg:basket/>
                    </span>
                </button>
            </a>
        </div>
    </div>
</nav>
</body>
</html>
