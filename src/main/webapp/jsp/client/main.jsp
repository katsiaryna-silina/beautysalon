<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>2cat - main page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../fragment/header_client.jsp"/>

Hi ${username}
<hr/>
${filter_attr}
<hr/>
<jsp:include page="../fragment/footer.jsp"/>


<form action="controller">
    <input type="hidden" name="command" value="logOut"/>
    <input type="submit" value="logOut"/>
</form>
</body>
</html>
