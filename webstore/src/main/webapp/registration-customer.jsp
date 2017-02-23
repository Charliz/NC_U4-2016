<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Черный
  Date: 28.12.2016
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Registration</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <br><br>
        </div>
    </div>

    <h1 align="center">Enter the data</h1>

    <form action="/webstore/registration" method="post">
        <table align="center">
            <tr>
                <td align="right">Login:</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td align="right">Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td align="right">Confirm password:</td>
                <td><input type="password" name="confirm password"></td>
            </tr>
            <tr>
                <td align="right">Email:</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td align="right">Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td align="right">Address:</td>
                <td><input type="text" name="address"></td>
            </tr>
            <tr>
                <td align="right">Payment:</td>
                <td><select name="payment">
                <option value="">select card type</option>
                <option value="master card">master card</option>
                <option value="visa">visa</option>
            </select></td></tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Registration" class="btn btn-default btn-block"></td>
            </tr>
        </table>
    </form>
</body>
</html>
