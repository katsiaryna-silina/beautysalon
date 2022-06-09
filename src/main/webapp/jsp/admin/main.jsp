<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>2cat - main page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../fragment/header_admin.jsp"/>

Hi ${username}
<hr/>
${filter_attr}
<hr/>
<jsp:include page="../fragment/footer.jsp"/>

</body>
</html>
