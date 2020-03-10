<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="item" items="${orders}">
    <li>${item.id}</li>
    <li>${item.price}</li>
</c:forEach>
<br>
<br>
<br>
<a href="<c:url value="/"/>">Link to home</a>
</body>
</html>
