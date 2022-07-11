<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.500"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<br>
<div class="container">
    </br>
    </br>
    <h3><fmt:message key="table.header.500"/></h3>
    </br>
    </br>
    Request from: ${pageContext.errorData.requestURI} is failed
    </br>
    Servlet name : ${pageContext.errorData.servletName}
    </br>
    Status code: ${pageContext.errorData.statusCode}
    </br>
    Exception: ${pageContext.exception}
    </br></br></br>
    Message from exception: ${requestScope['jakarta.servlet.error.message']}
</div>
</body>
</html>