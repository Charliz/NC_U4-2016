<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ncproject.webstore.entity.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Registration Success</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

    <h1 align="center"> Registration Successful! Welcome,  <b><%=((Customer)request.getSession().getAttribute("myUser")).getName()%>!!</b></h1>
    <form>
        <table align="center">
            <tr>
            <br>
            <td><a role="button" href="${root}/customer/mts" class="btn btn-default btn-block">Enter the Web Shop</a></td>
            <br>
            <tr><td><a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a></td></tr>
        </table>

    </form>
    <%--<a role="button" href="/webstore/mts" class="btn btn-default">To shop!</a>--%>
</body>
</html>
