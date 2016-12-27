<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>

<h3>Update Product</h3>

<form action="ControllerServlet" method="GET">

    <input type="hidden" name="command" value="UPDATE" />
    <input type="hidden" name="productId" value="${THE_PRODUCT.prod_id}" />

    <table>
        <tbody>

        <tr>
            <td><label>Description:</label></td>
            <td><input type="text" name="description" value="${THE_PRODUCT.description}"/></td>
        </tr>

        <tr>
            <td><label>Product Name:</label></td>
            <td><input type="text" name="productName" value="${THE_PRODUCT.productName}"/></td>
        </tr>

        <tr>
            <td><label>Price:</label></td>
            <td><input type="text" name="price" value="${THE_PRODUCT.price}"/></td>
        </tr>

        <tr>
            <td><label>Brand:</label></td>
            <td><input type="text" name="brand" value="${THE_PRODUCT.brand}"/></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save" /></td>
        </tr>

        </tbody>
    </table>
</form>

<p>
    <a href="ControllerServlet">Back to Product List</a>
</p>

</body>

</html>