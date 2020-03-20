<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li><a href="<c:url value="/?type=PIZZA"/>">Пиццы</a></li>
                <li><a href="<c:url value="/?type=SNACK"/>">Закуски</a></li>
                <li><a href="<c:url value="/?type=DRINK"/>">Напитки</a></li>
                <li><a href="<c:url value="/?type=DESSERT"/>">Десерты</a></li>
                <%--                <sec:authorize access="!isAuthenticated()">--%>
                <li><a href="<c:url value="/login"/>">Войти</a></li>
                <%--                </sec:authorize>--%>
                <%--                <sec:authorize access="hasAuthority('ADMIN')">--%>
                <li><a href="<c:url value="/admin/user_list"/>">Пользователи</a></li>
                <li><a href="<c:url value="/admin/product_list"/>">Продукты</a></li>
                <li><a href="<c:url value="/admin/product_group_list"/>">Группы продуктов</a></li>
                <%--                </sec:authorize>--%>
                <%--                <sec:authorize access="hasAuthority('OPERATOR')">--%>
                <li><a href="<c:url value="/order-list"/>">Заказы</a></li>
                <%--                </sec:authorize>--%>
                <%--                <sec:authorize access="isAuthenticated()">--%>
                <li><a href="<c:url value="/logout"/>">Выйти</a></li>
                <%--                </sec:authorize>--%>
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
