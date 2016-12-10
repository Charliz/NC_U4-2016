<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Online Shop App</title>
    <link rel="stylesheet" type="text/css"  href="<c:url value='/css/mycss.css' />">
</head>

<body>
<h2>Products</h2>

<input type="button" value="Add Product"
       onclick="window.location.href='add-product-form.jsp'"
/> <br><br>

<table class ="table" prod_id = "myTable">

    <thead class = table-header>
    <tr>
        <th>ID</th>
        <th>Category</th>
        <th>Description</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Brand</th>
        <th>Action</th>
    </tr>
    </thead>

    <%--- the attribute "PRODUCT_LIST" comes from the "request" object ---%>
    <c:forEach var="tempProduct" items="${PRODUCT_LIST}">

        <!-- set up a link for each product -->
        <c:url var ="updateLink" value="ControllerServlet">
            <c:param name="command" value="LOAD" />
            <c:param name="productId" value="${tempProduct.prod_id}"/>
        </c:url>

        <!-- set up a link to delete a product -->
        <c:url var = "deleteLink" value="ControllerServlet">
            <c:param name="command" value="DELETE" />
            <c:param name="productId" value="${tempProduct.prod_id}"/>
        </c:url>

        <tr>
            <td> ${tempProduct.prod_id} </td>
            <td> ${tempProduct.category} </td>
            <td> ${tempProduct.description} </td>
            <td> ${tempProduct.productName} </td>
            <td> ${tempProduct.price} </td>
            <td> ${tempProduct.brand} </td>
            <td>
                <a href="${updateLink}">Update</a><br>

                <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete entry?'))) return false">Delete</a><br><br>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
