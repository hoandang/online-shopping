<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>New Products</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body class="product">
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
			<ul id="menu">
				<c:forEach var="category" items="${categories}">
					<li>
						<a href="shop?action=products&category=${category.categoryName}">
							${category.categoryName}
						</a>
					</li>
				</c:forEach>
			</ul> <!-- End of #menu -->

			<div id="content">
				<h1 id="caption">New products</h1>
				<ul id="products">
					<c:forEach var="product" items="${products}">
						<li class="product">
							<span class="name">${product.description}</span>
							<span class="code">(Code: ${product.productID})</span>
							<span class="price">$${product.price}</span>
							<a href="cart?action=add&productId=${product.productID}" class="button-style">Add to cart</a>
						</li> <!-- End of .product -->
					</c:forEach>
				</ul>
				<!-- End of #products -->
			</div>
			<!-- End of #content -->
		</div>
		<!-- End of #container -->

		<div class="clear"></div>

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div>
	<!-- End of #wrap -->
</body>
</html>
