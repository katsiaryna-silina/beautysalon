<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>
<body>
<jsp:include page="fragment/header_default.jsp"/>

<div class="container">
    </br>
    </br>
    <div class="row">
        <div class="col text-center">
            </br>
            </br>
            <a><fmt:message key="hi"/>, ${username}! <fmt:message key="description.user.registered"/></a>
            <a><fmt:message key="description.login"/></a>
            <hr/>
            <a href="${pageContext.request.contextPath}/controller?command=show_user_orders">
                <fmt:message key="button.back.to.order.list"/>
            </a>
        </div>
    </div>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>