<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
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
            <li class="ui-corner-right nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/jsp/login.jsp">
                    <fmt:message key="header.login"/>
                </a>
            </li>
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

<script src=" https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>