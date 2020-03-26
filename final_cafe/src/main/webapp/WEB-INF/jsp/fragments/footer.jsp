<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <title>Footer fragment</title>
</head>
<body>
<footer class="bg-dark">
    <div class="container">
        <a href="?">О нас</a>
        <a href="?">Почему нашу пиццу все любят</a>
        <a href="?">Наш блог</a>
        <a href="?">Наши спонсоры</a>
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
