<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add products to order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
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
                                ${product.key.productGroup.name}
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
                        <span>${product.value}</span>
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
                                ${product.key.productGroup.name}
                        </div>
                        <div class="product-type text-muted">
                                ${product.key.weight} гр.
                        </div>
                    </div>
                    <div class="flex-part">
                        <form action="<c:url value="/page/minus-item-anon"/>" method="post">
                            <input type="hidden" value="${product.key.id}" name="variant">
                            <button type="submit" class="btn mx-3 white__bg__black minus"> -</button>
                        </form>
                        <span>${product.value}</span>
                        <form action='<c:url value="/page/put-item-anon"/>' method="post">
                            <input type="hidden" value="${product.key.id}" name="variant">
                            <button type="submit" class="btn mx-3 white__bg__black plus"> +</button>
                        </form>
                        <div class="prise mr-3">
                                ${String.format("%.2f",(product.key.price*product.value)/100)}
                            <fmt:message key="web.text.rub" bundle="${ rb }"/>
                        </div>
                        <form action="<c:url value="/page/delete-all-anon"/>" method="post">
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
    </div>

    <div class="sum">
        <div class="sum-text">
            <fmt:message key="web.text.sum-order" bundle="${ rb }"/>
        </div>
        <div class="sum-price"> ${sum}
            <fmt:message key="web.text.rub" bundle="${ rb }"/>
        </div>
    </div>

</main>

<c:import url="fragments/footer.jsp"/>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>

</body>
</html>

