<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.04.2022
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<br>
Request from: ${pageContext.errorData.requestURI} is failed
</br>
Servlet name : ${pageContext.errorData.servletName}
</br>
Status code: ${pageContext.errorData.statusCode}
</br>
Exception: ${pageContext.exception}
</br></br></br>
Message from exception: ${requestScope['jakarta.servlet.error.message']}
</body>
</html>
