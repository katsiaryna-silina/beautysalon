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
            <h3><fmt:message key="services.desc"/></h3>
            </br>
            <h5><fmt:message key="services.combing"/></h5>
            <p><fmt:message key="services.combing.description"/></p>
            <h5><fmt:message key="services.nail.clipping"/></h5>
            <p><fmt:message key="services.nail.clipping.description"/></p>
            <h5><fmt:message key="services.ear.cleaning"/></h5>
            <p><fmt:message key="services.ear.cleaning.description"/></p>
            <h5><fmt:message key="services.bathing"/></h5>
            <p><fmt:message key="services.bathing.description"/></p>
            <h5><fmt:message key="services.drying"/></h5>
            <p><fmt:message key="services.drying.description"/></p>
            <h5><fmt:message key="services.haircut"/></h5>
            <p><fmt:message key="services.haircut.description"/></p>
            <h5><fmt:message key="services.molting"/></h5>
            <p><fmt:message key="services.molting.description"/></p>
            <h5><fmt:message key="services.all.complex"/></h5>
            <p><fmt:message key="services.all.description"/></p>
        </div>
    </div>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>