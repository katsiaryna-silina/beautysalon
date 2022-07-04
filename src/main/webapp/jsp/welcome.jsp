<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="en_US" scope="session"/>
<%-- value="#{locale}"--%>
<fmt:setBundle basename="pagecontent.properties"/>
<html>
<head>
    <title><fmt:message key="lable.title.welcome"/>2cat - welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

</head>
<body>
<jsp:include page="fragment/header_default.jsp"/>
<br/>
<h3 class="text-center">
    Hello ${username}! You are successfully registered! You are welcome!</h3>

<jsp:include page="fragment/footer.jsp"/>
</body>
</html>
