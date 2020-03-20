<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <link rel="stylesheet" href="<c:url value='static/css/client_cabinet/main.css' />">
</head>
<body>

<div class="main-container">
<%--    <nav>--%>
<%--        <div class="nav-container">--%>
<%--            <div class="list-nav">--%>
<%--                <div class="icon">--%>
<%--                    <i class='fas fa-pizza-slice'></i>--%>
<%--                </div>--%>
<%--                <ul>--%>
<%--                    <li><a href="?">Пиццы</a></li>--%>
<%--                    <li><a href="?">Закуски</a></li>--%>
<%--                    <li><a href="?">Десерты</a></li>--%>
<%--                    <li><a href="?">Напитки</a></li>--%>
<%--                    <li><a href="?">Контакты</a></li>--%>
<%--                    <li><a href="?">О нас</a></li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--            <div class="basket">--%>
<%--                <button class="btn">--%>
<%--                    <span class="border-right">Корзина</span>--%>
<%--                    <span>1</span>--%>
<%--                </button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </nav>--%>
    <c:import url="fragments/navPanel.jsp"/>

    <main class="container">
        <div class="title-container">
            Личный кабинет
        </div>
        <div class="data__input">
            <div class="data__input__label">
                Имя:
            </div>
            <div class="data__input__place">
                Антуан <a href="?" class="input_name">Изменить</a>
            </div>
            <div class="data__input__label">
                Номер телефона:
            </div>
            <div class="data__input__place">
                +375 29 111-11-11
            </div>
            <div class="data__input__label">
                День рождения:
                <i class="fas fa-info-circle birthday"></i>
            </div>
            <div class="data__input__place birth___data">
                <div class="max__width__fixed grid">
                    <select name="cars" class="custom-select">
                        <option selected>День</option>
                        <option value="volvo">Volvo</option>
                        <option value="fiat">Fiat</option>
                        <option value="audi">Audi</option>
                    </select>
                    <select name="cars" class="custom-select">
                        <option selected>Месяц</option>
                        <option value="volvo">Volvo</option>
                        <option value="fiat">Fiat</option>
                        <option value="audi">Audi</option>
                    </select>
                </div>
                <button class="btn orange__bg" disabled>Сохранить</button>
            </div>
            <div class="data__input__label">
                Email:
            </div>
            <div class="data__input__place email___data">
                <div class="max__width__fixed">
                    <input type="email" class="form-control" placeholder="Enter email" id="email">
                </div>
                <button class="btn orange__bg" disabled>Сохранить</button>
            </div>
            <div></div>
            <div class="subscribe__form">
                <div class="subscribe__container">
                    <input type="checkbox" name="subscribe" id="subscribe">
                    <label for="subscribe">Подписаться на рассылку</label>
                    <span>
                    <i class="fas fa-info-circle"></i>
                </span>
                </div>
            </div>
        </div>

        <div class="log__out">
            <button class="btn orange__hover">Выйти</button>
        </div>
    </main>

<%--    <footer class="bg-dark">--%>
<%--        <div class="container">--%>
<%--            <a href="?">О нас</a>--%>
<%--            <a href="?">Почему нашу пиццу все любят</a>--%>
<%--            <a href="?">Наш блог</a>--%>
<%--            <a href="?">Наши спонсоры</a>--%>
<%--        </div>--%>
<%--    </footer>--%>
    <c:import url="fragments/footer.jsp"/>

</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>

</body>
</html>