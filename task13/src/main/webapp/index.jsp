<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<link rel="stylesheet" href="css/index.css">
<body>
<div>

    <fmt:setLocale value="ru_RU"/>
    <fmt:setBundle basename="property.text" var="rb"/>

    <c:if test="${failed!=null && failed==true}">
        <div class="red">
            An error founded in you file.
        </div>
    </c:if>
</div>


<div>
    <form action="${pageContext.request.contextPath}/index_page" method="post" enctype="multipart/form-data">
        <label>
            <input type="radio" name="type" value="sax">
            SAX
        </label>
        <br>
        <label>
            <input type="radio" name="type" value="dom">
            DOM
        </label>
        <br>
        <label>
            <input type="radio" name="type" value="stax">
            STAX
        </label>
        <br>
        <label>
            <input type="file" name="data" accept="application/xml">
        </label>
        <br>
        <button disabled type="submit">
            <%--            Submit--%>
            <fmt:message key="button.submit" bundle="${ rb }"/>
        </button>
    </form>
</div>
<script src="js/index.js"></script>

<%--<form action="${pageContext.request.contextPath}/test" method="post"--%>
<%--      enctype="multipart/form-data">--%>
<%--    <input type="file" name="data" accept="application/xml">--%>
<%--    <input type="submit" value="abc">--%>
<%--</form>--%>


</body>
</html>
