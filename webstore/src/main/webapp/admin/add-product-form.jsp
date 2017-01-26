<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Add Product</title>
</head>
<body>

<h3>Add Product</h3>

<form action="${root}/admin/ControllerServlet" method="GET">

    <input type="hidden" name="command" value="ADD" />

    <table>
        <tbody>

        <tr>
            <td><label>Brand:</label></td>
            <td><input type="text" name="brand" /></td>
        </tr>
        <tr>
            <td><label>Product Name:</label></td>
            <td><input type="text" name="productName" /></td>
        </tr>
        <tr>
            <td><label>Price:</label></td>
            <td><input type="text" name="price" /></td>
        </tr>
        <tr>
            <td><label>Description:</label></td>
            <td><input type="text" name="description" /></td>
        </tr>
        <br>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save" /></td>
        </tr>

        </tbody>
    </table>
</form>

<p>
    <a href="${root}/admin/ControllerServlet">Back to Product List</a>
</p>
<p>
    <b><a href="${root}/adminLogout">Logout</a></b>
</p>
</body>
</html>