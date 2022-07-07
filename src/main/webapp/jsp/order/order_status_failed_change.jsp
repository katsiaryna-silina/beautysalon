<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>2cat - update order</title>
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
<br/>
<h3 class="text-center">Update order</h3>
<br/>
<div class="container">
    <h5>Order's status with id=${order_id} has not successfully changed!</h5>

    <c:choose>
        <c:when test="${role == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/controller?command=show_all_orders_for_admin">Back to order
                list</a>
        </c:when>
        <c:when test="${role == 'CLIENT'}">
            <a href="${pageContext.request.contextPath}/controller?command=show_client_orders">Back to order
                list</a>
        </c:when>
    </c:choose>
</div>

<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
