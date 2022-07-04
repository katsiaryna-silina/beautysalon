<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.20.2/dist/bootstrap-table.min.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>

<body>
<jsp:include page="../fragment/header_admin.jsp"/>
<div class="container-fluid">
    <br/>
    <br/>
    <h3>Users</h3>
    <br/>
    <table id="table" class="table table-bordered table-light table-hover">
        <thead class="table-dark"/>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.20.2/dist/bootstrap-table.min.js"></script>
<script>
    $(function () {
        $('#table').bootstrapTable({
            toggle: 'table',
            pagination: true,
            height: 520,
            sidePagination: 'server',
            showExtendedPagination: true,
            totalNotFilteredField: 'totalNotFiltered',
            url: '${pageContext.request.contextPath}/controller?command=get_users_json',
            columns: [{
                field: 'id',
                title: 'ID'
            }, {
                field: 'username',
                title: 'Username'
            }, {
                field: 'email',
                title: 'Email'
            }, {
                field: 'role',
                title: 'Role',
                formatter: 'dataFormatter'
            }, {
                field: 'firstName',
                title: 'First Name'
            }, {
                field: 'lastName',
                title: 'Last Name'
            }, {
                field: 'phoneNumber',
                title: 'Phone number'
            }, {
                field: 'lastLogin.value',
                title: 'Last login',
                formatter: 'dateFormatter'
            }, {
                field: 'discountStatus',
                title: 'Discount status',
                formatter: 'dataFormatter'
            }, {
                field: 'userStatus',
                title: 'User status',
                formatter: 'dataFormatter'
            }, {
                field: 'operate',
                title: 'Item Operate',
                align: 'center',
                clickToSelect: false,
                formatter: "operateFormatter"
            }]
        })
    });

    function dataFormatter(value, row) {
        return value.toLowerCase();
    }

    function dateFormatter(value, row) {
        return new Date(value).toLocaleString();
    }

    function operateFormatter(value, row, index) {
        return [
            '<div class="right">',
            '<a class="btn btn-info" href="${pageContext.request.contextPath}/controller?command=update_user&username=' + row.username + '">Update',
            '</a>  ',
            '</div>'
        ].join('')
    }
</script>

<jsp:include page="../fragment/footer.jsp"/>
</body>

</html>