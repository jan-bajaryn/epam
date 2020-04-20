<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groups" scope="request" type="java.util.List<by.epam.cafe.entity.impl.ProductGroup>"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Product group list</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>


    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
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

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
</body>
</html>


<%--    <div class="grid">--%>
<%--        <div>Photo</div>--%>
<%--        <div>Id</div>--%>
<%--        <div>name</div>--%>
<%--        <div>description</div>--%>
<%--        <div>Type</div>--%>
<%--        <div>Delete</div>--%>
<%--        <div>Edit</div>--%>

<%--        <div><img src="/static/img/${p.photoName}" alt="Photo"></div>--%>
<%--        <div>${p.id}</div>--%>
<%--        <div>${p.name}</div>--%>
<%--        <div>${p.description}</div>--%>
<%--        <div>${p.type}</div>--%>
<%--        <div>--%>

<%--            <form action="<c:url value="/admin/delete_product_group"/>" method="post">--%>
<%--                <label>--%>
<%--                    <input type="number" hidden>--%>
<%--                </label>--%>
<%--                <button class="btn orange__bg" type="submit">Delete</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <a href="<c:url value="/admin/edit_product_group/${p.id}"/>">--%>
<%--                <button class="btn orange__bg">Edit</button>--%>
<%--            </a>--%>
<%--        </div>--%>
<%--    </div>--%>