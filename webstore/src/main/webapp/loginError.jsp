<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Login Error</title>
    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
</head>


<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>
<p>
    Sorry, you entered incorrect Username or Password.
    <br>
    <br>
    Please try again: <a href="${root}/userIndex.jsp" />Homepage</a>
</p>
</body>
</html>