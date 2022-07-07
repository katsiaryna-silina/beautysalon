<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>2cat - update feedback</title>
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
<div class="container">

    <h1>Update feedback</h1>
    </br>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="update_feedback"/>
        <input type="hidden" name="id" value="${id}"/>

        <label for="feedback_enter">Enter feedback:</label>
        <div class="input-with-error">
            <textarea class="form-control" id="feedback_enter" name="new_feedback" rows="3">
                ${current_feedback}</textarea>

            <%--<input id="feedback_enter" name="new_feedback" type="text"
                   value="${pageContext.request.getParameter("current_feedback")}">--%>
            <div class=" small" style="color: red">${feedback_error_message}</div>
        </div>
        <div style="clear: both"></div>

        </br>

        <label for="new_mark_enter">Your mark (from 1 to 5):</label>
        <div class="input-with-error">
            <input id="new_mark_enter" name="new_mark" type="number" max="5" min="1" step="1"
                   value="${current_mark}" required/>
            <div class="small" style="color: red">${mark_error_message}</div>
        </div>
        <div style="clear: both"></div>
        </br>
        <input class="btn btn-info btn-lg" type="submit" value="Save">
    </form>
</div>
<hr/>
<a href="${pageContext.request.contextPath}/controller?command=show_client_orders">Back to orders
    list</a>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>
