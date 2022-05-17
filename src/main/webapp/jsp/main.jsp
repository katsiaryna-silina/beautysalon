<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hi (redirect/forward) = ${username}
<hr/>
${filter_attr}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logOut"/>
    <input type="submit" value="logOut"/>
</form>
</body>
</html>
