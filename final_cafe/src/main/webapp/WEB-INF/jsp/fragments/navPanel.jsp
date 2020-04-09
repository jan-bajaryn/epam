<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.epam.cafe.entity.enums.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Nav Panel</title>
</head>
<body>

<%--<c:if test="${param.lang == null}">--%>
<%--    <fmt:setLocale value="ru-RU"/>--%>
<%--</c:if>--%>
<%--<c:if test="${param.lang != null}">--%>
<%--    <fmt:setLocale value="${param.lang}"/>--%>
<%--</c:if>--%>
<fmt:setBundle basename="property.text" var="rb"/>

<nav>
    <div class="nav-container">
        <div class="list-nav">
            <div class="icon">
                <i class='fas fa-pizza-slice'></i>
            </div>
            <ul>
                <%--                <li><a href="?">${role}</a></li>--%>
                <li>
                    <a href="<c:url value="/page/?type=PIZZA"/>">
                        <%--                    Пиццы--%>
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
                <%--                <sec:authorize access="!isAuthenticated()">--%>
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
                    <li><a href="<c:url value="/page/admin/user-list"/>">
                        <fmt:message key="web.links.userlist" bundle="${ rb }"/>
                    </a></li>
                    <li><a href="<c:url value="/page/admin/product-list"/>">
                        <fmt:message key="web.links.products" bundle="${ rb }"/>
                    </a></li>
                    <li><a href="<c:url value="/page/admin/product-group-list"/>">
                        <fmt:message key="web.links.productgroups" bundle="${ rb }"/>
                    </a></li>
                </c:if>
                <c:if test="${role eq 'OPERATOR'}">
                    <li><a href="<c:url value="/page/order-list"/>">
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
            <button class="btn">
                <span class="border-right">
                                            <fmt:message key="web.links.basket" bundle="${ rb }"/>
                </span>
                <span>
                <c:if test="${basket==null}">
                    0
                </c:if>
                    ${basket}
                </span>
            </button>
        </div>
    </div>
</nav>
</body>
</html>
