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
    <h5>Pick services you want</h5>
    <br/>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="pick_date_in_order"/>

        <label>Complex services: </label>
        <br/>

        <select name="complex_service_name">
            <c:forEach var="complex_service" items="${complex_services}">
                <option value="${complex_service.getName()}">
                        ${complex_service.getName()} ${complex_service.getPrice()}$
                </option>
            </c:forEach>
        </select>
        <br/>
        <br/>
        <button class="btn btn-primary col-2" type="submit">Next</button>
    </form>

    <br/>
    <h5>or</h5>
    <br/>

    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="pick_date_in_order"/>

        <label>Not complex services: </label>

        <fieldset>
            <c:forEach var="not_complex_service" items="${not_complex_services}">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="${not_complex_service.getName()}"
                           id="not_complex_service"
                           name="not_complex_service_names">
                    <label class="form-check-label" for="not_complex_service">
                            ${not_complex_service.getName()} ${not_complex_service.getPrice()}$
                    </label>
                </div>
            </c:forEach>
            <br/>
            <button class="btn btn-primary col-2" type="submit">Next</button>
        </fieldset>
    </form>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
