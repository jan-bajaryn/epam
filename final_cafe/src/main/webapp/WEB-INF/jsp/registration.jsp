<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="fragments/navPanel.jsp"/>


<header>
    <h1>
        <fmt:message key="web.text.registration" bundle="${ rb }"/>
    </h1>
</header>


<main class="container">
    <div class="create__data">
        <form action="<c:url value="/page/registration"/>" method="post">
            <div class="email">
                <label for="email">
                    <fmt:message key="web.inputs.email" bundle="${ rb }"/>
                    :
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.oblicatory" bundle="${ rb }"/>"> *
                        </a>
                    </span>
                </label>
                <input type="email" id="email" name="email"
                       placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="phone">
                <label for="phone">
                    <fmt:message key="web.inputs.phone" bundle="${ rb }"/>
                    :
                </label>
                <input type="text" id="phone" name="phone"
                       placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="username">
                <label for="username">
                    <fmt:message key="web.inputs.username" bundle="${ rb }"/>
                    :
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top">
                           <fmt:message key="web.inputs.oblicatory" bundle="${ rb }"/>
                        </a>
                    </span>
                </label>
                <input type="text" id="username" name="username"
                       placeholder="<fmt:message key="web.inputs.username" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="password">
                <label for="password">
                    <fmt:message key="web.inputs.password" bundle="${ rb }"/>:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.oblicatory" bundle="${ rb }"/>"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="password" name="password"
                       placeholder="<fmt:message key="web.inputs.password" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="password__repeat">
                <label for="password__repeat"><fmt:message key="web.inputs.repeatpassword" bundle="${ rb }"/>
                    :
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.oblicatory" bundle="${ rb }"/>"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="password__repeat" name="password_repeat"
                       placeholder="<fmt:message key="web.inputs.password" bundle="${ rb }"/>"
                       class="form-control">
            </div>
            <br>
            <br>
            <br>
            <div class="name__row">
                <label for="name"><fmt:message key="web.inputs.name" bundle="${ rb }"/>:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.oblicatory" bundle="${ rb }"/>"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="name" name="name"
                       placeholder="<fmt:message key="web.inputs.name" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="surname__row">
                <label for="surname"><fmt:message key="web.inputs.surname" bundle="${ rb }"/>:
                    <span class="obligatory">
                        <a href="#" data-toggle="tooltip"
                           data-placement="top"
                           title="<fmt:message key="web.inputs.oblicatory" bundle="${ rb }"/>"> *
                        </a>
                    </span>
                </label>
                <input type="text" id="surname" name="surname"
                       placeholder="<fmt:message key="web.inputs.surname" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="street">
                <label for="street">
                    <fmt:message key="web.inputs.street" bundle="${ rb }"/>:
                </label>
                <input type="text" id="street" name="street"
                       placeholder="<fmt:message key="web.inputs.street" bundle="${ rb }"/>"
                       class="form-control">
            </div>
            <div class="house">
                <label for="house">
                    <fmt:message key="web.inputs.house" bundle="${ rb }"/>
                    :</label>
                <input type="text" id="house" name="house"
                       placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                       class="form-control">
            </div>
            <div class="room">
                <label for="room">
                    <fmt:message key="web.inputs.room" bundle="${ rb }"/>:
                </label>
                <input type="text" id="room" name="room"
                       placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="porch">
                <label for="porch">
                    <fmt:message key="web.inputs.porch" bundle="${ rb }"/> :
                </label>
                <input type="text" id="porch" name="porch"
                       placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="floor">
                <label for="floor">
                    <fmt:message key="web.inputs.floor" bundle="${ rb }"/>:
                </label>
                <input type="text" id="floor" name="floor"
                       placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                       class="form-control">
            </div>

            <div class="submit">
                <label for="submit"></label>
                <button type="submit" id="submit" class="btn orange__bg">
                    <fmt:message key="web.inputs.submit" bundle="${ rb }"/>
                </button>
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