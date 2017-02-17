<%--
  Created by IntelliJ IDEA.
  User: Archi
  Date: 12.12.2016
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <form action="j_security_check" method=post>
        <table align="center">
            <tr>
                <td align="right">Login:</td>
                <td><input type="text" name="j_username"></td>
            </tr>
            <tr>
                <td align="right">Password:</td>
                <td><input type="password" name="j_password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Registration" name="registr" class="btn btn-default btn-block"></td>
                <td><input type="submit" value="Log In" class="btn btn-default btn-block"></td>
            </tr>
        </table>
    </form>
</body>
</html>
