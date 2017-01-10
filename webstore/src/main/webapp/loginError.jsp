<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Login Error</title>
</head>


<body>
<p>
    Sorry, you entered incorrect Username or Password.
    <br>
    <br>
    Please try again: <a href="${root}/adminIndex.jsp" />Homepage</a>
</p>
</body>
</html>