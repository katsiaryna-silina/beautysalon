<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>2cat - new order</title>
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
<h3 class="text-center">New order</h3>
<br/>
<div class="container">
    <h5>Sad message</h5>
    <br/>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="pick_date_in_order"/>
        <input type="hidden" name="date" value="${date}"/>
        <input type="hidden" name="complex_service_name" value="${complex_service_name}"/>
        <input type="hidden" name="not_complex_service_names" value="${not_complex_service_names}"/>

        <h5>${no_visit_times_message}</h5>
        <br/>

        <button class="btn btn-primary col-2" type="submit">Back</button>
    </form>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
