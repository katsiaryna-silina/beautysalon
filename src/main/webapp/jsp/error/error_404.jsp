<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.404"/></title>
    <style>
        body {
            /* Image Location */
            background-image: url("${pageContext.request.contextPath}/image/404error.jpg");

            /* Background image is centered vertically and horizontally at all times */
            background-position: center center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            background-color: #464646;
            /* Font Colour */
            color: white;
        }
    </style>
</head>
<body>
</body>
</html>
