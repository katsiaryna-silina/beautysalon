<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.users"/></title>
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
<jsp:include page="../fragment/header_admin_with_locale.jsp"/>
<div class="container-fluid">
    <br/>
    <br/>
    <h3><fmt:message key="table.header.users"/></h3>
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
                title: '<fmt:message key="table.title.id"/>'
            }, {
                field: 'username',
                title: '<fmt:message key="table.title.username"/>'
            }, {
                field: 'email',
                title: '<fmt:message key="table.title.email"/>'
            }, {
                field: 'role',
                title: '<fmt:message key="table.title.role"/>',
                formatter: 'dataFormatter'
            }, {
                field: 'firstName',
                title: '<fmt:message key="table.title.first.name"/>'
            }, {
                field: 'lastName',
                title: '<fmt:message key="table.title.last.name"/>'
            }, {
                field: 'phoneNumber',
                title: '<fmt:message key="table.title.phone.number"/>'
            }, {
                field: 'lastLogin.value',
                title: '<fmt:message key="table.title.last.login"/>',
                formatter: 'dateFormatter'
            }, {
                field: 'discountStatus.status',
                title: '<fmt:message key="table.title.discount.status"/>',
                formatter: 'dataFormatter'
            }, {
                field: 'discountStatus.discount',
                title: '<fmt:message key="table.title.discount"/>',
                formatter: 'discountFormatter'
            }, {
                field: 'userStatus',
                title: '<fmt:message key="table.title.user.status"/>',
                formatter: 'dataFormatter'
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

    function dateFormatter(value, row) {
        return new Date(value).toLocaleString();
    }

    function discountFormatter(value, row) {
        return value + "%";
    }

    function operateFormatter(value, row, index) {
        return [
            '<div class="right">',
            '<a class="btn btn-info" href="${pageContext.request.contextPath}/controller?command=update_user&username=' + row.username +
            '"><fmt:message key="button.update"/>',
            '</a>  ',
            '</div>'
        ].join('')
    }
</script>

<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>