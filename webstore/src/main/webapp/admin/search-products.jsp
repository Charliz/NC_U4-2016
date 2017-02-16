<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Show Employee info</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/mycss.css' />">
</head>
<body>
<p>
    <a href="${root}/admin/listProducts">Back to Products List</a>
</p>

<p>
    <b><a href="${root}/adminLogout">Logout</a></b>
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
