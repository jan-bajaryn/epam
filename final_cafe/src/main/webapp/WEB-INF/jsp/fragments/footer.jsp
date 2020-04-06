<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<%--TODO replacement--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Footer fragment</title>
</head>
<body>
<footer class="bg-dark">

<%--    TODO with include works fine--%>

            <c:if test="${param.lang == null}">
                <fmt:setLocale value="ru-RU"/>
            </c:if>
            <c:if test="${param.lang != null}">
                <fmt:setLocale value="${param.lang}"/>
            </c:if>
<%--    <fmt:setLocale value="ru-RU"/>--%>
    <fmt:setBundle basename="property.text" var="rb" scope="page"/>


    <div class="container">
        <a href="?">
            <%--            О нас--%>
                        <fmt:message key="web.links.aboutus" bundle="${ rb }"/>
        </a>
        <a href="?">
            <%--                        Почему нашу пиццу все любят--%>
            <fmt:message key="web.links.alllove" bundle="${ rb }"/>
        </a>
        <a href="?">
            <%--            Наш блог--%>
                        <fmt:message key="web.links.outblog" bundle="${ rb }"/>
        </a>
        <a href="?">
                        <fmt:message key="web.links.sponsors" bundle="${ rb }"/>
            <%--            Наши спонсоры--%>
        </a>
        <a href="?">
            <ctg:hello role="${param.role}"/>
        </a>
        <ctg:table-revenue rows="${ rw.size }" head="Revenue">
            ${ rw.revenue }
        </ctg:table-revenue>
        <a href=""><br><br></a>
        <ctg:table-revenue>5 rub BulbaComp</ctg:table-revenue>
    </div>

</footer>
</body>
</html>
