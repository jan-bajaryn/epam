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
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/login/main.css' />">


</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="fragments/navPanel.jsp"/>


<header class="container">
    <h1>
        <fmt:message key="web.links.login" bundle="${ rb }"/>
    </h1>
</header>


<main class="container">
    <form action="<c:url value="/page/login"/>" method="post">
        <%--        enctype="application/x-www-form-urlencoded"--%>
        <div class="username__group">
            <label for="username">
                <fmt:message key="web.inputs.username" bundle="${ rb }"/>:
            </label>
            <input type="text" id="username"
                   placeholder="<fmt:message key="web.inputs.username" bundle="${ rb }"/>"
                   class="form-control" name="username">
        </div>

        <div class="password__group">
            <label for="password"><fmt:message key="web.inputs.password" bundle="${ rb }"/>:</label>
            <input name="password" type="password" id="password" class="form-control"
                   placeholder="<fmt:message key="web.inputs.password" bundle="${ rb }"/>">
        </div>


        <c:if test="${not empty target_url}">
            <input type="hidden"
                   name="target_url"
                   value="${target_url}"/>
        </c:if>
        <%--        <input type="hidden"--%>
        <%--               name="${_csrf.parameterName}"--%>
        <%--               value="${_csrf.token}"/>--%>

        <div class="submit">
            <button class="btn white__bg__orange" type="submit">
                <fmt:message key="web.inputs.submit" bundle="${ rb }"/>
            </button>
        </div>
    </form>

    <div class="links">
        <div class="registration">
            <a href="<c:url value="/page/registration"/>">
                <fmt:message key="web.text.not-reg-yet" bundle="${ rb }"/>
            </a>
        </div>
        <div class="forgot__password">
            <a href="<c:url value="/page/forgot-password"/>">
                <fmt:message key="web.text.forgot-password" bundle="${ rb }"/>
            </a>
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