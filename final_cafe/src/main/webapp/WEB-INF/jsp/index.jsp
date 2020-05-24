<%@ page import="java.util.List" %>
<%--<%@ page import="by.epam.cafe.entity.Product" %>--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Pizzeria</title>

    <tag:imphead/>

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
                            <c:out value="${product_group.key.getName()}"/>
                    </div>
                    <div class="p_card-description text-muted">
                            <c:out value="${product_group.key.getDescription()}"/>
                    </div>
                    <div class="p_card-footer md-2">
                    <span>
                        <fmt:message key="web.text.from" bundle="${ rb }"/> <tag:money input="${product_group.value}"/>
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
                                                            <c:out value="${product_group.key.getName()}"/>
                                                    </div>
                                                    <div class="description text-muted">
                                                            <c:out value="${product_group.key.getDescription()}"/>
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
                                                                    <span> <tag:money input="${product.price}"/></span>
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
                                                            <c:out value="${product_group.key.getName()}"/>
                                                    </div>
                                                    <div class="description text-muted">
                                                            <c:out value="${product_group.key.getDescription()}"/>
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
                                                            <c:out value="${product_group.key.getName()}"/>
                                                    </div>
                                                    <div class="description text-muted">
                                                            <c:out value="${product_group.key.getDescription()}"/>
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
        </div>
    </main>
    <c:import url="fragments/footer.jsp"/>


    <tag:impfoot/>

    <script src="<c:url value="/static/js/index/main.js"/>"></script>
    <script src="<c:url value="/static/js/index/modals.js"/>"></script>

</div>
</body>
</html>