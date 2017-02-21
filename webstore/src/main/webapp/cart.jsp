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
  <link href="css/bootstrap.min.css" rel="stylesheet">

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
<script src="js/bootstrap.min.js"></script>

<div class="row">
  <div class="col-md-6 col-md-offset-3">

    <table class="table table-hover">
      <thead>
      <tr>
        <td>name</td>
        <td>count</td>
        <td>price summary</td>
        <td></td>
      </tr>
      </thead>
      <%--<tr>--%>
      <c:forEach var="tempProduct" items="${cata}">
        <c:url var = "DelFromCart" value="/cart">
          <c:param name="command" value="DEL" />
          <c:param name="id" value="${tempProduct.id}"/>
        </c:url>
        <tr>
          <td> ${tempProduct.name} </td>
          <td> ${tempProduct.count} </td>
          <td> ${tempProduct.summary} </td>
          <td><a role="button" href="${DelFromCart}" class="btn btn-default btn-block">remove</a></td>
        </tr>

      </c:forEach>
      <tr>

        <h1>Shopping cart</h1>
      </tr>
      <%--</tr>--%>


    </table>
    <strong>Sum of elements </strong>
    <c:set var="sum" value="${cart_sum}"></c:set>
    <c:out value="${sum}"></c:out>
    <div class="row">
      <div class="col-md-6"><a role="button" href="${root}/customer/mts" class="btn btn-default">Back to shop</a></div>
      <c:url var = "createOrder" value="/myorders">
        <c:param name="command" value="CORD" />
        <c:param name="customer_id" value="${3}"/>
      </c:url>
      <div class="col-md-6"><a role="button" href="${createOrder}" class="btn btn-default">Create order</a></div>
    </div>
  </div>
</div>
<br>
<b><a href="${root}/logout">Logout</a></b>
</body>
</html>