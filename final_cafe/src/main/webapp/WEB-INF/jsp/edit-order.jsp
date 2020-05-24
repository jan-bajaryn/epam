<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="order" scope="request" type="by.epam.cafe.entity.db.impl.Order"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>


<!doctype html>
<html lang="en">
<head>
    <title>Edit single order</title>
    <tag:imphead footer="${true}" navbar="${true}" btns="${true}" err="${true}"/>

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
        <c:if test="${not empty redirect_unknown_error}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <fmt:message key="web.errors.unknown-error" bundle="${ rb }"/>
            </div>
        </c:if>
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

                        <c:if test="${not empty redirect_status_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_status}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
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
                        <c:if test="${not empty redirect_payment_type_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_payment_type}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>

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
                        <c:if test="${not empty redirect_price_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_price}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="number" value="${order.price}" class="form-control" id="price" name="price"
                               min="0" max="1000000000" required>
                    </div>
                    <div class="name__row">
                        <label for="name">
                            <fmt:message key="web.inputs.name" bundle="${ rb }"/>
                            :
                        </label>

                        <c:if test="${not empty redirect_name_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_name}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>

                        <input type="text" id="name" name="name" placeholder="Имя"
                               class="form-control" value="<c:out value="${order.clientName}"/>"
                               required
                               pattern="^[A-Za-zА-Яа-яЁё]+(([',. \\-][A-Za-zА-Яа-яЁё ])?[A-Za-zА-Яа-яЁё]*)*$">
                    </div>
                    <div class="time__row">
                        <label for="time">
                            <fmt:message key="web.inputs.time" bundle="${ rb }"/>
                            :
                        </label>
                        <c:if test="${not empty redirect_time_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_time}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="time" id="time" name="time"
                               placeholder="<fmt:message key="web.inputs.date-deliver" bundle="${ rb }"/>"
                               class="form-control" value="${time}" required>
                    </div>
                    <div class="first__row">
                        <label for="street">
                            <fmt:message key="web.inputs.street" bundle="${ rb }"/>
                            :
                        </label>
                        <c:if test="${not empty redirect_street_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_street}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="text" class="form-control" placeholder="Улица" id="street"
                               name="street" value="<c:out value="${order.deliveryInf.street}"/>"
                               required>
                        <label for="house">
                            <fmt:message key="web.inputs.house" bundle="${ rb }"/>
                        </label>
                        <c:if test="${not empty redirect_house_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_house}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                               id="house" name="house"
                               value="<c:out value="${order.deliveryInf.house}"/>"
                               required>
                    </div>
                    <div class="sec__row">
                        <label for="room">
                            <fmt:message key="web.inputs.room" bundle="${ rb }"/>
                        </label>
                        <c:if test="${not empty redirect_room_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_room}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"
                               id="room"
                               name="room" value="<c:out value="${order.deliveryInf.room}"/>">
                        <label for="porch">
                            <fmt:message key="web.inputs.porch" bundle="${ rb }"/>
                        </label>
                        <c:if test="${not empty redirect_porch_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_porch}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"
                               id="porch"
                               name="porch" value="<c:out value="${order.deliveryInf.porch}"/>"
                               min="1" max="49">
                        <label for="floor">
                            <fmt:message key="web.inputs.floor" bundle="${ rb }"/>
                        </label>
                        <c:if test="${not empty redirect_floor_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_floor}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                               id="floor" name="floor"
                               value="<c:out value="${order.deliveryInf.floor}"/>"
                               min="-99" max="99">
                    </div>
                    <div class="phone__row">
                        <label for="tel">
                            <fmt:message key="web.inputs.phone" bundle="${ rb }"/>
                        </label>
                        <c:if test="${not empty redirect_tel_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_tel}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="tel" class="form-control"
                               placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                               id="tel" name="tel"
                               value="<c:out value="${order.deliveryInf.phone}"/>"
                               pattern="\d{9}" required>
                    </div>
                    <div class="email__row">
                        <label for="email">
                            <fmt:message key="web.inputs.email" bundle="${ rb }"/>
                        </label>
                        <c:if test="${not empty redirect_email_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_email}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <input type="email" class="form-control"
                               placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                               id="email"
                               name="email" value="<c:out value="${order.deliveryInf.email}"/>">
                    </div>
                    <div class="comments__row">
                        <label for="comments">
                            <fmt:message key="web.inputs.comments" bundle="${ rb }"/>
                        </label>
                        <c:if test="${not empty redirect_comments_error}">
                            <span class="error__message"
                                  title="<fmt:message key="web.inputs.err-value" bundle="${ rb }"/>: <c:out value="${redirect_comments}"/>">
                                <fmt:message key="web.errors.field" bundle="${ rb }"/>
                            </span>
                        </c:if>
                        <textarea class="form-control" id="comments" name="comments"
                                  placeholder="<fmt:message key="web.inputs.comments" bundle="${ rb }"/>"
                                  maxlength="200">
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

