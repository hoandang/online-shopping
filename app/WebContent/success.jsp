<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<title>Successful Page</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
			<div id="success_page">
				<h1>Your order has been successfully placed</h1>
				<h2>Your order number: ${orderId}</h2>
				<p><a href="shop?action=new-products">&lt;&lt;&lt; Continue to shopping</a></p>
			</div>
		</div>
		<!-- End of #container -->

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div> <!-- End of #wrap -->
</body>
</html>