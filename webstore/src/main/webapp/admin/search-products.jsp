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
        <br><br>
    </div>
</div>
<p>
    <a role="button" href="${root}/admin/listProducts" class="btn btn-default btn-block">Back to Products List</a>
</p>

<p>
    <br>
    <a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a>
</p>
<form class="form-style" name = "form1">

    <table class ="table">

        <thead class = table-header>
        <tr>
            <th>Brand</th>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        </thead>

<c:choose>
    <c:when test="${not empty PRODUCTS}">
    <c:forEach var="tempProduct" items="${PRODUCTS}">
        <tr class="table-odd-row">
            <td> ${tempProduct.brand} </td>
            <td> ${tempProduct.productName} </td>
            <td> <div style="width: 270px; height: 40px; text-align:center; padding: 14px; overflow: auto;">
                    ${tempProduct.description} </div></td>
            <td> ${tempProduct.price} </td>
        </tr>
    </c:forEach>
    </c:when>
    <c:otherwise>
        <p><b> No PRODUCTS found !!</b></p>
    </c:otherwise>
</c:choose>

    </table>
</form>

</body>
</html>
