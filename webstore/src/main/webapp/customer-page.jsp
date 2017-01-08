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
</head>
<body>
    <h1 align="center">Welcome back</h1>
    <form action="/webstore/editUser" method="post">
        <table align="center">
            <tr>
                <td align="right">Login:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getLogin()%></td>
            </tr>
            <tr>
                <td align="right">Email:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getEmail()%></td>
            </tr>
            <tr>
                <td align="right">Name:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getName()%></td>
            </tr>
            <tr>
                <td align="right">Address:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getAddress()%></td>
            </tr>
            <tr>
                <td align="right">Payment:</td>
                <td><%=((Customer)request.getSession().getAttribute("myUser")).getPayment()%></td>
            </tr>
            <tr><td><input type="submit" value="Edit user"></td></tr>
        </table>
    </form>
</body>
</html>
