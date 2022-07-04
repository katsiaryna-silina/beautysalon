<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>All orders</title>
    <link href="https://cdn.datatables.net/v/bs4/dt-1.10.25/datatables.min.css"
          rel="stylesheet"
          type="text/css"/>
    <link crossorigin="anonymous"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          rel="stylesheet"/>
</head>
<body>
<jsp:include page="../fragment/header_admin.jsp"/>
<div class="container">
    <br/><br/>

    <h3>Orders</h3>

    <br/>
    <table class="table table-busered table-striped" id="userTable">

        <thead>
        <tr>
            <th>Id</th>
            <th>Order date</th>
            <th>Username</th>
            <th>User's first name</th>
            <th>User's last name</th>
            <th>Email</th>
            <th>Phone number</th>
            <th>Visit day and time</th>
            <th>Services</th>
            <th>Price with discount</th>
            <th>Order status</th>
            <th>Decline reason</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>
                    <a class="btn btn-info"
                       href="${pageContext.request.contextPath}/controller?command=update_user&username=${user.username}">Update</a>
                    <p class="small text-danger">${update_user_error_message}</p>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
</div>

<jsp:include page="../fragment/footer.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/v/bs4/dt-1.10.25/datatables.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        $("#userTable").DataTable({
            'aoColumnDefs': [{
                'bSortable': false,
                'aTargets': [-1] /* 1st one, start by the right */
            }]
        });
    })
</script>
</body>

</html>

