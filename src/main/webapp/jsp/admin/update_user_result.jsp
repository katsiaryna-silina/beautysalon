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
    <h5><fmt:message key="user.update.user.id"/>${user_id}
        <fmt:message key="user.update.username"/>${username}</h5>
    <br/>
    <hr/>

    <h5 class="text-center">${change_user_message}</h5>

    <hr/>
    <a href="${pageContext.request.contextPath}/controller?command=update_user&username=${username}">
        <fmt:message key="button.back.to.user.update"/>
    </a>
    <br/>

    <a href="${pageContext.request.contextPath}/controller?command=show_all_users">
        <fmt:message key="button.back.to.user.list"/>
    </a>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>