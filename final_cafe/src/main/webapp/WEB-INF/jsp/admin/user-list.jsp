<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>


    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/admin/user_list/main.css' />">

</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="../fragments/navPanel.jsp"/>

<header>
    <h1>
        <fmt:message key="web.links.user-list" bundle="${ rb }"/>
    </h1>
</header>

<main class="text-center container">
    <div class="create__new mb-5">
        <a href="<c:url value="/page/admin/create-user"/>">
            <button class="btn white__bg__orange">
                <fmt:message key="web.btn.create-user" bundle="${ rb }"/>
            </button>
        </a>
    </div>

    <div class="text-center">
        <table class="table">
            <thead>
            <tr>
                <th>
                    <fmt:message key="web.tab.identifier" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.is-blocked" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.role" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.username" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.name" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.inputs.surname" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.address" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.details" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.block" bundle="${ rb }"/>
                </th>
                <th>
                    <fmt:message key="web.tab.unblock" bundle="${ rb }"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.blocked}</td>
                    <td>${u.role}</td>
                    <td>${u.username}</td>
                    <td>${u.name}</td>
                    <td>${u.surname}</td>
                    <td>
                        <span>${u.street}</span>
                        &nbsp;
                        <span>${u.house}</span>
                        &nbsp;
                        <span>${u.room}</span>
                        &nbsp;
                        <span>${u.porch}</span>
                        &nbsp;
                        <span>${u.floor}</span>
                        &nbsp;
                    </td>
                    <td>
                        <a href="<c:url value="/page/admin/edit-user/${u.id}"/>">
                            <button class="btn orange__bg">
                                <fmt:message key="web.tab.details" bundle="${ rb }"/>
                            </button>
                        </a>
                    </td>
                    <td>
                        <form action="<c:url value="/page/admin/block/${u.id}"/>" method="post">
                            <button class="btn orange__bg" type="submit">
                                <fmt:message key="web.tab.block" bundle="${ rb }"/>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="<c:url value="/page/admin/unblock/${u.id}"/>" method="post">
                            <button class="btn orange__bg" type="submit">
                                <fmt:message key="web.tab.unblock" bundle="${ rb }"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<c:import url="../fragments/footer.jsp"/>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
</body>
</html>
