<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Home</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body>
	<div id="wrap">
		<jsp:include page="header.jsp" /> <!-- End of #header -->

		<div class="clear"></div>

		<div id="container">
			<div id="page-404">
				<h1 class="error-404">error - 404</h1>
				<img alt="Welcome to London" src="images/404.png" width="200"/>
			</div>
		</div>
		<!-- End of #container -->

		<jsp:include page="footer.jsp" /> <!-- End of #footer -->
	</div> <!-- End of #wrap -->
</body>
</html>
