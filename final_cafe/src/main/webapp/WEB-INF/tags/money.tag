<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="input" required="true"
              type="java.lang.Integer"
              description="Integer to parse to money value" %>
<fmt:setBundle basename="property.text" var="rb"/>
<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${input/100}"/> <fmt:message key="web.text.rub" bundle="${ rb }"/>