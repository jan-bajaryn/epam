<%--@elvariable id="sum" type="java.lang.Integer"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<jsp:useBean id="productMap" scope="request" type="java.util.Map<Product,java.lang.Integer>"/>--%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>


<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <tag:imphead err="${true}"/>

    <link rel="stylesheet" href="<c:url value='/static/css/order/main.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/order/modals.css' />">
</head>
<body>

<fmt:setBundle basename="property.text" var="rb"/>

<main>
    <div class="main-container">
        <header class="mt-4">
            <h1>
                <fmt:message key="web.links.basket" bundle="${ rb }"/>
            </h1>
            <c:if test="${not empty redirect_unknown_error}">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <fmt:message key="web.errors.unknown-error" bundle="${ rb }"/>
                </div>
            </c:if>
            <c:if test="${not empty redirect_basket_error}">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <fmt:message key="web.errors.no-products" bundle="${ rb }"/>
                </div>
            </c:if>
        </header>


        <div class="product-list">
            <%--@elvariable id="productMap" type="java.util.Map<by.epam.cafe.entity.db.impl.Product,java.lang.Integer>"--%>
            <c:if test="${productMap!=null}">
                <c:forEach var="product" items="${productMap}">

                    <div class="product-item">
                        <div class="grid-part">
                            <div class="image-part">
                                <img src="<c:url value="/static/img/${product.key.productGroup.photoName}"/>" alt="">
                            </div>
                            <div class="product-name">
                                <c:out value="${product.key.productGroup.name}"/>
                            </div>
                            <div class="product-type text-muted">
                                    ${product.key.weight} <fmt:message key="web.gram" bundle="${ rb }"/>.
                            </div>
                        </div>
                        <div class="flex-part">
                            <c:choose>
                                <%--@elvariable id="role" type="by.epam.cafe.entity.enums.Role"--%>
                                <c:when test="${role eq 'ANON'}">
                                    <form action="<c:url value="/page/anon/minus-item"/>" method="post">
                                        <input type="hidden" value="${product.key.id}" name="variant">
                                        <button type="submit" class="btn mx-3 white__bg__black minus"> -</button>
                                    </form>
                                    <span><c:out value="${product.value}"/></span>
                                    <form action='<c:url value="/page/anon/put-item"/>' method="post">
                                        <input type="hidden" value="${product.key.id}" name="variant">
                                        <button type="submit" class="btn mx-3 white__bg__black plus"> +</button>
                                    </form>
                                    <div class="prise mr-3">
                                        <tag:money input="${product.key.price*product.value}"/>
                                    </div>
                                    <form action="<c:url value="/page/anon/delete-all"/>" method="post">
                                        <button class="abc" type="submit">
                                            <i class="fa fa-trash mr-3" aria-hidden="true"></i>
                                        </button>
                                        <label>
                                            <input name="id" type="number" value="${product.key.id}"
                                                   style="display: none;">
                                        </label>
                                    </form>
                                </c:when>
                                <c:when test="${role eq 'CLIENT'}">
                                    <form action="<c:url value="/page/client/minus-item"/>" method="post">
                                        <input type="hidden" value="${product.key.id}" name="variant">
                                        <button type="submit" class="btn mx-3 white__bg__black minus"> -</button>
                                    </form>
                                    <span><c:out value="${product.value}"/></span>
                                    <form action='<c:url value="/page/client/put-item"/>' method="post">
                                        <input type="hidden" value="${product.key.id}" name="variant">
                                        <button type="submit" class="btn mx-3 white__bg__black plus"> +</button>
                                    </form>
                                    <div class="prise mr-3">
                                        <tag:money input="${product.key.price*product.value}"/>
                                    </div>
                                    <form action="<c:url value="/page/client/delete-all"/>" method="post">
                                        <button class="abc" type="submit">
                                            <i class="fa fa-trash mr-3" aria-hidden="true"></i>
                                        </button>
                                        <label>
                                            <input name="id" type="number" value="${product.key.id}"
                                                   style="display: none;">
                                        </label>
                                    </form>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <div class="sum">
            <div class="sum-text">
                <fmt:message key="web.text.sum-order" bundle="${ rb }"/>
            </div>
            <div class="sum-price"><tag:money input="${sum}"/>
<%--                <fmt:message key="web.text.rub" bundle="${ rb }"/>--%>
            </div>
        </div>

        <div class="decision mb-5 mt-2">
            <a href="<c:url value="/page/"/>">
                <button class="btn orange__hover">
                    <fmt:message key="web.btn.return-menu" bundle="${ rb }"/>
                </button>
            </a>

            <button class="btn orange__bg myBtn">
                <fmt:message key="web.btn.make-order" bundle="${ rb }"/>
            </button>
            <div class="my__modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div class="modal__main__content">
                        <h3>
                            <fmt:message key="web.text.where-delivery" bundle="${ rb }"/>
                        </h3>

                        <c:choose>
                            <c:when test="${role eq 'ANON'}">
                                <form action="<c:url value="/page/anon/make-order"/>" method="post">
                                    <div class="body__form">
                                        <label for="street"></label>
                                        <label for="comments"></label>
                                        <label for="floor"></label>
                                        <label for="porch"></label>
                                        <label for="room"></label>
                                        <label for="house"></label>
                                        <label for="name"></label>
                                        <label for="tel"></label>
                                        <label for="email"></label>
                                        <label for="time"></label>


                                        <div class="name__row">
                                                <%--@elvariable id="redirect_name_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_name" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_name_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="text" name="name" id="name"
                                                   placeholder="<fmt:message key="web.inputs.your-name" bundle="${ rb }"/>"
                                                   class="form-control"
                                                   value="<c:out value="${redirect_name}"/>"
                                                   required
                                                   pattern="^[A-Za-zА-Яа-яЁё]+(([',. \\-][A-Za-zА-Яа-яЁё ])?[A-Za-zА-Яа-яЁё]*)*$">
                                        </div>
                                        <div class="time__row">
                                                <%--@elvariable id="redirect_time_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_time" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_time_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="time" name="time" id="time"
                                                   placeholder="<fmt:message key="web.inputs.date-deliver" bundle="${ rb }"/>"
                                                   class="form-control" value="<c:out value="${redirect_time}"/>"
                                                   required>
                                        </div>
                                        <div class="first__row">
                                                <%--@elvariable id="redirect_street_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_street" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_street_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="text" class="form-control" id="street"
                                                   placeholder="<fmt:message key="web.inputs.street" bundle="${ rb }"/>"
                                                   name="street" value="<c:out value="${redirect_street}"/>"
                                                   required>
                                                <%--@elvariable id="redirect_house_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_house" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_house_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="text" class="form-control" id="house"
                                                   placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                                                   name="house" value="<c:out value="${redirect_house}"/>"
                                                   required>
                                        </div>
                                        <div class="sec__row">
                                                <%--@elvariable id="redirect_room_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_room" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_room_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="text" class="form-control" id="room"
                                                   placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"
                                                   name="room" value="<c:out value="${redirect_room}"/>">
                                                <%--@elvariable id="redirect_porch_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_porch" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_porch_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>

                                            <input type="number" class="form-control" id="porch"
                                                   placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"
                                                   name="porch" value="<c:out value="${redirect_porch}"/>"
                                                   min="1" max="49">
                                                <%--@elvariable id="redirect_floor_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_floor" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_floor_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="number" class="form-control" id="floor"
                                                   placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                                                   name="floor" value="<c:out value="${redirect_floor}"/>"
                                                   min="-99" max="99">
                                        </div>
                                        <div class="phone__row">
                                                <%--@elvariable id="redirect_tel_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_tel" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_tel_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="tel" class="form-control" id="tel"
                                                   placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                                                   name="tel" value="<c:out value="${redirect_tel}"/>"
                                                   pattern="\d{9}" required>
                                        </div>
                                        <div class="email__row">
                                                <%--@elvariable id="redirect_email_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_email" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_email_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="email" class="form-control" id="email"
                                                   placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                                                   name="email" value="<c:out value="${redirect_email}"/>" required>
                                        </div>
                                        <div class="payment_type_row">
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
                                                <c:forEach var="type" items="${types}">
                                                    <option>${type}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="comments__row">
                                                <%--@elvariable id="redirect_comments_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_comments" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_comments_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <textarea class="form-control" name="comments" id="comments"
                                                      placeholder="<fmt:message key="web.inputs.comments" bundle="${ rb }"/>"
                                                      maxlength="200">
                                                <c:out value="${redirect_comments}"/>
                                            </textarea>
                                        </div>
                                    </div>
                                    <button class="btn orange__bg accept__btn" type="submit">
                                        <fmt:message key="web.inputs.accept-order" bundle="${ rb }"/>
                                    </button>
                                </form>
                            </c:when>
                            <%--@elvariable id="role" type="by.epam.cafe.entity.enums.Role"--%>
                            <c:when test="${role eq 'CLIENT'}">

                                <jsp:useBean id="info" scope="request" type="by.epam.cafe.controller.dto.UserDTO"/>

                                <form action="<c:url value="/page/client/make-order"/>" method="post">
                                    <div class="body__form">
                                        <label for="street"></label>
                                        <label for="comments"></label>
                                        <label for="floor"></label>
                                        <label for="porch"></label>
                                        <label for="room"></label>
                                        <label for="house"></label>
                                        <label for="name"></label>
                                        <label for="tel"></label>
                                        <label for="email"></label>
                                        <label for="time"></label>


                                        <div class="name__row">
                                                <%--@elvariable id="redirect_name_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_name" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_name_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="text" id="name" name="name"
                                                   placeholder="<fmt:message key="web.inputs.your-name" bundle="${ rb }"/>"
                                                   class="form-control" value="<c:out value="${info.name}"/>"
                                                   title="<c:out value="${redirect_name}"/>"
                                                   required
                                                   pattern="^[A-Za-zА-Яа-яЁё]+(([',. \\-][A-Za-zА-Яа-яЁё ])?[A-Za-zА-Яа-яЁё]*)*$">
                                        </div>
                                        <div class="time__row">
                                                <%--@elvariable id="redirect_time_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_time" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_time_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="time" id="time" name="time"
                                                   placeholder="<fmt:message key="web.inputs.date-deliver" bundle="${ rb }"/>"
                                                   class="form-control" value="<c:out value="${redirect_time}"/>"
                                                   required>
                                        </div>
                                        <div class="first__row">
                                                <%--@elvariable id="redirect_street_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_street" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_street_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.street" bundle="${ rb }"/>"
                                                   id="street"
                                                   name="street" title="<c:out value="${redirect_street}"/>"
                                                   value="<c:out value="${info.street}"/>"
                                                   required>
                                                <%--@elvariable id="redirect_house_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_house" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_house_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                                                   id="house" name="house" value="<c:out value="${info.house}"/>"
                                                   title="<c:out value="${redirect_house}"/>"
                                                   required>
                                        </div>
                                        <div class="sec__row">
                                                <%--@elvariable id="redirect_room_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_room" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_room_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"
                                                   id="room" name="room" value="<c:out value="${info.room}"/>"
                                                   title="<c:out value="${redirect_room}"/>">
                                                <%--@elvariable id="redirect_porch_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_porch" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_porch_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="number" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"
                                                   id="porch" name="porch" value="<c:out value="${info.porch}"/>"
                                                   title="<c:out value="${redirect_porch}"/>"
                                                   min="1" max="49">

                                                <%--@elvariable id="redirect_floor_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_floor" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_floor_error}">
                                                <span class="error__message hid" hidden></span>
                                            </c:if>
                                            <input type="number" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                                                   id="floor" name="floor" value="<c:out value="${info.floor}"/>"
                                                   title="<c:out value="${redirect_floor}"/>"
                                                   min="-99" max="99">
                                        </div>
                                        <div class="phone__row">
                                                <%--@elvariable id="redirect_tel_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_tel" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_tel_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="tel" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                                                   id="tel" name="tel" value="<c:out value="${info.phone}"/>"
                                                   title="<c:out value="${redirect_tel}"/>"
                                                   pattern="\d{9}" required>
                                        </div>

                                        <div class="email__row">
                                                <%--@elvariable id="redirect_email_error" type="java.lang.String"--%>
                                                <%--@elvariable id="redirect_email" type="java.lang.String"--%>
                                            <c:if test="${not empty redirect_email_error}">
                                                <span class="error__message">
                                                    <fmt:message key="web.errors.field" bundle="${ rb }"/>
                                                </span>
                                            </c:if>
                                            <input type="email" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                                                   id="email" name="email" value="<c:out value="${info.email}"/>"
                                                   title="<c:out value="${redirect_email}"/>" required>
                                        </div>
                                        <div class="payment_type_row">
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
                                                <c:forEach var="type" items="${types}">
                                                    <option>${type}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="comments__row">
                                    <textarea class="form-control" name="comments"
                                              placeholder="<fmt:message key="web.inputs.comments" bundle="${ rb }"/>"
                                              maxlength="200">
                                        <%--@elvariable id="redirect_comments" type="java.lang.String"--%>
                                        <c:out value="${redirect_comments}"/>
                                    </textarea>
                                        </div>
                                    </div>
                                    <button class="btn orange__bg accept__btn" type="submit">
                                        <fmt:message key="web.inputs.accept-order" bundle="${ rb }"/>
                                    </button>
                                </form>
                            </c:when>
                        </c:choose>


                    </div>
                </div>
            </div>
        </div>

    </div>
</main>

<c:import url="fragments/footer.jsp"/>


<tag:impfoot/>


<script src="<c:url value="/static/js/order/modals.js"/>"></script>


</body>
</html>