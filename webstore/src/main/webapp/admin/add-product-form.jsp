<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Add Product</title>
</head>
<body>

<h3>Add Product</h3>

<form action="${root}/admin/createProduct" method="post" onsubmit="return validate();">

    <script type="text/javascript">
        function validate()
        {
            var price = document.getElementById("price").value;
            var quantity = document.getElementById("quantity").value;
            var valid = true;
            if(isNaN(price) || isNaN(quantity)){
                alert("Please Enter a number for the Price!!");
                valid = false;
            }
            return valid;
        };
    </script>

    <table>
        <tbody>

        <tr>
            <td><label><b>Brand:</b></label></td>
            <td><input type="text" name="brand" required/></td>
        </tr>
        <tr>
            <td><label><b>Product Name:</b></label></td>
            <td><input type="text" name="productName" required/></td>
        </tr>
        <tr>
            <td><label><b>Price:</b></label></td>
            <td><input type="text" name="price" id="price" required/></td>
        </tr>
        <tr>
            <td><label><b>Description:</b></label></td>
            <td><input type="text" name="description" required/></td>
        </tr>
        <tr>
            <td><label><b>Quantity:</b></label></td>
            <td><input type="text" name="quantity" id="quantity" required/></td>
        </tr>
        <br>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>

        </tbody>
    </table>
</form>

<p>
    <a href="${root}/admin/listProducts">Back to Product List</a>
</p>
<p>
    <br>
    <b><a href="${root}/logout">Logout</a></b>
</p>
</body>
</html>