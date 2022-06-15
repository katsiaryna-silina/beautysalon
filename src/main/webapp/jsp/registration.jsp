<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2cat - registration</title>
    <style>
        form {
            width: 345pt;
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
<jsp:include page="fragment/header_default.jsp"/>
<br/>
<div class="container" style="width: 415pt">

    <h1>Registration form</h1>
    </br>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="registration"/>

        <label for="username_enter">Enter username:</label>
        <div class="input-with-error">
            <input id="username_enter" name="username" type="text"
                   value="${pageContext.request.getParameter("username")}"/>
            <div class="small" style="color: red">${username_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="password_enter">Enter password:</label>
        <div class="input-with-error">
            <input id="password_enter" name="password" type="password"/>
            <div class="small" style="color: red">${password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="repeated_password_enter">Confirm password:</label>
        <div class="input-with-error">
            <input id="repeated_password_enter" name="repeated_password" type="password"/>
            <div class="small" style="color: red">${password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="email_enter">Enter email:</label>
        <div class="input-with-error">
            <input id="email_enter" name="email" type="text"
                   value="${pageContext.request.getParameter("email")}">
            <div class="small" style="color: red">${email_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="first_name_enter">Enter first name:</label>
        <div class="input-with-error">
            <input id="first_name_enter" name="first_name" type="text"
                   value="${pageContext.request.getParameter("first_name")}">
            <div class="small" style="color: red">${first_name_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="last_name_enter">Enter last name:</label>
        <div class="input-with-error">
            <input id="last_name_enter" name="last_name" type="text"
                   value="${pageContext.request.getParameter("last_name")}">
            <div class="small" style="color: red">${last_name_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="phone_number_enter">Enter phone number:</label>
        <div class="input-with-error">
            <input id="phone_number_enter" name="phone_number" type="text"
                   value="${pageContext.request.getParameter("phone_number")}">
            <div class="small" style="color: red">${phone_number_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <input class="btn btn-info btn-lg" type="submit" value="Sing Up!">
        <div class="small" style="color: red">${create_new_user_error_message}</div>
    </form>
</div>
<jsp:include page="fragment/footer.jsp"/>
</body>
</html>
