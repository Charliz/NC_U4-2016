<%@ page import="com.ncproject.webstore.entity.Customer" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25.12.2016
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer page</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

    <h1 align="center">Welcome back</h1>
    <form action="/webstore/editUser" method="post">
        <table align="center">
            <tr>
                <td>Login:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getLogin()%></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getEmail()%></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getName()%></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getAddress()%></td>
            </tr>
            <tr>
                <td>Payment:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getPayment()%></td>
            </tr>
            <tr><td><input type="submit" value="Edit user" class="btn btn-default btn-block"></td></tr>
        </table>
    </form>
    <%--<a role="button" href="/webstore/mts" class="btn btn-default">To shop!</a>--%>
</body>
</html>
