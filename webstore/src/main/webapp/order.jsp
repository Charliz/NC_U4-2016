<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ncproject.webstore.entity.Customer" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 23.02.2017
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New order</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h4 align="right"><%=((Customer)request.getSession().getAttribute("myUser")).getLogin()%></h4>
        <h4 align="right"><a href="${root}/logout">Logout</a></h4>
        <br><br>
    </div>
</div>

<div class="row">
    <div class="col-md-6 col-md-offset-3">

        <table class="table table-hover">
            <thead>
            <tr>
                <td>Date</td>
                <td>Status</td>
                <td>List of products</td>
                <td>Sum</td>
            </tr>
            </thead>
            <c:forEach var="tempProduct" items="${ords}">
                <tr>
                    <td> ${tempProduct.data} </td>
                    <td> ${tempProduct.status} </td>
                    <td> ${tempProduct.productlist} </td>
                    <td> ${tempProduct.total} </td>
                </tr>
            </c:forEach>
            <tr>
                <h1>We have made your new order. You are welcome!</h1>
            </tr>
        </table>

        <a role="button" href="${root}/customer/mts" class="btn btn-default">Back to shop</a>
    </div>
</div>

</body>
</html>
