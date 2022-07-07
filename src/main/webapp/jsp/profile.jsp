<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <jsp:include page="fragment/header_admin.jsp"/>
    </c:when>
    <c:when test="${role == 'CLIENT'}">
        <jsp:include page="fragment/header_client.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="fragment/header_default.jsp"/>
    </c:otherwise>
</c:choose>

<c:if test="${role.equals(Role.ADMIN)}">
    <jsp:include page="fragment/header_admin.jsp"/>
</c:if>
<c:if test="${role.equals(Role.CLIENT)}">
    <jsp:include page="fragment/header_client.jsp"/>
</c:if>
<br/>
<br/>

<div class="container">

    <h3>User Info</h3>

    <br/><br/>

    <p>Discount status: ${discount_status.getStatus()}</p>
    <p>Discount : ${discount_status.getDiscount()}%</p>

    <br/>

    <p>First name: ${first_name}</p>
    <p>Last name: ${last_name}</p>

    <br/>

    <p>Username: ${username}</p>
    <p>Email: ${email}</p>
    <p>Phone number: ${phone_number}</p>


    <a class="btn btn-info" href="#">Change user info</a>
    <a class="btn btn-info" href="change_password.jsp">Change password</a>
    <a class="btn btn-danger ml-2" href="#">Delete account</a>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>
