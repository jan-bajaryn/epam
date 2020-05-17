<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="users" scope="request" type="java.util.List<by.epam.cafe.entity.db.impl.User>"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Users</title>

    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>

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
                    <td><c:out value="${u.username}"/></td>
                    <td><c:out value="${u.name}"/></td>
                    <td><c:out value="${u.surname}"/></td>
                    <td>
                        <span><c:out value="${u.street}"/></span>
                        &nbsp;
                        <span><c:out value="${u.house}"/></span>
                        &nbsp;
                        <span><c:out value="${u.room}"/></span>
                        &nbsp;
                        <span><c:out value="${u.porch}"/></span>
                        &nbsp;
                        <span><c:out value="${u.floor}"/></span>
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
    <tag:pagination paginationMap="${paginationMap}" url="/page/admin/user-list" current="${param.pagination}"/>
</main>
<c:import url="../fragments/footer.jsp"/>

<tag:impfoot/>

</body>
</html>
