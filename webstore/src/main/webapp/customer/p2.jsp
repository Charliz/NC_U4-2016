<%@ page import="com.ncproject.webstore.entity.Customer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="upload" value="${UPLOAD}"/>
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

  <link href="${root}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
  <link href="${root}/css/slider.css" rel="stylesheet" type="text/css" media="all"/>
  <script type="text/javascript" src="${root}/js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${root}/js/move-top.js"></script>
  <script type="text/javascript" src="${root}/js/easing.js"></script>
  <script type="text/javascript" src="${root}/js/startstop-slider.js"></script>

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
    <h4 align="right"><%=((Customer)request.getSession().getAttribute("myUser")).getLogin()%></h4>
    <h4 align="right"><a href="${root}/logout">Logout</a></h4>
    <h4><a role="button" href="${root}/customer/customer-page.jsp" class="btn btn-default btn-block">My profile</a></h4>
    <h4><a role="button" href="/webstore/cart" class="btn btn-default btn-block">My cart</a></h4>
    <h4><a role="button" href="/webstore/myorders" class="btn btn-default btn-block">My orders</a></h4>
  </div>

  <div class="panel-body">sum of all items is <c:out value="${cart_sum}"></c:out></div>
</div>

<%--<div class="row">--%>
  <%--<div class="col-md-6 col-md-offset-3">--%>

    <%--<table class="table table-hover">--%>
      <%--<thead>--%>
        <%--<tr>--%>
          <%--<td>name</td>--%>
          <%--<td>description</td>--%>
          <%--<td>price</td>--%>
          <%--<td>quantity</td>--%>
          <%--<td></td>--%>
        <%--</tr>--%>
      <%--</thead>--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
      <%--<c:forEach var="tempProduct" items="${cata}">--%>
            <%--<c:url var = "addToCart" value="/customer/mts">--%>
              <%--<c:param name="command" value="ADD" />--%>
              <%--<c:param name="id" value="${tempProduct.id}"/>--%>
            <%--</c:url>--%>
        <%--<tr>--%>
            <%--<td><b> ${tempProduct.name} </b> </td>--%>
          <%--<td> ${tempProduct.description} </td>--%>
          <%--<td> ${tempProduct.price} </td>--%>
          <%--<td> ${tempProduct.quantity} </td>--%>
          <%--<td><a role="button" href="${addToCart}" class="btn btn-default btn-block">add to cart</a></td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--<br>--%>
    <%--</table>--%>

  <%--</div>--%>
<%--</div>--%>



<div class="section group">
  <div class="content">
  <c:forEach var="tempProduct" items="${cata}">
    <c:url var = "addToCart" value="/customer/mts">
      <c:param name="command" value="ADD" />
      <c:param name="id" value="${tempProduct.id}"/>
    </c:url>
				<div class="grid_1_of_3 images_1_of_3">
					 <a href="preview.html"><img src="${root}/images/${tempProduct.id}.jpg" alt="" width="240" height="180"/></a>
                  <style>
                    p.clip {
                      white-space: nowrap; /* Запрещаем перенос строк */
                      overflow: hidden; /* Обрезаем все, что не помещается в область */
                      background: #fc0; /* Цвет фона */
                      padding: 5px; /* Поля вокруг текста */
                      text-overflow: ellipsis; /* Добавляем многоточие */
                    }
                    h2.clipH2 {
                      font-size: 12pt;
                      white-space: nowrap; /* Запрещаем перенос строк */
                      overflow: hidden; /* Обрезаем все, что не помещается в область */
                      background: #f4f1a8; /* Цвет фона */
                      padding: 5px; /* Поля вокруг текста */
                      text-overflow: ellipsis; /* Добавляем многоточие */
                    }
                  </style>
                     <h2 class="clipH2"> ${tempProduct.name} </h2>
                     <p class="clip">${tempProduct.description}</p>
					<div class="price-details">
				       <div class="price-number">
							<p><span class="rupees">${tempProduct.price} </span></p>
					    </div>
					       		<div class="add-cart">
									<h4><a href="${addToCart}">Add to Cart</a></h4>
							     </div>
							 <div class="clear"></div>
					</div>
					 
				</div>

				<%--<div class="grid_1_of_4 images_1_of_4">--%>
					<%--<a href="preview.html"><img src="${root}/images/feature-pic2.jpg" alt="" /></a>--%>
					 <%--<h2>Lorem Ipsum is simply </h2>--%>
					<%--<div class="price-details">--%>
				       <%--<div class="price-number">--%>
							<%--<p><span class="rupees">$899.75</span></p>--%>
					    <%--</div>--%>
					       		<%--<div class="add-cart">								--%>
									<%--<h4><a href="preview.html">Add to Cart</a></h4>--%>
							     <%--</div>--%>
							 <%--<div class="clear"></div>--%>
					<%--</div>--%>
				    <%----%>
				<%--</div>--%>
				<%--<div class="grid_1_of_4 images_1_of_4">--%>
					<%--<a href="preview.html"><img src="${root}/images/feature-pic3.jpg" alt="" /></a>--%>
					 <%--<h2>Lorem Ipsum is simply </h2>--%>
					 <%--<div class="price-details">--%>
				       <%--<div class="price-number">--%>
							<%--<p><span class="rupees">$599.00</span></p>--%>
					    <%--</div>--%>
					       		<%--<div class="add-cart">								--%>
									<%--<h4><a href="preview.html">Add to Cart</a></h4>--%>
							     <%--</div>--%>
							 <%--<div class="clear"></div>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="grid_1_of_4 images_1_of_4">--%>
					<%--<a href="preview.html"><img src="${root}/images/feature-pic4.jpg" alt="" /></a>--%>
					 <%--<h2>Lorem Ipsum is simply </h2>--%>
					<%--<div class="price-details">--%>
				       <%--<div class="price-number">--%>
							<%--<p><span class="rupees">$679.87</span></p>--%>
					    <%--</div>--%>
					       		<%--<div class="add-cart">								--%>
									<%--<h4><a href="preview.html">Add to Cart</a></h4>--%>
							     <%--</div>--%>
							 <%--<div class="clear"></div>--%>
					<%--</div>				     --%>
				<%--</div>--%>
  </c:forEach>
  </div>

</div>

</body>
</html>