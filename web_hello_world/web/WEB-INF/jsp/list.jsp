<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User list</h1>

<ul>
    <c:forEach items="${strings}" var="string">
        <li>${string}</li>
    </c:forEach>
</ul>

</body>
</html>
