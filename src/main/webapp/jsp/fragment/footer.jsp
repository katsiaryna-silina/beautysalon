<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<footer class="bg-light footer text-center fixed-bottom">
    <div class="container  p-6">
        <h2> 2cat </h2>
        <p><fmt:message key="description.footer"/></p>
    </div>
    <hr>
    <p>&copy; 2022 2cat, Inc.</p>
</footer>
</body>
</html>