<%--
  Created by IntelliJ IDEA.
  User: Champion
  Date: 27.02.2017
  Time: 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email</title>
</head>
<body>
<h1>Email Status</h1>
<form method="POST" action="/emailServlet">
    <label for="to">To:</label><input id="to" name="to" type="text" value="${THE_CUSTOMER.email}" required/><br/>
    <label for="subject">Subject:</label><input id="subject" name="subject" type="text" required/><br/>
    <textarea name="body" cols="60" rows="15" required></textarea><br/>
    <br>
    <input type="submit" value="Send"/>
</form>
</body>
</html>
