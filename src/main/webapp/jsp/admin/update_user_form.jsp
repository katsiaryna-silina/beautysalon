<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>

<jsp:include page="../fragment/header_admin.jsp"/>
<br/>

<div class="container">

    <h3>Update user </h3>
    <h5>user id=${user.id} username=${user.username}</h5>
    <br/>
    <hr/>

    <c:if test="${user.role=='CLIENT'}">
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="change_discount"/>
            <input type="hidden" name="user_id" value="${user.id}"/>
            <input type="hidden" name="username" value="${user.username}"/>
            <input type="hidden" name="current_discount_status_name" value="${user.discountStatus.name()}"/>

            <h5>Current user's discount: ${user.discountStatus.name()} ${user.discountStatus.getDiscount()}%</h5>

            <label>Change discount status: </label>
            <select name="new_discount_status_name">
                <c:forEach var="discount_status" items="${discount_statuses}">
                    <option value="${discount_status.name()}">
                            ${discount_status.name()} ${discount_status.getDiscount()}%
                    </option>
                </c:forEach>
            </select>
            <br/>
            <button class="btn btn-primary col-2" type="submit">Save</button>
        </form>

        <br/>

        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="change_user_role"/>
            <input type="hidden" name="user_id" value="${user.id}"/>
            <input type="hidden" name="username" value="${user.username}"/>
            <input type="hidden" name="current_role_name" value="${user.role.name()}"/>

            <h5>Current user's role: ${user.role}</h5>

            <label>Change user role: </label>
            <select name="new_role_name">
                <c:forEach var="role" items="${roles}">
                    <option>${role.name()}</option>
                </c:forEach>
            </select>
            <br/>
            <button class="btn btn-primary col-2" type="submit">Save</button>
        </form>
        <br/>
    </c:if>

    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_user_status"/>
        <input type="hidden" name="user_id" value="${user.id}"/>
        <input type="hidden" name="username" value="${user.username}"/>
        <input type="hidden" name="current_user_status_name" value="${user.userStatus.name()}"/>

        <h5>Current user's status: ${user.userStatus}</h5>

        <label>Change user status: </label>
        <select name="new_user_status_name">
            <c:forEach var="user_status" items="${user_statuses}">
                <option>${user_status.name()}</option>
            </c:forEach>
        </select>
        <br/>
        <button class="btn btn-primary col-2" type="submit">Save</button>
    </form>

    <hr/>
    <a href="${pageContext.request.contextPath}/controller?command=show_all_users">Back to user list</a>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
