<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>BootstrapTemplate</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${root}/js/bootstrap.min.js"></script>
<%--<div class="panel panel-primary">--%>
<%--&lt;%&ndash;<div class="panel panel-default">&ndash;%&gt;--%>
<%--<div class="panel-title">--%>
<%--Panel content--%>
<%--</div>--%>
<%--<div class="panel-body">Panel footer</div>--%>
<%--</div>--%>
<%--</div>--%>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h4><a role="button" href="${root}/admin/listProducts" class="btn btn-default btn-block">View Catalog </a></h4>
        <h4><a role="button" href="${root}/admin/add-product-form.jsp" class="btn btn-default btn-block">Add Products </a></h4>
    </div>
</div>
<a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a>

</body>
</html>