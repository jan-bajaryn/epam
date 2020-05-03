<%@ page import="java.util.List" %>
<%--<%@ page import="by.epam.cafe.entity.Product" %>--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pizzeria</title>

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


<div class="main-container">
    <c:import url="fragments/navPanel.jsp"/>
    <main class="container">
        <div class="title-container">
            <fmt:message key="web.text.products" bundle="${ rb }"/>
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
                        от ${product_group.value} руб.
                    </span>
                        <button class="btn mr-5 myBtn">
                            <fmt:message key="web.btn.choose" bundle="${ rb }"/>
                        </button>
                        <div class="modal">
                            <c:choose>
                                <c:when test="${role eq 'ANON'}">
                                    <form action="<c:url value="/page/anon/put-item"/>" method="post">
                                        <div class="modal-content">
                                            <span class="close">&times;</span>
                                            <div class="modal__main__content">
                                                <img src="<c:url value="/static/img/${product_group.key.getPhotoName()}"/>"
                                                     alt="Photo"/>
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
                                                                    <input type="radio" name="variant"
                                                                           value="${product.id}">
                                                                        ${product.weight}
                                                                    <fmt:message key="web.gram" bundle="${ rb }"/>
                                                                    -
                                                                    <span> ${String.format("%.2f", product.price/100.0)}р</span>
                                                                </label>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>

                                                <button class="btn orange__bg" type="submit">
                                                    <fmt:message key="web.btn.add-basket" bundle="${ rb }"/>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:when>
                                <c:when test="${role eq 'CLIENT'}">
                                    <form action="<c:url value="/page/client/put-item"/>" method="post">
                                        <div class="modal-content">
                                            <span class="close">&times;</span>
                                            <div class="modal__main__content">
                                                <img src="<c:url value="/static/img/${product_group.key.getPhotoName()}"/>"
                                                     alt="Photo"/>
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
                                                                    <input type="radio" name="variant"
                                                                           value="${product.id}">
                                                                        ${product.weight}
                                                                    <fmt:message key="web.gram" bundle="${ rb }"/>
                                                                    -
                                                                    <span> ${String.format("%.2f", product.price/100.0)}р</span>
                                                                </label>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>

                                                <button class="btn .orange__bg" type="submit">
                                                    <fmt:message key="web.btn.add-basket" bundle="${ rb }"/>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="?" method="post">
                                        <div class="modal-content">
                                            <span class="close">&times;</span>
                                            <div class="modal__main__content">
                                                <img src="<c:url value="/static/img/${product_group.key.getPhotoName()}"/>"
                                                     alt="Photo"/>
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
                                                                    <input type="radio" name="variant"
                                                                           value="${product.id}">
                                                                        ${product.weight}
                                                                    <fmt:message key="web.gram" bundle="${ rb }"/>
                                                                    -
                                                                    <span> ${String.format("%.2f", product.price/100.0)}р</span>
                                                                </label>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>

                                                <button class="btn orange__bg" disabled type="submit">
                                                    <fmt:message key="web.btn.add-basket" bundle="${ rb }"/>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </c:forEach>
            <%--            <ctg:info-time/>--%>
        </div>
    </main>
    <c:import url="fragments/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>

    <script src="<c:url value="/static/js/index/main.js"/>"></script>
    <script src="<c:url value="/static/js/index/modals.js"/>"></script>

</div>
</body>
</html>