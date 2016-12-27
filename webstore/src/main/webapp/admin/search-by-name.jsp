<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Employee info</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/mycss.css' />">
</head>
<body>
<form action="ControllerServlet" method="GET" class="form-style" name = "form1">

    <table class ="table">

        <thead class = table-header>
        <tr>
            <th>Description</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Brand</th>
        </tr>
        </thead>

    <c:forEach var="tempProduct" items="${PRODUCTS}">
        <tr class="table-odd-row">
            <td> ${tempProduct.description} </td>
            <td> ${tempProduct.productName} </td>
            <td> ${tempProduct.price} </td>
            <td> ${tempProduct.brand} </td>
        </tr>
    </c:forEach>
    </table>
</form>
<p>
    <a href="ControllerServlet">Back to Products List</a>
</p>
</body>
</html>
