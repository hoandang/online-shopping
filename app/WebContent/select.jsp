<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript">

function setSelectedIndex(status) {
	var v = status;
	var obj = document.getElementById("status");
    for ( var i = 0; i < obj.options.length; i++ ) {
        if ( obj.options[i].value == v.toLowerCase()) {
            obj.options[i].selected = true;
            return;
        }
    }
}
</script>
<title>Admin</title>
</head>
<body onload="setSelectedIndex('${order.status}')">
	<div id="wrap">
		<div id="container">
		<ul>
			<li><a href="admin">View Orders</a></li>
		</ul>
		<br/> <br/>
		<div id="customer-detail">
			<p><span class="bold">Order Number</span>: ${orderID}</p>
			<p><span class="bold">Name</span>: ${customer.givenName} ${customer.surname}</p>
			<p><span class="bold">Email</span>: ${customer.email}</p>
			<p><span class="bold">Address</span>: ${customer.address} ${customer.state} ${customer.postcode} ${customer.country}</p>
			<p><span class="bold">Payment Detail</span>: ${customer.creditNo}</p>
			<form method="post" action="admin?action=update">
				<p> 
					<span class="bold">Order Status</span>: 
					<select name="status" id="status">
						<option value="paid">PAID</option>
						<option value="sent">SENT</option>
						<option value="ordered">ORDERED</option>
					</select>
					<input type="hidden" name="orderId" value="${order.orderID}" />
					<input type="hidden" name="customerId" value="${customer.customerID}" />
					<input type="submit" value="Update" class="button-style"/>
				</p>
			</form>
			<span class="msg">${msg}</span>
		</div>
	
		<table id="cart">
			<thead>
				<tr id="header">
					<th>Category</th>
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
							<td>${product.description}</td>
							<td class="quantity">
								<input class="center" type="text" name="${product.productID}" disabled="disabled" value="${product.quantity}" maxlength="3" /> 
							</td>
							<td class="price">
								<span>$${total}</span>
								<p>$${product.price} each</p>
							</td>
						</tr> <!-- End of .content_cart -->
					</c:forEach>
					<tr id="subtotal">
						<td colspan="4">Subtotal: <span id="sum">$${subTotal}</span></td>
					</tr> <!-- End of #subtotal -->
			</tbody>
		</table>
		</div>
		
		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div>
</body>
</html>
