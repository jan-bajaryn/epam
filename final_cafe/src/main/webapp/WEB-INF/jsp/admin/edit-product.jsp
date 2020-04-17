<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="product" scope="request" type="by.epam.cafe.entity.impl.Product"/>
<jsp:useBean id="groups" scope="request" type="java.util.List<by.epam.cafe.entity.impl.ProductGroup>"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/admin/edit_product/main.css' />">


</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>
        <fmt:message key="web.text.edit-product" bundle="${ rb }"/>
    </h1>
</header>

<main class="container">
    <c:if test="${product!=null}">
        <div class="create__data">
            <form action="<c:url value="/page/admin/edit_product"/>" method="post">
                <div class="id">
                    <label for="id">
                        <fmt:message key="web.tab.identifier" bundle="${ rb }"/>
                    </label>
                    <input type="number" id="id" name="id"
                           placeholder="<fmt:message key="web.tab.identifier" bundle="${ rb }"/>"
                           class="form-control" value="${product.id}" readonly>
                </div>

                <div class="product_group">
                    <label for="product_group">
                        <fmt:message key="web.inputs.product-group" bundle="${ rb }"/>
                    </label>
                    <select class="form-control" id="product_group" name="product_group">
                        <c:if test="${product.productGroup!=null}">
                            <option value="${product.productGroup.id}">${product.productGroup.name}</option>
                            <option value="">
                                <fmt:message key="web.text.empty" bundle="${ rb }"/>
                            </option>
                        </c:if>
                        <c:if test="${product.productGroup == null}">
                            <option value="">
                                <fmt:message key="web.text.empty" bundle="${ rb }"/>
                            </option>
                        </c:if>
                        <c:forEach var="g" items="${groups}">
                            <option value="${g.id}">${g.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="price">
                    <label for="price">
                        <fmt:message key="web.inputs.price" bundle="${ rb }"/>
                    </label>
                    <input type="number" id="price" name="price"
                           placeholder="<fmt:message key="web.inputs.price" bundle="${ rb }"/>"
                           class="form-control" value="${product.price}">
                </div>

                <div class="weight">
                    <label for="weight">
                        <fmt:message key="web.tab.weight" bundle="${ rb }"/>
                    </label>
                    <input type="number" id="weight" name="weight"
                           placeholder="<fmt:message key="web.tab.weight" bundle="${ rb }"/>"
                           class="form-control" value="${product.weight}">
                </div>


                <div class="submit">
                    <label for="submit"></label>
                    <button type="submit" id="submit" class="btn orange__bg">
                        <fmt:message key="web.inputs.submit" bundle="${ rb }"/>
                    </button>
                </div>

            </form>
        </div>
    </c:if>
</main>

<c:import url="../fragments/footer.jsp"/>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
</body>
</html>