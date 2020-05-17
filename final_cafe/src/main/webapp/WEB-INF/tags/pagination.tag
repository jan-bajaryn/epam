<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="paginationMap" required="true"
              type="java.util.Map<java.lang.Integer,by.epam.cafe.service.pagination.PaginationStatus>"
              description="Count of all elements" %>
<%@ attribute name="url" required="true"
              type="java.lang.String"
              description="Count of all elements" %>
<%@ attribute name="current" required="true"
              type="java.lang.Integer"
              description="Count of all elements" %>

<ul class="pagination justify-content-center pagination-lg">

    <c:url value="${url}" var="prev">
        <c:param name="pagination" value="${current-1}"/>
    </c:url>
    <c:url value="${url}" var="next">
        <c:param name="pagination" value="${current+1}"/>
    </c:url>
    <c:forEach var="item" items="${paginationMap}" varStatus="stat">

        <c:choose>
            <c:when test="${stat.first}">
                <c:choose>
                    <c:when test="${item.value eq 'DISABLED'}">
                        <li class="page-item disabled"><a class="page-link" href="${prev}">Previous</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${prev}">Previous</a></li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${stat.last}">
                <c:choose>
                    <c:when test="${item.value eq 'DISABLED'}">
                        <li class="page-item disabled"><a class="page-link" href="${next}">Next</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${next}">Next</a></li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <c:url value="${url}" var="cur">
                    <c:param name="pagination" value="${item.key}"/>
                </c:url>
                <c:if test="${item.value eq 'DISABLED'}">
                    <li class="page-item disabled"><a class="page-link" href="${cur}">${item.key}</a></li>
                </c:if>
                <c:if test="${item.value eq 'ACTIVE'}">
                    <li class="page-item active"><a class="page-link" href="${cur}">${item.key}</a></li>
                </c:if>
                <c:if test="${item.value eq 'NORMAL'}">
                    <li class="page-item"><a class="page-link" href="${cur}">${item.key}</a></li>
                </c:if>
                <c:if test="${item.value eq 'THREE_POINTS'}">
                    <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                </c:if>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</ul>