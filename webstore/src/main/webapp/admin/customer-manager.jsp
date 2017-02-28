<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>BootstrapTemplate</title>

  <!-- Bootstrap -->
  <link href="${root}/css/bootstrap.min.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${root}/js/bootstrap.min.js"></script>

<div class="panel panel-primary">
  <div class="panel-heading">
    <h4><a role="button" href="${root}/adminIndex.jsp" class="btn btn-default btn-block">My profile</a></h4>

    <h4><a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a></h4>
  </div>
</div>

<div class="row">
  <div class="col-md-8">

    <table class="table table-hover">
      <thead>
      <tr>
        <td>Name</td>
        <td>Address</td>
        <td>Login</td>
        <td>Password</td>
        <td>Email</td>
        <td>Payment</td>
      </tr>
      </thead>
      <%--<tr>--%>
      <c:forEach var="listUsers" items="${users}">
        <c:url var = "deleteUser" value="/admin/deleteCustomer">
          <c:param name="command" value="delCust" />
          <c:param name="custEmail" value="${listUsers.email}"/>
        </c:url>
        <tr>
          <td> ${listUsers.name} </td>
          <td> ${listUsers.address} </td>
          <td> ${listUsers.login} </td>
          <td> ${listUsers.password} </td>
          <td> ${listUsers.email} </td>
          <td> ${listUsers.payment} </td>

          <td><a role="button" href="${deleteUser}" class="btn btn-default btn-block">delete user</a></td>
        </tr>
      </c:forEach>
      <%--</tr>--%>
      <br>
      <b><a href="${root}/logout">Logout</a></b>
    </table>

  </div>
</div>

</body>
</html>
