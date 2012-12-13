<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Order</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript">
function cancelCart() {
	var r = confirm("Are you sure you want to empty the cart!");
	if (r == true)
		window.location.href='cart?action=cancel'; 
	//<a href="shop?action=checkout"><span>Checkout</span></a> <!-- Checkout cart -->
}

function checkoutCart() {
	window.location.href='shop?action=checkout';
}
</script>
</head>
<body class="order">
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
		<form method="post" action="cart?action=update">
			<table id="cart">
				<caption>
					<span><img width="31px" height="23px" src="images/icon-cart.png" alt="Icon cart" /></span> 
					Your Cart
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
								<p>
									<input type="text" name="${product.productID}" value="${product.quantity}" /> <!-- READ THIS LINE and UpdateCart function -->
									<a title="Remove this item" class="remove_lnk" 
										href="cart?action=delete&productId=${product.productID}"> 
										<span></span>
									</a>
								</p>
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

					<tr id="process_cart">
						<td colspan="5">
							<input type="submit" value="Update" class="update"/>
							<input type="button" value="Cancel" id="cancel" class="cancel" onclick="cancelCart()"/> <!-- Cancel cart -->
							<input type="button" value="Checkout" class="checkout" onclick="checkoutCart()"/> <!-- Checkout cart -->
						</td>
					</tr> <!-- End of #process_cart -->
					
					<tr id="continue_shopping">
						<td colspan="5"><a href="shop?action=new-products">&lt;&lt;&lt; Continue to shopping</a></td>
					</tr>
				</tbody>
			</table> <!-- End of cart table -->
			</form>
		</div>
		<!-- End of #container -->

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div>
	<!-- End of #wrap -->
</body>
</html>