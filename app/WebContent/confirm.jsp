<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<title>Confirm</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
			<form action="order?action=proceed" method="post" id="confirm">
				
				<h1>Your entered information</h1>
				
				<!-- Get information from customer -->				
				<p>
					<label>Title: </label> <span>${customer.title}</span>
				</p>
				<p>
					<label>Given Name: </label> <span>${customer.givenName}</span>
				</p>
				<p>
					<label>Surname Name: </label> <span>${customer.surname}</span>
				</p>
				<p>
					<label>Email: </label> <span>${customer.email}</span>
				</p>
				<p>
					<label>Address: </label> <span>${customer.address}</span>
				</p>
				<p>
					<label>Country: </label> 
					<span>${customer.country} ${customer.state} ${customer.postcode}</span>
				</p>
				<c:choose>
					<c:when test="${error == true}">
						<p class="error-box">
							<span class="msg">${invalidGivenName}</span>
							<span class="msg">${invalidSurname}</span>
							<span class="msg">${invalidAddress}</span>
							<span class="msg">${invalidEmail}</span>
							<span class="msg">${invalidCountry}</span>
							<span class="msg">${invalidState}</span>
							<span class="msg">${invalidPostCode}</span>
							<span class="msg">${invalidCreditNo}</span>
						</p>
						<input type="button" value="Edit" class="button-style" 
								onclick="window.history.back();"/>
					</c:when>
					<c:otherwise>
						<p>
							<input type="button" value="Edit" class="button-style" 
								onclick="window.history.back();"/>
							<input type="submit" id="submit" value="Proceed"
								class="button-style" />
						</p>
					</c:otherwise>
				</c:choose>
			</form> <!-- End of #checkout -->
		</div>
		<!-- End of #container -->

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div>
	<!-- End of #wrap -->

</body>
</html>