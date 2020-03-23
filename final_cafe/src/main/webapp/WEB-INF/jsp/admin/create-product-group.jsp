<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/admin/create_product_group/main.css' />">


</head>
<body>


<c:import url="../fragments/navPanel.jsp"/>
<header>
    <h1>Create product group</h1>
</header>

<main class="container">
    <div class="create__data">
        <form action="<c:url value="/admin/create_product_group"/>" method="post" enctype="multipart/form-data">


            <div class="name">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Name"
                       class="form-control">
            </div>

            <div class="description">
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" placeholder="Description"
                       class="form-control">
            </div>

            <div class="custom-file">
                <label class="custom-file-label">
                    <input id="file" type="file" name="file">
                </label>
            </div>

            <div class="type">
                <label for="type">Type</label>
                <select class="form-control" id="type" name="type">
                    <c:forEach var="t" items="${types}">
                        <option>${t}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="items">
                <c:forEach items="${products}" var="p">
                    <div>
                        <label>
                            <span>${p.weight} грамм,</span>
                            <span>${p.price} рублей</span>
                            <input type="checkbox" value="${p.id}" name="products">
                        </label>
                    </div>
                </c:forEach>
            </div>


            <div class="submit">
                <label for="submit"></label>
                <button type="submit" id="submit" class="btn orange__bg">Подтвердить</button>
            </div>

        </form>
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