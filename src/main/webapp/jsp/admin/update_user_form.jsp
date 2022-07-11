<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.update.user"/></title>
</head>
<body>

<jsp:include page="../fragment/header_admin.jsp"/>

<div class="container">
    <br/>
    <br/>
    <h3><fmt:message key="table.header.update.user"/></h3>
    <h5><fmt:message key="user.update.user.id"/>${user.id}
        <fmt:message key="user.update.username"/>${user.username}</h5>
    <br/>
    <hr/>

    <c:if test="${user.role=='CLIENT'}">
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="change_discount"/>
            <input type="hidden" name="user_id" value="${user.id}"/>
            <input type="hidden" name="username" value="${user.username}"/>
            <input type="hidden" name="current_discount_status_name" value="${user.discountStatus.getStatus()}"/>

            <h5><fmt:message key="user.update.current.discount"/>
                    ${user.discountStatus.getStatus()}
                    ${user.discountStatus.getDiscount()}%</h5>

            <label><fmt:message key="user.update.change.discount"/> </label>
            <select name="new_discount_status_name">
                <c:forEach var="discount_status" items="${discount_statuses}">
                    <option value="${discount_status}">
                            ${discount_status}
                    </option>
                </c:forEach>
            </select>
            <br/>
            <button class="btn btn-primary col-2" type="submit"><fmt:message key="button.save"/></button>
        </form>

        <br/>

        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="change_user_role"/>
            <input type="hidden" name="user_id" value="${user.id}"/>
            <input type="hidden" name="username" value="${user.username}"/>
            <input type="hidden" name="current_role_name" value="${user.role.name()}"/>

            <h5><fmt:message key="user.update.current.role"/> ${user.role}</h5>

            <label><fmt:message key="user.update.change.role"/> </label>
            <select name="new_role_name">
                <c:forEach var="role" items="${roles}">
                    <option>${role.name()}</option>
                </c:forEach>
            </select>
            <br/>
            <button class="btn btn-primary col-2" type="submit"><fmt:message key="button.save"/></button>
        </form>
        <br/>
    </c:if>

    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_user_status"/>
        <input type="hidden" name="user_id" value="${user.id}"/>
        <input type="hidden" name="username" value="${user.username}"/>
        <input type="hidden" name="current_user_status_name" value="${user.userStatus.name()}"/>

        <h5><fmt:message key="user.update.current.status"/> ${user.userStatus}</h5>

        <label><fmt:message key="user.update.change.status"/> </label>
        <select name="new_user_status_name">
            <c:forEach var="user_status" items="${user_statuses}">
                <option>${user_status.name()}</option>
            </c:forEach>
        </select>
        <br/>
        <button class="btn btn-primary col-2" type="submit"><fmt:message key="button.save"/></button>
    </form>

    <hr/>
    <a href="${pageContext.request.contextPath}/controller?command=show_all_users">
        <fmt:message key="button.back.to.user.list"/>
    </a>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>