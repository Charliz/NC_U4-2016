<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Admin Realm</title>
</head>


<body>
<p>
    Where would you like to go?
    <br>
    <br>
    Please choose:
    <br>
    <br>
    <a href="${root}/admin/listProducts"> View Catalog </a>
    <br>
    <br>
    <a href="${root}/admin/add-product-form.jsp"/> Add Products </a>
</p>
</body>
</html>