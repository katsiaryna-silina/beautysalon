<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>2cat - new order</title>
</head>
<body>
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <jsp:include page="fragment/header_admin.jsp"/>
    </c:when>
    <c:when test="${role == 'CLIENT'}">
        <jsp:include page="fragment/header_client.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="fragment/header_default.jsp"/>
    </c:otherwise>
</c:choose>
<br/>
<div class="container" style="width: 415pt">

    <h1>New order</h1>
    </br>

    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="new_order"/>

        <div class="form-group">
            <label for="input-date_time">Visit date and time: </label>
            <input type="datetime-local" name="visit_date_time" class="form-control"
                   id="input-date_time"
                   step="900"
                   min="${min_date_time}" max="${max_date_time}">
        </div>


        <h5>Pick services you want:</h5>

        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="combing" name="combing">
            <label class="form-check-label" for="combing">
                Combing
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="nail_clipping" name="nail_clipping">
            <label class="form-check-label" for="nail_clipping">
                Nail clipping
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="ear_cleaning" name="ear_cleaning">
            <label class="form-check-label" for="ear_cleaning">
                Ear cleaning
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="drying" name="drying">
            <label class="form-check-label" for="drying">
                Drying
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="haircut" name="haircut">
            <label class="form-check-label" for="haircut">
                Haircut
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="express_molting" name="express_molting">
            <label class="form-check-label" for="express_molting">
                Express molting
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="all_complex" name="all_complex">
            <label class="form-check-label" for="all_complex">
                All complex
            </label>
        </div>

        <button class="btn btn-primary col-2" type="submit">Save</button>
    </form>
</div>
<jsp:include page="fragment/footer.jsp"/>
</body>
</html>

