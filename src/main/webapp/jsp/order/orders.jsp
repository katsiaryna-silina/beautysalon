<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All orders</title>
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
<jsp:include page="../fragment/header_client.jsp"/>
<div class="container-fluid">
    <br/>
    <br/>
    <h3>Orders</h3>
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
            url: '${pageContext.request.contextPath}/controller?command=get_orders_for_client_json',
            columns: [{
                field: 'id',
                title: 'ID'
            }, {
                field: 'orderDateTime.value',
                title: 'Order date and time',
                formatter: 'dateFormatter'
            }, {
                field: 'visitDate.value',
                title: 'Visit date'
            }, {
                field: 'visitBeginTime.value',
                title: 'Visit begin time'
            }, {
                field: 'visitEndTime.value',
                title: 'Visit end time'
            }, {
                field: 'serviceNames',
                title: 'Service names',
                formatter: 'listFormatter'
            }, {
                field: 'priceWithDiscount',
                title: 'Price with discount',
                formatter: 'priceFormatter'
            }, {
                field: 'status',
                title: 'Order status'
            }, {
                field: 'description',
                title: 'Description'
            }, {
                field: 'feedback',
                title: 'Feedback',
                align: 'center',
                clickToSelect: false,
                formatter: "feedbackFormatter"
            }, {
                field: 'operate',
                title: 'Operate',
                align: 'center',
                clickToSelect: false,
                formatter: "operateFormatter"
            }]
        })
    });

    function dataFormatter(value, row) {
        return value.toLowerCase();
    }

    function listFormatter(value, row) {
        value.forEach(function (part, index) {
            value[index] = " " + value[index];
        });
        return value;
    }

    function dateFormatter(value, row) {
        return new Date(value).toLocaleString();
    }

    function priceFormatter(value, row) {
        return value + "$";
    }

    function feedbackFormatter(value, row, index) {
        return [
            '<div class="right">',
            '<a class="btn btn-success" href="${pageContext.request.contextPath}/controller?command=show_feedback&id=' + row.id + '">Update feedback',
            '</a>  ',
            '</div>'
        ].join('')
    }

    function operateFormatter(value, row, index) {
        return [
            '<div class="right">',
            '<a class="btn btn-info" href="${pageContext.request.contextPath}/controller?command=update_order_by_client&id=' + row.id + '&status=' + row.status + '">Update',
            '</a>  ',
            '</div>'
        ].join('')
    }
</script>

<jsp:include page="../fragment/footer.jsp"/>
</body>

</html>