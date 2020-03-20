<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/registration/main.css' />">

</head>
<body>

<c:import url="fragments/navPanel.jsp"/>


<header>
    <h1>Registration</h1>
</header>


<main class="container">
    <div class="create__data">
        <form action="<c:url value="/registration"/>" method="post">
            <div class="email">
                <label for="email">Email:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="Обязательное поле"> *
                        </a>
                    </span>
                </label>
                <input type="email" id="email" name="email" placeholder="Email"
                       class="form-control">
            </div>

            <div class="phone">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" placeholder="Phone"
                       class="form-control">
            </div>

            <div class="username">
                <label for="username">Username:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="Обязательное поле"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="username" name="username" placeholder="Username"
                       class="form-control">
            </div>

            <div class="password">
                <label for="password">Password:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="Обязательное поле"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="password" name="password" placeholder="Password"
                       class="form-control">
            </div>

            <div class="password__repeat">
                <label for="password__repeat">Repeat password:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="Обязательное поле"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="password__repeat" name="password_repeat" placeholder="Password"
                       class="form-control">
            </div>
            <br>
            <br>
            <br>
            <div class="name__row">
                <label for="name">Name:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="Обязательное поле"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="name" name="name" placeholder="Name"
                       class="form-control">
            </div>

            <div class="surname__row">
                <label for="surname">Surname:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="Обязательное поле"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="surname" name="surname" placeholder="Surname"
                       class="form-control">
            </div>

            <div class="street">
                <label for="street">Street:</label>
                <input type="text" id="street" name="street" placeholder="Street"
                       class="form-control">
            </div>
            <div class="house">
                <label for="house">House:</label>
                <input type="text" id="house" name="house" placeholder="House"
                       class="form-control">
            </div>
            <div class="room">
                <label for="room">Room:</label>
                <input type="text" id="room" name="room" placeholder="Room"
                       class="form-control">
            </div>

            <div class="porch">
                <label for="porch">Porch:</label>
                <input type="text" id="porch" name="porch" placeholder="Porch"
                       class="form-control">
            </div>

            <div class="floor">
                <label for="floor">Floor:</label>
                <input type="text" id="floor" name="floor" placeholder="Floor"
                       class="form-control">
            </div>

            <div class="submit">
                <label for="submit"></label>
                <button type="submit" id="submit" class="btn orange__bg">Подтвердить</button>
            </div>

        </form>
    </div>
</main>
<c:import url="fragments/footer.jsp"/>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>


<script src="/static/js/registration/main.js"></script>

</body>
</html>