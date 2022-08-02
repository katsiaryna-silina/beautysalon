<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.update.user"/></title>
    <style>
        form {
            width: 360pt;
        }

        form > label {
            float: left;
        }

        form > .input-with-error {
            float: right;
            width: 230pt;
            margin-bottom: 11pt;
        }

        form > .input-with-error > input {
            width: 230pt;
        }
    </style>
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
<br/>
<div class="container" style="width: 415pt">
    <br/>
    <br/>
    <h3><fmt:message key="user.update.password.change"/></h3>
    </br>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_password"/>

        <label for="current_password_enter"><fmt:message key="user.enter.current.password"/></label>
        <div class="input-with-error">
            <input id="current_password_enter" name="current_password" type="password"/>
            <div class="small" style="color: red">${password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="new_password_enter"><fmt:message key="user.update.password.enter.new"/></label>
        <div class="input-with-error">
            <input id="new_password_enter" name="new_password" type="password"/>
            <div class="small" style="color: red">${new_password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="repeated_password_enter"><fmt:message key="user.update.password.confirm.new"/></label>
        <div class="input-with-error">
            <input id="repeated_password_enter" name="repeated_password" type="password"/>
        </div>
        <div style="clear: both"></div>
        <br/>
        <button class="btn btn-info btn-lg" type="submit"><fmt:message key="button.change.password"/></button>
    </form>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>