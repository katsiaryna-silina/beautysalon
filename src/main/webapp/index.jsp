<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>

<body>
<jsp:include page="jsp/fragment/header_default.jsp"/>
<br/>
<h3 class="text-center"><fmt:message key="description.salon.message"/></h3>

<jsp:include page="jsp/fragment/footer.jsp"/>
</body>
</html>