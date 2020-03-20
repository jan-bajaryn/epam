<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>User list</h1>
</header>

<main class="container">

    <div class="create__new mb-5">
        <a href="<c:url value="/admin/create_product_group"/>">
            <button class="btn white__bg__orange">
                Create new product group
            </button>
        </a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>Photo</th>
            <th>Id</th>
            <th>name</th>
            <th>description</th>
            <th>Type</th>
            <th>Disabled</th>
            <th>Disable</th>
            <th>Enable</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="p">
            <tr>
                <td><img src="/static/img/${p.photoName}" alt="Photo"></td>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.description}</td>
                <td>${p.type}</td>
                <td>${p.disabled}</td>
                <td>
                    <form action="<c:url value="/admin/disable_product_group"/>" method="post">
                        <button class="btn orange__bg" type="submit">Disable</button>
                        <label>
                            <input type="number" hidden name="id" value="${p.id}">
                        </label>
                    </form>
                </td>

                <td>
                    <form action="<c:url value="/admin/enable_product_group"/>" method="post">
                        <button class="btn orange__bg" type="submit">Enable</button>
                        <label>
                            <input type="number" hidden name="id" value="${p.id}">
                        </label>
                    </form>
                </td>
                <td>
                    <a href="<c:url value="/admin/edit_product_group/${p.id}"/>">
                        <button class="btn orange__bg">Edit</button>
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