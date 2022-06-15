<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2cat - registration</title>
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
<%--
//todo change header by role
--%>
<jsp:include page="fragment/header_default.jsp"/>
<br/>
<div class="container" style="width: 415pt">

    <h1>Password change</h1>
    </br>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_password"/>

        <label for="current_password_enter">Enter current password:</label>
        <div class="input-with-error">
            <input id="current_password_enter" name="current_password" type="password"/>
            <div class="small" style="color: red">${password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="new_password_enter">Enter new password:</label>
        <div class="input-with-error">
            <input id="new_password_enter" name="new_password" type="password"/>
            <div class="small" style="color: red">${new_password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="repeated_password_enter">Confirm new password:</label>
        <div class="input-with-error">
            <input id="repeated_password_enter" name="repeated_password" type="password"/>
        </div>
        <div style="clear: both"></div>

        <input class="btn btn-info btn-lg" type="submit" value="Change password!">
    </form>
</div>
<jsp:include page="fragment/footer.jsp"/>
</body>
</html>
