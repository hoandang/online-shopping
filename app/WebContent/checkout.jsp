<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<title>Checkout</title>
</head>
<body class="order">
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
			<form action="order?action=confirm" method="post" id="checkout">
				<h1>Enter your information</h1>
				<p>
					<label>Title: </label> <select name="title">
						<option value="mr" selected="selected">Mr</option>
						<option value="mrs">Mrs</option>
						<option value="ms">Ms</option>
					</select>
				</p>
				<p>
					<label>Given Name: </label> <input type="text" name="givenName" value="Hoan" />
				</p>
				<p>
					<label>Surname Name: </label> <input type="text" name="surname" value="Dang"/>
				</p>
				<p>
					<label>Email: </label> <input type="text" name="email" value="danghuyhoan@gmail.com"/>
				</p>
				<p>
					<label>Address: </label> <input type="text" name="address" value="420 Pitt Street"/>
				</p>
				<p>
					<label>Country: </label> <input type="text" name="country" value="Australia" />
				</p>
				<p>
					<label>State: </label> <input type="text" name="state" value="NSW"/>
				</p>
				<p>
					<label>Postcode: </label> <input type="text" name="postcode" value="2000"/>
				</p>
				<p>
					<label>Payment details: </label> <input type="text" name="creditNo" value="12345"/>
				</p>
				<p>
					<input type="submit" id="submit" value="Purchase" class="button-style" />
				</p>
			</form>
			<!-- End of #checkout -->
		</div>
		<!-- End of #container -->

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div>
	<!-- End of #wrap -->

</body>
</html>