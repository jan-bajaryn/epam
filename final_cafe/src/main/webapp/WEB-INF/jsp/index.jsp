<%@ page import="java.util.List" %>
<%--<%@ page import="by.epam.cafe.entity.Product" %>--%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
          crossorigin="anonymous">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="<c:url value='/static/css/index/main.css'/>">
    <link rel="stylesheet" href="<c:url value='/static/css/index/modals.css'/>">
</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>
<fmt:setLocale value="ru-RU"/>

<div class="main-container">
    <c:import url="fragments/navPanel.jsp"/>
    <main class="container">
        <div class="title-container">
            Пиццы
        </div>
        <div class="p_card-list">
            <c:forEach var="product_group" items="${products}">
                <div class="p_card">
                    <div class="p_card-image">
                        <img src="<c:url value="/static/img/${product_group.key.getPhotoName()}"/>" alt="">
                    </div>
                    <div class="p_card-name">
                            ${product_group.key.getName()}
                    </div>
                    <div class="p_card-description text-muted">
                            ${product_group.key.getDescription()}
                    </div>
                    <div class="p_card-footer md-2">
                    <span>
                        от ${String.format("%.2f", product_group.value/100.0)} руб.
                    </span>
                        <button class="btn mr-5 myBtn">
                            Выбрать
                        </button>
                        <div class="modal">
                            <form action="/put_item" method="get">
                                <div class="modal-content">
                                    <span class="close">&times;</span>
                                    <div class="modal__main__content">
                                        <img src="/static/img/${product_group.key.getPhotoName()}" alt="Photo"/>
                                        <div class="content__description">
                                            <div class="header">
                                                    ${product_group.key.getName()}
                                            </div>
                                            <div class="description text-muted">
                                                    ${product_group.key.getDescription()}
                                            </div>
                                            <div>
                                                <c:forEach var="product" items="${product_group.key.products}">
                                                    <div>
                                                        <label>
                                                            <input type="radio" name="variant" value="${product.id}">
                                                                ${product.weight}
<%--                                                            грамм--%>
                                                            <fmt:message key="web.gram" bundle="${ rb }"/>
                                                            -
                                                            <span> ${String.format("%.2f", product.price/100.0)}р</span>
                                                        </label>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <button class="btn .orange__bg" type="submit">Добавить в корзину</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </c:forEach>
            <ctg:info-time/>
        </div>
    </main>
    <c:import url="fragments/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>

    <script src="/static/js/index/modals.js"></script>
    <script src="/static/js/index/main.js"></script>

</div>
</body>
</html>