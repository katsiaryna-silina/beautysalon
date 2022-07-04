<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="locale.pagecontent"/>
<fmt:message key="lable.title.main" var="main_page"/>
<html>
<head>
    <title>${main_page}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
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

Hi ${username}

<jsp:include page="fragment/footer.jsp"/>

</body>
</html>
