<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
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
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <jsp:include page="../fragment/header_admin_with_locale.jsp"/>
    </c:when>
    <c:when test="${role == 'CLIENT'}">
        <jsp:include page="../fragment/header_client_with_locale.jsp"/>
    </c:when>
</c:choose>
<div class="container-fluid">
    <br/>
    <br/>
    <h3><fmt:message key="header.orders"/></h3>
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
                title: '<fmt:message key="table.title.id"/>'
            }, {
                field: 'orderDateTime.value',
                title: '<fmt:message key="table.title.order.date.time"/>',
                formatter: 'dateFormatter'
            }, {
                field: 'visitDate.value',
                title: '<fmt:message key="table.title.visit.date"/>',
            }, {
                field: 'visitBeginTime.value',
                title: '<fmt:message key="table.title.visit.time.begin"/>'
            }, {
                field: 'visitEndTime.value',
                title: '<fmt:message key="table.title.visit.time.end"/>'
            }, {
                field: 'serviceNames',
                title: '<fmt:message key="table.title.service.names"/>',
                formatter: 'listFormatter'
            }, {
                field: 'priceWithDiscount',
                title: '<fmt:message key="table.title.price.with.discount"/>',
                formatter: 'priceFormatter'
            }, {
                field: 'status',
                title: '<fmt:message key="table.title.order.status"/>'
            }, {
                field: 'description',
                title: '<fmt:message key="table.title.description"/>'
            }, {
                field: 'feedback',
                title: '<fmt:message key="table.title.feedback"/>',
                align: 'center',
                clickToSelect: false,
                formatter: "feedbackFormatter"
            }, {
                field: 'operate',
                title: '<fmt:message key="table.title.operate"/>',
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
            '<a class="btn btn-success" href="${pageContext.request.contextPath}/controller?command=show_feedback&id=' + row.id + '"><fmt:message key="button.update.feedback"/>',
            '</a>  ',
            '</div>'
        ].join('')
    }

    function operateFormatter(value, row, index) {
        if (${role == 'ADMIN'}) {
            return [
                '<div class="right">',
                '<a class="btn btn-info" href="${pageContext.request.contextPath}/controller?command=update_order_by_admin&id=' + row.id + '&status=' + row.status + '"><fmt:message key="button.update"/>',
                '</a>  ',
                '</div>'
            ].join('')
        } else if (${role == 'CLIENT'}) {
            return [
                '<div class="right">',
                '<a class="btn btn-info" href="${pageContext.request.contextPath}/controller?command=update_order_by_client&id=' + row.id + '&status=' + row.status + '"><fmt:message key="button.update"/>',
                '</a>  ',
                '</div>'
            ].join('')
        }
    }
</script>

<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>