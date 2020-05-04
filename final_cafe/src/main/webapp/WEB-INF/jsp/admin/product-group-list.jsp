<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groups" scope="request" type="java.util.List<by.epam.cafe.entity.impl.ProductGroup>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Product group list</title>

    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>


    <link rel="stylesheet" href="<c:url value='/static/css/admin/product_group_list/main.css' />">


</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>
        <fmt:message key="web.links.product-groups" bundle="${ rb }"/>
    </h1>
</header>

<main class="container">

    <div class="create__new mb-5">
        <a href="<c:url value="/page/admin/create-product-group"/>">
            <button class="btn white__bg__orange">
                <fmt:message key="web.btn.create-new-product-group" bundle="${ rb }"/>
            </button>
        </a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>
                <fmt:message key="web.tab.photo" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.identifier" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.name" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.description" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.type" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.disabled" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.disable" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.enable" bundle="${ rb }"/>
            </th>
            <th>
                <fmt:message key="web.tab.edit" bundle="${ rb }"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="p">
            <tr>
                <td><img src="<c:url value="/static/img/${p.photoName}"/>" alt="Photo"></td>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.description}</td>
                <td>${p.type}</td>
                <td>${p.disabled}</td>
                <td>
                    <form action="<c:url value="/page/admin/disable-product-group"/>" method="post">
                        <button class="btn orange__bg" type="submit">
                            <fmt:message key="web.tab.disable" bundle="${ rb }"/>
                        </button>
                        <label>
                            <input type="number" hidden name="id" value="${p.id}">
                        </label>
                    </form>
                </td>

                <td>
                    <form action="<c:url value="/page/admin/enable-product-group"/>" method="post">
                        <button class="btn orange__bg" type="submit">
                            <fmt:message key="web.tab.enable" bundle="${ rb }"/>
                        </button>
                        <label>
                            <input type="number" hidden name="id" value="${p.id}">
                        </label>
                    </form>
                </td>
                <td>
                    <a href="<c:url value="/page/admin/edit-product-group/${p.id}"/>">
                        <button class="btn orange__bg">
                            <fmt:message key="web.tab.edit" bundle="${ rb }"/>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<c:import url="../fragments/footer.jsp"/>

<tag:impfoot/>

</body>
</html>