<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>

<body>
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <jsp:include page="jsp/fragment/header_admin_with_locale.jsp"/>
    </c:when>
    <c:when test="${role == 'CLIENT'}">
        <jsp:include page="jsp/fragment/header_client_with_locale.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="jsp/fragment/header_default.jsp"/>
    </c:otherwise>
</c:choose>
<br/>
<h3 class="text-center"><fmt:message key="description.salon.message"/></h3>

<jsp:include page="jsp/fragment/footer.jsp"/>
</body>
</html>