<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Login</title>


    <tag:imphead footer="${true}" navbar="${true}" btns="${true}" />

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
    <c:if test="${not empty redirect_authentication_error}">
        <div class="alert alert-info alert-dismissible">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <fmt:message key="web.errors.authentication" bundle="${ rb }"/>
        </div>
    </c:if>
    <form action="<c:url value="/page/login"/>" method="post">
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


        <c:if test="${not empty redirect_target_url}">
            <input type="hidden"
                   name="target_url"
                   value="<c:out value="${redirect_target_url}"/>"/>
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

<tag:impfoot/>

</body>
</html>