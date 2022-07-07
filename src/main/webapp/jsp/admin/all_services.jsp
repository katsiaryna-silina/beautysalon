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
            url: '${pageContext.request.contextPath}/controller?command=get_all_services_json',
            columns: [{
                field: 'id',
                title: 'ID'
            }, {
                field: 'name',
                title: 'Name'
            }, {
                field: 'isComplex',
                title: 'Is complex'
            }, {
                field: 'description',
                title: 'Description'
            }, {
                field: 'price',
                title: 'Price',
                formatter: 'priceFormatter'
            }, {
                field: 'minutesNeeded',
                title: 'Minutes needed'
            }, {
                field: 'isDeprecated',
                title: 'Is deprecated'
            }, {
                field: 'operate',
                title: 'Item Operate',
                align: 'center',
                clickToSelect: false,
                formatter: "operateFormatter"
            }]
        })
    });

    function priceFormatter(value, row) {
        return value + "$";
    }

    function operateFormatter(value, row, index) {
        if (row.isDeprecated) {
            return [
                '<div class="right">',
                '<a class="btn btn-outline-success" href="${pageContext.request.contextPath}/controller?command=update_service&id=' + row.id + '&is_deprecated=' + row.isDeprecated + '">Return to client\'s select',
                '</a>  ',
                '</div>'
            ].join('')
        } else {
            return [
                '<div class="right">',
                '<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/controller?command=delete_service&id=' + row.id + '&is_deprecated=' + row.isDeprecated + '">Delete from client\'s select',
                '</a>  ',
                '</div>'
            ].join('')
        }
    }
</script>

<jsp:include page="../fragment/footer.jsp"/>
</body>

</html>