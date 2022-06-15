<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>All users</title>
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

    <h3>Users</h3>

    <br/>
    <table class="table table-busered table-striped" id="userTable">

        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone number</th>
            <th>Discount status</th>
            <th>Discount</th>
            <th>User status</th>
            <th>Last login</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="user" items="${users}">
            <td>${user.id.toString()}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.discountStatus.discount}%</td>
            <td>${user.discountStatus.status}</td>
            <td>${user.userStatus}</td>
            <td>${user.lastLogin.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${user.lastLogin}"/>
            </td>


            <td>
                <a class="btn btn-info" href="#">Update</a>
                    <%--
                                    <a class="btn btn-info" href="@{/users/updateform(userId=${user.id})}">Update</a>
                    --%>
            </td>
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
