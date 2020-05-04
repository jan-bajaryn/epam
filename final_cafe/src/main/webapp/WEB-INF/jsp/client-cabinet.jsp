<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Client cabinet</title>
    <tag:imphead/>

    <link rel="stylesheet" href="<c:url value='/static/css/client_cabinet/main.css' />">
</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>

<div class="main-container">
    <c:import url="fragments/navPanel.jsp"/>

    <main class="container">
        <div class="title-container">
            <fmt:message key="web.text.self-cabinet" bundle="${ rb }"/>
        </div>
        <div class="data__input">
            <div class="data__input__label">
                <fmt:message key="web.inputs.name" bundle="${ rb }"/>
            </div>
            <div class="data__input__place">
                Антуан <a href="?" class="input_name">
                <fmt:message key="web.tab.edit" bundle="${ rb }"/>
            </a>
            </div>
            <div class="data__input__label">
                <fmt:message key="web.inputs.phone" bundle="${ rb }"/>
            </div>
            <div class="data__input__place">
                +375 29 111-11-11
            </div>
            <div class="data__input__label">
                <fmt:message key="web.inputs.birthday" bundle="${ rb }"/>
                <i class="fas fa-info-circle birthday"></i>
            </div>
            <div class="data__input__place birth___data">
                <div class="max__width__fixed grid">
                    <select name="cars" class="custom-select">
                        <option selected>
                            <fmt:message key="web.inputs.day" bundle="${ rb }"/>
                        </option>
                        <option value="volvo">Volvo</option>
                        <option value="fiat">Fiat</option>
                        <option value="audi">Audi</option>
                    </select>
                    <select name="cars" class="custom-select">
                        <option selected>
                            <fmt:message key="web.inputs.month" bundle="${ rb }"/>
                        </option>
                        <option value="volvo">Volvo</option>
                        <option value="fiat">Fiat</option>
                        <option value="audi">Audi</option>
                    </select>
                </div>
                <button class="btn orange__bg" disabled>
                    <fmt:message key="web.inputs.save" bundle="${ rb }"/>
                </button>
            </div>
            <div class="data__input__label">
                <fmt:message key="web.inputs.email" bundle="${ rb }"/>
            </div>
            <div class="data__input__place email___data">
                <div class="max__width__fixed">
                    <input type="email" class="form-control" placeholder="Enter email" id="email">
                </div>
                <button class="btn orange__bg" disabled>
                    <fmt:message key="web.inputs.save" bundle="${ rb }"/>
                </button>
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
            <button class="btn orange__hover">
                <fmt:message key="web.links.logout" bundle="${ rb }"/>
            </button>
        </div>
    </main>

    <c:import url="fragments/footer.jsp"/>

</div>

<tag:impfoot/>


</body>
</html>