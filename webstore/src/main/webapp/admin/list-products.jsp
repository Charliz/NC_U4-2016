<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Online Shop App</title>
    <link type="text/css" rel="stylesheet" href="${root}/css/mycss.css"/>
</head>

<body>
<h2>Products</h2>

<p>
    <b><a href="${root}/adminLogout">Logout</a></b>
</p>

<form class="form-style" >

    <div>
        <label>Product Name:<input type="text" name ="productName"/></label> <input type="submit" name="command" value="Search_Name">
    </div>

</form>

<form class="form-style" >

    <input type="button" value="Add Product"
           onclick="window.location.href='/webstore/admin/add-product-form.jsp'"
    /> <br><br>

    <table class ="table" id = "myTable">

        <thead class = table-header>
        <tr>
            <th>Brand</th>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
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

            <tr class = table-odd-row>
                <td> ${tempProduct.brand} </td>
                <td> ${tempProduct.productName} </td>
                <td><div style="width: 270px; height: 40px; text-align:center; padding: 14px; overflow: auto;">
                        ${tempProduct.description} </div>
                </td>
                <td> ${tempProduct.price} </td>

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