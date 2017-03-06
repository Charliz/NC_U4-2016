<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="upload" value="${UPLOAD}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Admin control panel</title>

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

<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>
<a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a>

<br><br>

<form class="form-style" action="${root}/admin/searchProducts">

    <div>
        <label>Product Name:<input type="text" name ="productName" required/></label>
        <input type="submit" value="Search">
    </div>

</form>
<br>

<form class="form-style" action="${root}/admin/listProducts" method = "get">

    <c:url var = "addLink" value="/admin/add-product-form.jsp"></c:url>
    <div class="col-md-6"><a role="button" href="${addLink}" class="btn btn-default">Add Product</a></div>

    <c:url var = "ordersLink" value="/admin/listOrders"></c:url>
    <div class="col-md-6"><a role="button" href="${ordersLink}" class="btn btn-default">View Orders</a></div>


    <br>

    <table class ="table" id = "myTable">

        <thead class = table-header>
        <tr>
            <th>Brand</th>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        </thead>

        <%--- the attribute "PRODUCT_LIST" comes from the "request" object ---%>
        <c:forEach var="tempProduct" items="${PRODUCT_LIST}">

            <!-- set up a link for each product -->
            <c:url var ="updateLink" value="/admin/loadProductToForm">
                <c:param name="productId" value="${tempProduct.prod_id}"/>
            </c:url>

            <!-- set up a link to delete a product -->
            <c:url var = "deleteLink" value="/admin/deleteProduct">
                <c:param name="productId" value="${tempProduct.prod_id}"/>
            </c:url>

            <tr class = table-odd-row>
                <td> ${tempProduct.brand} </td>
                <td> ${tempProduct.productName} </td>
                <td><div style="width: 270px; height: 40px; text-align:center; padding: 14px; overflow: auto;">
                        ${tempProduct.description} </div>
                </td>
                <td> ${tempProduct.price} </td>
                <td> ${tempProduct.quantity} </td>
                <td>
                    <form class="form-style" action="${root}/admin/uploadPicture" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="description" value="${tempProduct.prod_id}" />
                        <input type="file" name="file" />
                        <input type="submit" value="UPLOAD" />
                    </form>
                    <img src="${root}/images/${tempProduct.prod_id}.jpg" alt=""width="100" height="70"/>
                </td>

                <td>
                    <a href="${updateLink}">Update</a><br>

                    <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete entry?'))) return false">Delete</a><br>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>