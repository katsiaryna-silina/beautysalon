<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update order</title>
</head>
<body>

<jsp:include page="../fragment/header_admin.jsp"/>
<br/>

<div class="container">

    <h3>Update order</h3>
    <br/>
    <h6>Order id=${order.getId()}</h6>
    <h6>Visit day: ${order.getVisitDate()} time: ${order.getVisitBeginTime()}-${order.getVisitEndTime()}</h6>
    <h6>Services:</h6>
    <c:forEach var="service_name" items="${order.getServiceNames()}">
        <a>${service_name}; </a>
    </c:forEach>
    <br/>
    <br/>
    <h6>Price with discount: ${order.getPriceWithDiscount()}</h6>
    <br/>
    <h6>User info:</h6>
    <a>username: ${order.getUsername()}, First name: ${order.getFirstName()}, Last name: ${order.getLastName()}</a>
    <br/>
    <a>email: ${order.getEmail()}, phone number: ${order.getPhoneNumber()}</a>

    <br/>
    <hr/>

    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_order_status"/>
        <input type="hidden" name="order_id" value="${order.getId()}"/>
        <input type="hidden" name="current_order_status_name" value="${order.getStatus()}"/>

        <h6>Order status: ${order.getStatus()}</h6>
        <h6>Order status description: ${order.getDescription()}</h6>
        <label class="text-danger">Change order status: </label>
        <select name="new_order_status_name">
            <c:forEach var="order_status" items="${order_statuses}">
                <option>${order_status}</option>
            </c:forEach>
        </select>
        <br/>
        <button class="btn btn-primary col-2" type="submit">Save</button>
    </form>

    <hr/>
    <a href="${pageContext.request.contextPath}/controller?command=show_all_orders_for_admin">Back to order
        list</a>
</div>

<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
