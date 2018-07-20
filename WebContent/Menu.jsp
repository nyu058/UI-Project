<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="data.Menu"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" src="add+-.js"></script>
<title>Menu</title>
<link rel="shortcut icon" type="image/png" href="favicon.png"/>
<link rel="stylesheet" href="CSS/Style.css">
<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
%>
</head>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect("index.jsp");
	}
	Connect db = new Connect();
	db.openConnection();
	Menu menuObj = new Menu();
%>
<body>
	<div style="background-color: #FFCC00; padding: 40px">
		<span> <a href="index.jsp"><img src="img/logo.png"
				height="100" width="200"></a>
			<div class="dropdown pull-right" style="display: inline">
				<div style="color: white; margin-top: 40px">
					<h4>
						Hello, &nbsp;
						<c:out value="${sessionScope.fname}" escapeXml="false" />
						!
					</h4>
				</div>


				<button type="button"
					class="btn btn-lg btn-primary orange dropdown-toggle"
					id="loginDropdown" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false" style="float: right">
					<b>My Account&nbsp;<i
						class="glyphicon glyphicon-triangle-bottom"></i></b>
				</button>
				<ul class="dropdown-menu ">

					<li><a href="#" class="dropdown-item">My Profile</a></li>
					<li><a href="ordersCust.jsp" class="dropdown-item">Past
							Orders</a></li>
					<form action="Logout" method="get">
						<div class="col-xs-12">
							<input type="submit" class="btn btn-danger full-width"
								value="Logout" style="margin-top: 10px">
						</div>
					</form>
				</ul>
			</div>
		</span>
	</div>
	<ul class="menunav">
	
		<li><a href="#Appetizers"><b>Appetizer</b></a></li>
		<li><a href="#Main"><b>Main</b></a></li>
		<li><a href="#dessert"><b>Dessert</b></a></li>
<form action="orderSuccess.jsp">
		<button type="submit" id="checkout" class="btn btn-primary green "
			onclick=" down('0')"
			style="float: right; margin-right: 20px; margin-left: 20px; margin-top:10px  ">
			<i class="glyphicon glyphicon-ok"></i>&nbsp;Confirm Order
		</button>
</form>
		<h3 style="float: right;">
			Total: &nbsp;$ <span id="dish_sun">0.00</span>

		</h3>
		<input class="form-control"
			style="display: inline; width: 60px; margin-right: 20px; margin-left: 10px; margin-top: 10px; float: right"
			type="number" min="1" value="1" required/>
		<h3 style="float: right;">Table Number:</h3>
	</ul>
	<div class="container">
		<div class="row">
			<div
				class="col-xs-12 col-sm-12  col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
				<div id="Appetizers">
					<table class="table">
						<h2>Appetizer</h2>
						<%=menuObj.getAppetizers(request.getParameter("id"), db)%>
					</table>
				</div>

				<div id="Main">
					<table class="table">
						<h2>Main</h2>
						<%=menuObj.getMains(request.getParameter("id"), db)%>
					</table>
				</div>


				<div id="dessert">
					<table class="table">
						<h2>Dessert</h2>
						<%=menuObj.getDesserts(request.getParameter("id"), db)%>


					</table>
				</div>


			</div>
		</div>
	</div>






</body>


</html>