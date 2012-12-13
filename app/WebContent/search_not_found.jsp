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
			
			<center>
				<h1 class="error">No order matching</h1>
			</center>
		</div>
		<!-- End of #container -->

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div>
	<!-- End of #wrap -->

</body>
</html>