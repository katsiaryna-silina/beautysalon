<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>2cat - welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="../fragment/header_admin.jsp"/>

<br/>

<div class="container">
    <h3>Update user </h3>
    <h5>user id=${user_id} username=${username}</h5>
    <br/>
    <hr/>

    <h5 class="text-center">${change_user_message}</h5>

    <hr/>
    <a href="${pageContext.request.contextPath}/controller?command=update_user&username=${username}">Back to user
        update</a>
    <br/>

    <a href="${pageContext.request.contextPath}/controller?command=show_all_users">Back to user list</a>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
