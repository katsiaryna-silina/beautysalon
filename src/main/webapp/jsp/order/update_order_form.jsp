<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.update.order"/></title>
</head>
<body>

<jsp:include page="../fragment/header_client.jsp"/>

<div class="container">
    <br/>
    <br/>
    <h3><fmt:message key="table.header.update.order"/></h3>
    <br/>
    <h6><fmt:message key="order.data.order.id"/>${order.getId()}</h6>
    <h6><fmt:message key="order.data.visit.day"/> ${order.getVisitDate()}
        <fmt:message key="order.data.time"/> ${order.getVisitBeginTime()}-${order.getVisitEndTime()}
    </h6>
    <h6><fmt:message key="order.data.services"/></h6>
    <c:forEach var="service_name" items="${order.getServiceNames()}">
        <a>${service_name} </a>
    </c:forEach>
    <br/>
    <br/>
    <h6><fmt:message key="order.data.price.with.discount"/> ${order.getPriceWithDiscount()}</h6>
    <br/>
    <hr/>

    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_order_status"/>
        <input type="hidden" name="order_id" value="${order.getId()}"/>
        <input type="hidden" name="current_order_status_name" value="${order.getStatus()}"/>

        <h6><fmt:message key="order.data.order.status"/> ${order.getStatus()}</h6>
        <h6><fmt:message key="order.data.order.status.description"/> ${order.getDescription()}</h6>
        <label class="text-danger"><fmt:message key="order.change.order.status"/> </label>
        <select name="new_order_status_name">
            <c:forEach var="order_status" items="${order_statuses}">
                <option>${order_status}</option>
            </c:forEach>
        </select>
        <br/>
        <button class="btn btn-primary col-2" type="submit"><fmt:message key="button.save"/></button>
    </form>

    <hr/>
    <a href="${pageContext.request.contextPath}/controller?command=show_user_orders">
        <fmt:message key="button.back.to.order.list"/>
    </a>
</div>

<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>