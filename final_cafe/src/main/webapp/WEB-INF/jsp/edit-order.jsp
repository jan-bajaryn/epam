<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="order" scope="request" type="by.epam.cafe.entity.impl.Order"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>


<!doctype html>
<html lang="en">
<head>
    <title>Edit single order</title>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/edit-order/main.css' />">


</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>


<c:import url="fragments/navPanel.jsp"/>

<header class="container">
    <h1>
        <fmt:message key="web.text.edit-order" bundle="${ rb }"/>
    </h1>
</header>

<main class="container">
    <c:if test="${order!=null}">
        <form action="<c:url value="/page/edit-order"/>" method="post">
            <div class="modal__main__content">
                <div class="body__form">
                    <div class="id__row">
                        <label for="id">
                            <fmt:message key="web.tab.order-number" bundle="${ rb }"/>
                        </label>
                        <input type="number" value="${order.id}" class="form-control" id="id" name="id" readonly>
                    </div>

                    <div class="status__row">
                        <label for="status">
                            <fmt:message key="web.inputs.order-status" bundle="${ rb }"/>
                        </label>
                        <select class="form-control" id="status" name="status">
                            <option><c:out value="${order.status}"/></option>
                            <c:forEach var="stat" items="${statuses}">
                                <option><c:out value="${stat}"/></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="payment_type__row">
                        <label for="payment_type">
                            <fmt:message key="web.inputs.payment-type" bundle="${ rb }"/>
                        </label>
                        <select class="form-control" id="payment_type" name="payment_type">
                            <option>${order.paymentType}</option>
                            <c:forEach var="type" items="${types}">
                                <option><c:out value="${type}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="price__row">
                        <label for="price">
                            <fmt:message key="web.inputs.order-price" bundle="${ rb }"/>
                        </label>
                        <input type="number" value="${order.price}" class="form-control" id="price" name="price">
                    </div>
                    <div class="name__row">
                        <label for="name">
                            <fmt:message key="web.inputs.name" bundle="${ rb }"/>
                            :
                        </label>
                        <input type="text" id="name" name="name" placeholder="Имя"
                               class="form-control" value="<c:out value="${order.clientName}"/>">
                    </div>
                    <div class="time__row">
                        <label for="time">
                            <fmt:message key="web.inputs.time" bundle="${ rb }"/>
                            :
                        </label>
                        <input type="time" id="time" name="time"
                               placeholder="<fmt:message key="web.inputs.date-deliver" bundle="${ rb }"/>"
                               class="form-control" value="${time}">
                    </div>
                    <div class="first__row">
                        <label for="street">
                            <fmt:message key="web.inputs.street" bundle="${ rb }"/>
                            :
                        </label>
                        <input type="text" class="form-control" placeholder="Улица" id="street"
                               name="street" value="<c:out value="${order.deliveryInf.street}"/>">
                        <label for="house">
                            <fmt:message key="web.inputs.house" bundle="${ rb }"/>
                        </label>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                               id="house" name="house"
                               value="<c:out value="${order.deliveryInf.house}"/>">
                    </div>
                    <div class="sec__row">
                        <label for="room">
                            <fmt:message key="web.inputs.room" bundle="${ rb }"/>
                        </label>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"
                               id="room"
                               name="room" value="<c:out value="${order.deliveryInf.room}"/>">
                        <label for="porch">
                            <fmt:message key="web.inputs.porch" bundle="${ rb }"/>
                        </label>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"
                               id="porch"
                               name="porch" value="<c:out value="${order.deliveryInf.porch}"/>">
                        <label for="floor">
                            <fmt:message key="web.inputs.floor" bundle="${ rb }"/>
                        </label>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                               id="floor" name="floor"
                               value="<c:out value="${order.deliveryInf.floor}"/>">
                    </div>
                    <div class="phone__row">
                        <label for="tel">
                            <fmt:message key="web.inputs.phone" bundle="${ rb }"/>
                        </label>
                        <input type="tel" class="form-control"
                               placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                               id="tel" name="tel"
                               value="<c:out value="${order.deliveryInf.phone}"/>">
                    </div>
                    <div class="email__row">
                        <label for="email">
                            <fmt:message key="web.inputs.email" bundle="${ rb }"/>
                        </label>
                        <input type="email" class="form-control"
                               placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                               id="email"
                               name="email" value="<c:out value="${order.deliveryInf.email}"/>">
                    </div>
                    <div class="comments__row">
                        <label for="comments">
                            <fmt:message key="web.inputs.comments" bundle="${ rb }"/>
                        </label>
                        <textarea class="form-control" id="comments" name="comments"
                                  placeholder="<fmt:message key="web.inputs.comments" bundle="${ rb }"/>">
                            <c:out value="${order.deliveryInf.comments}"/>
                        </textarea>
                    </div>
                </div>
                <button class="btn orange__bg accept__btn" type="submit">
                    <fmt:message key="web.tab.edit" bundle="${ rb }"/>
                </button>
            </div>
        </form>
    </c:if>
    <c:if test="${order==null}">
        <div>
            <fmt:message key="web.text.noorder" bundle="${ rb }"/>
        </div>
    </c:if>
</main>

<c:import url="fragments/footer.jsp"/>


<tag:impfoot/>


</body>
</html>

