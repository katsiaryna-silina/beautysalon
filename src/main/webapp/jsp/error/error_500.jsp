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
