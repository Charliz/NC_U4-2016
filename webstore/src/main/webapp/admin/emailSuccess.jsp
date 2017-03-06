<%@ page import="com.ncproject.webstore.entity.MailEvent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Email Success</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

<h1 align="center"> Email to <b><c:out value="${email}"></c:out></b> Sent Successfully!!</h1>
<form>
    <table align="center">

        <tr><td><a role="button" href="${root}/admin/listOrders" class="btn btn-default btn-block">Back to Orders List</a></td></tr>
    </table>

</form>

</body>
</html>