<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Order</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body class="order">
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
		<form name="submitForm" method="post" action="update">
			<table id="cart">
				<caption>
					<span><img width="31px" height="23px" src="images/icon-cart.png" alt="Icon cart" /></span> 
					Your Cart
				</caption>
				<tbody>
					<tr id="empty_cart">
						<td colspan="4">
							<h3>Your cart is empty</h3>
						</td>
					</tr>
					<tr id="continue_shopping">
						<td colspan="4"><a href="shop?action=new-products">&lt;&lt;&lt; Continue to shopping</a></td>
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