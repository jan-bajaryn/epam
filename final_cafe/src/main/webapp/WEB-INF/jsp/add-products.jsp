<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Add products to order</title>

    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/add-products/main.css' />">


</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="fragments/navPanel.jsp"/>

<header class="container">
    <h1>
        <fmt:message key="web.tab.add-product" bundle="${ rb }"/>
    </h1>
</header>

<main class="container">

    <div class="product-list">
        <c:if test="${contain_products!=null}">
            <c:forEach var="product" items="${contain_products}">

                <div class="product-item">
                    <div class="grid-part">
                        <div class="image-part">
                            <img src="<c:url value="/static/img/${product.key.productGroup.photoName}"/>" alt="">
                        </div>
                        <div class="product-name">
                                <c:out value="${product.key.productGroup.name}"/>
                        </div>
                        <div class="product-type text-muted">
                                ${product.key.weight} гр.
                        </div>
                    </div>
                    <div class="flex-part">
                        <form action="<c:url value="/page/operator/minus-product"/>" method="post">
                            <input type="hidden" value="${product.key.id}" name="product_id">
                            <input type="hidden" value="${id}" name="order_id">
                            <button type="submit" class="btn mx-3 white__bg__black minus"> -</button>
                        </form>
                        <span><c:out value="${product.value}"/></span>
                        <form action='<c:url value="/page/operator/plus-product"/>' method="post">
                            <input type="hidden" value="${product.key.id}" name="product_id">
                            <input type="hidden" value="${id}" name="order_id">
                            <button type="submit" class="btn mx-3 white__bg__black plus"> +</button>
                        </form>
                        <div class="prise mr-3">
                                ${String.format("%.2f",(product.key.price*product.value)/100)}
                            <fmt:message key="web.text.rub" bundle="${ rb }"/>
                        </div>
                        <form action="<c:url value="/page/operator/delete-product"/>" method="post">
                            <input type="hidden" value="${id}" name="order_id">
                            <button class="abc" type="submit">
                                <i class="fa fa-trash mr-3" aria-hidden="true"></i>
                            </button>
                            <label>
                                <input name="id" type="number" value="${product.key.id}"
                                       style="display: none;">
                            </label>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${not_contain_products!=null}">
            <c:forEach var="product" items="${not_contain_products}">

                <div class="product-item">
                    <div class="grid-part">
                        <div class="image-part">
                            <img src="<c:url value="/static/img/${product.key.productGroup.photoName}"/>" alt="">
                        </div>
                        <div class="product-name">
                                <c:out value="${product.key.productGroup.name}"/>
                        </div>
                        <div class="product-type text-muted">
                                ${product.key.weight} гр.
                        </div>
                    </div>
                    <div class="flex-part">
                        <form action="<c:url value="/page/operator/minus-product"/>" method="post">
                            <input type="hidden" value="${product.key.id}" name="product_id">
                            <input type="hidden" value="${id}" name="order_id">
                            <button type="submit" class="btn mx-3 white__bg__black minus" disabled> -</button>
                        </form>
                        <span><c:out value="${product.value}"/></span>
                        <form action='<c:url value="/page/operator/plus-product"/>' method="post">
                            <input type="hidden" value="${product.key.id}" name="product_id">
                            <input type="hidden" value="${id}" name="order_id">
                            <button type="submit" class="btn mx-3 white__bg__black plus"> +</button>
                        </form>
                        <div class="prise mr-3">
                                ${String.format("%.2f",(product.key.price*product.value)/100)}
                            <fmt:message key="web.text.rub" bundle="${ rb }"/>
                        </div>
                        <form action="?" method="get">
                            <button class="abc" type="submit" disabled>
                                <i class="fa fa-trash mr-3" aria-hidden="true"></i>
                            </button>
                            <label>
                                <input name="id" type="number" value=""
                                       style="display: none;">
                            </label>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <div class="sum">
        <div class="sum-text">
            <fmt:message key="web.text.sum-order" bundle="${ rb }"/>
        </div>
        <div class="sum-price"> <c:out value="${sum}"/>
            <fmt:message key="web.text.rub" bundle="${ rb }"/>
        </div>
    </div>

</main>

<c:import url="fragments/footer.jsp"/>

<tag:impfoot/>


</body>
</html>

