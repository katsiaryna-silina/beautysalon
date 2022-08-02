<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>
<body>
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <jsp:include page="fragment/header_admin_with_locale.jsp"/>
    </c:when>
    <c:when test="${role == 'CLIENT'}">
        <jsp:include page="fragment/header_client_with_locale.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="fragment/header_default.jsp"/>
    </c:otherwise>
</c:choose>

<div class="container">
    </br>
    </br>
    <div class="row">
        <div class="col text-center">
            <h4><fmt:message key="hi"/>${username}!</h4>
        </div>
    </div>
    </br>
    <div class="col text-center">
        <img style="width: 70%" src="../image/welcome.jpg" alt="welcome">
    </div>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>