<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<title>Search order</title>
</head>
<body class="search">
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
			
			<form id="search_form" method="post" action="order?action=search">
				<h1>Search your order</h1>
				<label>Order Number: </label><input type="text" name="orderNo" id="orderNo"/>
				<label>Your surname: </label><input type="text" name="surname" />
				<input type="submit" value="Search" class="button-style" />
			</form>
			
			<table id="cart">
				<caption>
					<b>Order number: ${orderID}</b>
					<b>Status: ${order.status}</b>
				</caption>
				<thead>
					<tr id="header">
						<th>Category</th>
						<th>Code</th>
						<th>Description</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody>
					<!-- Init the sub total cart -->
					<c:set var="subTotal" value = "0" />
					<!--  start looping the cart to iterate the each product -->
					<c:forEach var="product" items="${products}">
						<!-- total price the each product -->
						<c:set var="total" value ="${product.quantity * product.price}" />
						<!-- sub total for whole cart -->
						<c:set var="subTotal" value = "${subTotal + total}" />
						<tr class="content_cart">
							<td>${product.category.categoryName}</td>
							<td>${product.productID}</td>
							<td>${product.description}</td>
							<td class="quantity">
								<input type="text" name="${product.productID}" value="${product.quantity}" maxlength="3" disabled="disabled"/> 
							</td>
							<td class="price">
								<span>$${total}</span>
								<p>$${product.price} each</p>
							</td>
						</tr> <!-- End of .content_cart -->
					</c:forEach>
					<tr id="subtotal">
						<td colspan="5">Subtotal: <span id="sum">$${subTotal}</span></td>
					</tr> <!-- End of #subtotal -->

				</tbody>
			</table> 
			
		</div>
		<!-- End of #container -->

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div>
	<!-- End of #wrap -->

</body>
</html>