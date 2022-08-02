<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title></title>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand -->
    <a class="nav-link navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
        <img alt="2cat"
             src="${pageContext.request.contextPath}/image/2cat.jpg"/>
    </a>

    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <!-- Navbar links -->
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <fmt:message key="header.services"/>
                </a>
            </li>
        </ul>
    </div>
    <div class="mx-auto order-0">
        <!-- Toggler/collapsibe Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <form action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="login"/>
                <input type="text" id="login" class="fadeIn second form-control-sm" name="username"
                       placeholder="username"
                       value="">
                <input type="password" id="password" class="fadeIn third form-control-sm" name="password"
                       placeholder="password"
                       value="">
                <button class="adeIn fourth btn btn-outline-primary btn-sm" type="submit" value="LogIn">
                    <fmt:message key="header.login"/>
                </button>
                <p class=" small text-danger">${login_failed_message}</p>
            </form>
            <li class="ui-corner-right nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/jsp/registration.jsp">
                    <fmt:message key="header.register"/>
                </a>
            </li>
            <li class="ui-corner-right nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">
                    <fmt:message key="header.ru"/>
                </a>
            </li>
            <li class="ui-corner-right nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_US">
                    <fmt:message key="header.en"/>
                </a>
            </li>
        </ul>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>