<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<jsp:useBean id="productMap" scope="request" type="java.util.Map<by.epam.cafe.entity.impl.Product,java.lang.Integer>"/>--%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <tag:imphead/>

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
        </header>
        <div class="promo">
            <form action="?">
                <div class="form-group promo-group">
                    <label for="promo"></label>
                    <input class="form-control" placeholder="Promo" type="text" id="promo">
                    <button class="btn white__bg__orange">
                        <fmt:message key="web.btn.execute" bundle="${ rb }"/>
                    </button>
                </div>
            </form>
        </div>

        <div class="product-list">
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
                                            ${String.format("%.2f",(product.key.price*product.value)/100)}
                                        <fmt:message key="web.text.rub" bundle="${ rb }"/>
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
                                            ${String.format("%.2f",(product.key.price*product.value)/100)}
                                        <fmt:message key="web.text.rub" bundle="${ rb }"/>
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
            <div class="sum-price"> ${sum}
                <fmt:message key="web.text.rub" bundle="${ rb }"/>
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
                                            <input type="text" name="name"
                                                   placeholder="<fmt:message key="web.inputs.your-name" bundle="${ rb }"/>"
                                                   class="form-control">
                                        </div>
                                        <div class="time__row">
                                            <input type="time" name="time"
                                                   placeholder="<fmt:message key="web.inputs.date-deliver" bundle="${ rb }"/>"
                                                   class="form-control">
                                        </div>
                                        <div class="first__row">
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.street" bundle="${ rb }"/>"
                                                   name="street">
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                                                   name="house">
                                        </div>
                                        <div class="sec__row">
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"

                                                   name="room">
                                            <input type="number" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"

                                                   name="porch">
                                            <input type="number" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                                                   name="floor">
                                        </div>
                                        <div class="phone__row">
                                            <input type="tel" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                                                   name="tel">
                                        </div>
                                        <div class="email__row">
                                            <input type="email" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                                                   name="email">
                                        </div>
                                        <div class="comments__row">
                                    <textarea class="form-control" name="comments"
                                              placeholder="<fmt:message key="web.inputs.comments" bundle="${ rb }"/>"></textarea>
                                        </div>
                                    </div>
                                    <button class="btn orange__bg accept__btn" type="submit">
                                        <fmt:message key="web.inputs.accept-order" bundle="${ rb }"/>
                                    </button>
                                </form>
                            </c:when>
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
                                            <input type="text" id="name" name="name"
                                                   placeholder="<fmt:message key="web.inputs.your-name" bundle="${ rb }"/>"
                                                   class="form-control" value="<c:out value="${info.name}"/>">
                                        </div>
                                        <div class="time__row">
                                            <input type="time" id="time" name="time"
                                                   placeholder="<fmt:message key="web.inputs.date-deliver" bundle="${ rb }"/>"
                                                   class="form-control">
                                        </div>
                                        <div class="first__row">
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.street" bundle="${ rb }"/>"
                                                   id="street"
                                                   name="street"
                                                   value="<c:out value="${info.street}"/>">
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.house" bundle="${ rb }"/>"
                                                   id="house" name="house" value="<c:out value="${info.house}"/>">
                                        </div>
                                        <div class="sec__row">
                                            <input type="text" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.room" bundle="${ rb }"/>"
                                                   id="room" name="room" value="<c:out value="${info.room}"/>">
                                            <input type="number" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.porch" bundle="${ rb }"/>"
                                                   id="porch" name="porch" value="<c:out value="${info.porch}"/>">
                                            <input type="number" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.floor" bundle="${ rb }"/>"
                                                   id="floor" name="floor" value="<c:out value="${info.floor}"/>">
                                        </div>
                                        <div class="phone__row">
                                            <input type="tel" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.phone" bundle="${ rb }"/>"
                                                   id="tel" name="tel" value="<c:out value="${info.phone}"/>">
                                        </div>
                                        <div class="email__row">
                                            <input type="email" class="form-control"
                                                   placeholder="<fmt:message key="web.inputs.email" bundle="${ rb }"/>"
                                                   id="email" name="email" value="<c:out value="${info.email}"/>">
                                        </div>
                                        <div class="comments__row">
                                    <textarea class="form-control" id="comments" name="comments"
                                              placeholder="<fmt:message key="web.inputs.comments" bundle="${ rb }"/>"></textarea>
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