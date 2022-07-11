<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.delete.registration"/></title>
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

<div class="container" style="width: 415pt">
    <br/>
    <br/>
    <h1><fmt:message key="table.header.registration"/></h1>
    </br>
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="registration"/>

        <label for="username_enter"><fmt:message key="user.enter.username"/></label>
        <div class="input-with-error">
            <input id="username_enter" name="username" type="text"
                   value="${pageContext.request.getParameter("username")}"/>
            <div class="small" style="color: red">${username_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="password_enter"><fmt:message key="user.enter.password"/></label>
        <div class="input-with-error">
            <input id="password_enter" name="password" type="password"/>
            <div class="small" style="color: red">${password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="repeated_password_enter"><fmt:message key="user.enter.password.confirm"/></label>
        <div class="input-with-error">
            <input id="repeated_password_enter" name="repeated_password" type="password"/>
            <div class="small" style="color: red">${password_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="email_enter"><fmt:message key="user.enter.email"/></label>
        <div class="input-with-error">
            <input id="email_enter" name="email" type="text"
                   value="${pageContext.request.getParameter("email")}">
            <div class="small" style="color: red">${email_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="first_name_enter"><fmt:message key="user.enter.first.name"/></label>
        <div class="input-with-error">
            <input id="first_name_enter" name="first_name" type="text"
                   value="${pageContext.request.getParameter("first_name")}">
            <div class="small" style="color: red">${first_name_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="last_name_enter"><fmt:message key="user.enter.last.name"/></label>
        <div class="input-with-error">
            <input id="last_name_enter" name="last_name" type="text"
                   value="${pageContext.request.getParameter("last_name")}">
            <div class="small" style="color: red">${last_name_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <label for="phone_number_enter"><fmt:message key="user.enter.phone.number"/></label>
        <div class="input-with-error">
            <input id="phone_number_enter" name="phone_number" type="text"
                   value="${pageContext.request.getParameter("phone_number")}">
            <div class="small" style="color: red">${phone_number_error_message}</div>
        </div>
        <div style="clear: both"></div>

        <button class="btn btn-info btn-lg" type="submit">
            <fmt:message key="button.sing.up"/>
        </button>
        <div class="small" style="color: red">${create_new_user_error_message}</div>
    </form>
</div>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>