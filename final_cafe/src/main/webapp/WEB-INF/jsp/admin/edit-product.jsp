<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="product" scope="request" type="by.epam.cafe.entity.db.impl.Product"/>
<jsp:useBean id="groups" scope="request" type="java.util.List<by.epam.cafe.entity.db.impl.ProductGroup>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Edit product</title>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}" err="${true}"/>

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
            <form action="<c:url value="/page/admin/edit-product"/>" method="post">
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
                    <c:if test="${not empty redirect_product_group_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_product_group}"/>">
                        <fmt:message key="web.errors.field" bundle="${ rb }"/>
                    </span>
                    </c:if>
                    <select class="form-control" id="product_group" name="product_group">
                        <c:if test="${product.productGroup!=null}">
                            <option value="${product.productGroup.id}"><c:out
                                    value="${product.productGroup.name}"/></option>
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
                            <option value="${g.id}"><c:out value="${g.name}"/></option>
                        </c:forEach>
                    </select>
                </div>

                <div class="price">
                    <label for="price">
                        <fmt:message key="web.inputs.price" bundle="${ rb }"/>
                    </label>
                    <c:if test="${not empty redirect_price_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_price}"/>">
                        <fmt:message key="web.errors.field" bundle="${ rb }"/>
                    </span>
                    </c:if>
                    <input type="number" id="price" name="price"
                           placeholder="<fmt:message key="web.inputs.price" bundle="${ rb }"/>"
                           class="form-control" value="${product.price}">
                </div>

                <div class="weight">
                    <label for="weight">
                        <fmt:message key="web.tab.weight" bundle="${ rb }"/>
                    </label>
                    <c:if test="${not empty redirect_weight_error}">
                    <span class="error__message"
                          title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_weight}"/>">
                        <fmt:message key="web.errors.field" bundle="${ rb }"/>
                    </span>
                    </c:if>
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

<tag:impfoot/>

</body>
</html>