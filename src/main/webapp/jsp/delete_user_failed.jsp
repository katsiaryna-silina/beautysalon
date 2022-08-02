<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.delete.user"/></title>
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

<div class="container">
    </br>
    </br>
    <div class="row">
        <div class="col text-center">
            <h3><fmt:message key="table.header.delete.user"/></h3>
            </br>
            </br>
            <a><fmt:message key="user.delete.failed"/></a>
        </div>
    </div>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>