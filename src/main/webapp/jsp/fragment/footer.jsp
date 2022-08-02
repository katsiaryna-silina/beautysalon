<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="ctg" %>
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
        <h2><ctg:brand/></h2>
        <p><fmt:message key="description.footer"/></p>
    </div>
    <hr>
    <p>&copy; 2022 <ctg:brand/>, Inc.</p>
</footer>
</body>
</html>