<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin Login</title>
</head>
<body>

<h2>Hello!! Please, Login</h2>

<form action="j_security_check" method=post >
    <strong>Username: </strong>
    <input type="text" name="j_username">

    <strong>Password: </strong>
    <input type="password" name="j_password">

    <input type="submit" value="Login!">
    <input type="reset" value="Reset!">
</form>

</body>
</html>
