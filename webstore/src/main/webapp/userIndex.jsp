<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Archi
  Date: 12.12.2016
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

<h1 align="center">Hello</h1>
<form>
    <table align="center">

        <tr>
            <a href="${root}/customer/mts">Enter the Web Shop</a>

            <br>
            <br>

            <a href="${root}/registration-customer.jsp">Register</a>

        </tr>
    </table>
</form>
</body>
</html>
