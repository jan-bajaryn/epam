<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.epam.cafe.entity.enums.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nav Panel</title>
</head>
<body>
<nav>
    <div class="nav-container">
        <div class="list-nav">
            <div class="icon">
                <i class='fas fa-pizza-slice'></i>
            </div>
            <ul>
<%--                <li><a href="?">${role}</a></li>--%>
                <li><a href="<c:url value="/page/?type=PIZZA"/>">Пиццы</a></li>
                <li><a href="<c:url value="/page/?type=SNACK"/>">Закуски</a></li>
                <li><a href="<c:url value="/page/?type=DRINK"/>">Напитки</a></li>
                <li><a href="<c:url value="/page/?type=DESSERT"/>">Десерты</a></li>
                <%--                <sec:authorize access="!isAuthenticated()">--%>
                <c:if test="${role eq 'ANON'}">
                    <li><a href="<c:url value="/page/login"/>">Войти</a></li>
                </c:if>
                <c:if test="${!(role eq 'ANON')}">
                    <li><a href="<c:url value="/page/cabinet"/>">Личный кабинет</a></li>
                </c:if>
                <c:if test="${role eq 'ADMIN'}">
                    <li><a href="<c:url value="/page/admin/user-list"/>">Пользователи</a></li>
                    <li><a href="<c:url value="/page/admin/product-list"/>">Продукты</a></li>
                    <li><a href="<c:url value="/page/admin/product-group-list"/>">Группы продуктов</a></li>
                </c:if>
                <c:if test="${role eq 'OPERATOR'}">
                    <li><a href="<c:url value="/page/order-list"/>">Заказы</a></li>
                </c:if>
                <c:if test="${!(role eq 'ANON')}">
                    <li><a href="<c:url value="/page/logout"/>">Выйти</a></li>
                </c:if>
            </ul>
        </div>
        <div class="basket">
            <button class="btn">
                <span class="border-right">Корзина</span>
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
