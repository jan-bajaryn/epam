<%@ attribute name="err" required="false" type="java.lang.Boolean" description="Is error required" %>
<%@ attribute name="btns" required="false" type="java.lang.Boolean" description="Is import of buttons css required" %>
<%@ attribute name="navbar" required="false" type="java.lang.Boolean" description="Is import of buttons css required" %>
<%@ attribute name="footer" required="false" type="java.lang.Boolean" description="Is import of buttons css required" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta charset="UTF-8">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
      integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
      crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
      integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
      crossorigin="anonymous">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

<c:if test="${err!=null && err}">
    <link rel="stylesheet" href="<c:url value='/static/css/errors.css' />">
</c:if>
<c:if test="${btns!=null && btns}">
    <link rel="stylesheet" href="<c:url value='/static/css/buttons.css' />">
</c:if>
<c:if test="${navbar!=null && navbar}">
    <link rel="stylesheet" href="<c:url value='/static/css/nav__bar.css' />">
</c:if>
<c:if test="${footer!=null && footer}">
    <link rel="stylesheet" href="<c:url value='/static/css/footer.css' />">
</c:if>


