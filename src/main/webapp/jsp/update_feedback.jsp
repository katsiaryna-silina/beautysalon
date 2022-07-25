<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.update.feedback"/></title>
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

<div class="container">
    </br>
    </br>
    <div class="row">
        <div class="col text-center">
            <h3><fmt:message key="table.header.update.feedback"/></h3>
            </br>
            </br>
            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="update_feedback"/>
                <input type="hidden" name="id" value="${id}"/>

                <label for="feedback_enter"><fmt:message key="feedback.enter"/></label>
                <div class="input-with-error">
            <textarea class="form-control" id="feedback_enter" name="new_feedback" rows="3">
                ${current_feedback}</textarea>
                    <div class=" small" style="color: red">${feedback_error_message}</div>
                </div>
                <div style="clear: both"></div>

                </br>

                <label for="new_mark_enter"><fmt:message key="feedback.mark"/></label>
                <div class="input-with-error">
                    <input id="new_mark_enter" name="new_mark" type="number" max="5" min="1" step="1"
                           value="${current_mark}" required/>
                    <div class="small" style="color: red">${mark_error_message}</div>
                </div>
                <div style="clear: both"></div>
                </br>
                <input class="btn btn-info btn-lg" type="submit" value="Save">
            </form>

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