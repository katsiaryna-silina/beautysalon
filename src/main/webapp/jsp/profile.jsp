<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.profile"/></title>
</head>
<body>
<c:if test="${role == 'ADMIN'}">
    <jsp:include page="fragment/header_admin.jsp"/>
</c:if>
<c:if test="${role == 'CLIENT'}">
    <jsp:include page="fragment/header_client.jsp"/>
</c:if>

<div class="container">
    <br/>
    <br/>
    <h3><fmt:message key="user.info"/></h3>
    <br/>
    <br/>
    <p><fmt:message key="user.discount.status"/> ${discount_status.getStatus()}</p>
    <p><fmt:message key="user.discount"/> ${discount_status.getDiscount()}%</p>
    <br/>
    <p><fmt:message key="user.first.name"/> ${first_name}</p>
    <p><fmt:message key="user.last.name"/> ${last_name}</p>
    <br/>
    <p><fmt:message key="user.username"/> ${username}</p>
    <p><fmt:message key="user.email"/> ${email}</p>
    <p><fmt:message key="user.phone.number"/>Phone number: ${phone_number}</p>

    <a class="btn btn-info" href="change_password.jsp"><fmt:message key="button.change.password"/></a>
    <a class="btn btn-danger ml-2" href="delete_user.jsp"><fmt:message key="button.delete.account"/></a>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>