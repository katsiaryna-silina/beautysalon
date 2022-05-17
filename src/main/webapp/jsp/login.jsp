<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login"/>
    Username:<input type="text" name="username" value=""/>
    </br>
    Password:<input type="text" name="password" value=""/>
    </br>
    <input type="submit" name="sub" value="Push"/>
</form>
<p class="text-danger">${login_failed_message}</p>

</body>
</html>
