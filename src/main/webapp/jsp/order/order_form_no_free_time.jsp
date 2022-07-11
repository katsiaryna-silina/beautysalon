<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.new.order"/></title>
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
            <h3><fmt:message key="table.new.order"/></h3>
            </br>
            </br>
            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="pick_date_in_order"/>
                <input type="hidden" name="date" value="${date}"/>
                <input type="hidden" name="complex_service_name" value="${complex_service_name}"/>
                <input type="hidden" name="not_complex_service_names" value="${not_complex_service_names}"/>

                <h5>${no_visit_times_message}</h5>
                <br/>

                <button class="btn btn-primary col-2" type="submit">
                    <fmt:message key="button.back"/>
                </button>
            </form>
            </a>
        </div>
    </div>
</div>

<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>