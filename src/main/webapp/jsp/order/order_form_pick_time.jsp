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
    <h6>Services: ${complex_service_name}
        <c:forEach var="not_complex_service_name" items="${not_complex_service_names}">
            ${not_complex_service_name}
        </c:forEach>
    </h6>
    <h6>Date: ${date}</h6>
    <h6>Needed time for services: ${needed_time} minutes</h6>
    <h6>Full price: ${full_price}$</h6>
    <h6>Price with discount: ${price_with_discount}$ (discount=${discount_status.getDiscount()}%)</h6>
    <br/>
    <h5>Pick time you want</h5>
    <br/>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="show_new_order_data"/>
        <input type="hidden" name="date" value="${date}"/>
        <input type="hidden" name="needed_time" value="${needed_time}"/>
        <input type="hidden" name="complex_service_name" value="${complex_service_name}"/>
        <c:forEach var="not_complex_service" items="${not_complex_service_names}">
            <input class="form-check-input" type="checkbox" value="${not_complex_service}"
                   id="not_complex_service" checked hidden
                   name="not_complex_service_names">
        </c:forEach>
        <input type="hidden" name="services_ids" value="${services_ids}"/>
        <input type="hidden" name="full_price" value="${full_price}"/>
        <input type="hidden" name="price_with_discount" value="${price_with_discount}"/>

        <label>Time: </label>
        <br/>
        <select name="visit_time">
            <c:forEach var="visit_time" items="${visit_times}">
                <option value="${visit_time.getBeginTime()}_${visit_time.getEndTime()}_${visit_time.getVisitTimeIdDuration().keySet()}">
                        ${visit_time.getBeginTime()}-${visit_time.getEndTime()}
                </option>
            </c:forEach>
        </select>
        <br/>
        <br/>
        <button class="btn btn-primary col-2" type="submit">Next</button>
    </form>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
