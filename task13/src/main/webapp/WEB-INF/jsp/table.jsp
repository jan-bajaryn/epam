<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Table</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>

<header class="mb-5">
    <h1>
        <fmt:message key="web.text.orders" bundle="${ rb }"/>
    </h1>
</header>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>id</th>
        <th>
            <fmt:message key="web.text.creation" bundle="${ rb }"/>
        </th>
        <th>
            <fmt:message key="web.text.price" bundle="${ rb }"/>
        </th>
        <th>

            <fmt:message key="web.text.status" bundle="${ rb }"/>
        </th>
        <th>
            <fmt:message key="web.text.paymenttype" bundle="${ rb }"/>
        </th>
        <th>
            <fmt:message key="web.text.information" bundle="${ rb }"/>
        </th>
        <th>
            <fmt:message key="web.text.ptoducts" bundle="${ rb }"/>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${orders}">
        <tr>
            <td>${item.id}</td>
            <td>${item.creation}</td>
            <td>${item.price}</td>
            <td>${item.status}</td>
            <td>${item.paymentType}</td>
            <td>
                <c:if test="${item.deliveryInf!=null}">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <fmt:message key="web.text.deliveryTime" bundle="${ rb }"/>
                            <fmt:message key="web.text.clientName" bundle="${ rb }"/>
                            <fmt:message key="web.text.address" bundle="${ rb }"/>
                            <fmt:message key="web.text.phone" bundle="${ rb }"/>
                            <fmt:message key="web.text.email" bundle="${ rb }"/>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${item.deliveryInf.deliveryTime}</td>
                            <td>${item.deliveryInf.clientName}</td>
                            <td>${item.deliveryInf.address}</td>
                            <td>${item.deliveryInf.phone}</td>
                            <td>${item.deliveryInf.email}</td>
                        </tr>
                        </tbody>
                    </table>
                </c:if>
            </td>
            <td>
                <c:if test="${item.products!=null}">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><fmt:message key="web.text.name" bundle="${ rb }"/></th>
                            <th><fmt:message key="web.text.description" bundle="${ rb }"/></th>
                            <th><fmt:message key="web.text.photoName" bundle="${ rb }"/></th>
                            <th><fmt:message key="web.text.sprice" bundle="${ rb }"/></th>
                            <th><fmt:message key="web.text.type" bundle="${ rb }"/></th>
                            <th><fmt:message key="web.text.size" bundle="${ rb }"/></th>
                            <th><fmt:message key="web.text.ingredients" bundle="${ rb }"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${item.products}" var="it">
                            <tr>
                                <td>${it.name}</td>
                                <td>${it.description}</td>
                                <td>${it.photoName}</td>
                                <td>${it.price}</td>
                                <td>${it.type}</td>
                                <td>${it.size}</td>
                                <td>
                                    <c:if test="${it.ingredients!=null}">
                                        <ol>
                                            <c:forEach items="${it.ingredients}" var="ing">
                                                <li>
                                                        ${ing}
                                                </li>
                                            </c:forEach>
                                        </ol>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<br>
<br>
<br>
<a href="<c:url value="/"/>">
    <button class="btn btn-primary">Link to home</button>
</a>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
</body>
</html>