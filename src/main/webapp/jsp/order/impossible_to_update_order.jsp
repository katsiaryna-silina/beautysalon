<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.update.order"/></title>
</head>
<body>
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <jsp:include page="../fragment/header_admin.jsp"/>
    </c:when>
    <c:when test="${role == 'CLIENT'}">
        <jsp:include page="../fragment/header_client.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="../fragment/header_default.jsp"/>
    </c:otherwise>
</c:choose>

<div class="container">
    </br>
    </br>
    <div class="row">
        <div class="col text-center">
            <h3><fmt:message key="table.header.update.order"/></h3>
            </br>
            </br>
            <a><fmt:message key="description.order.not.updated"/></a>
            <hr/>
            <c:choose>
                <c:when test="${role == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/controller?command=show_user_orders">
                        <fmt:message key="button.back.to.user.order"/>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller?command=show_all_orders_for_admin">
                        <fmt:message key="button.back.to.all.order.list"/>
                    </a>
                </c:when>
                <c:when test="${role == 'CLIENT'}">
                    <a href="${pageContext.request.contextPath}/controller?command=show_user_orders">
                        <fmt:message key="button.back.to.order.list"/>
                    </a>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>