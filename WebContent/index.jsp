
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
<meta charset="ISO-8859-1">
<link rel="shortcut icon" type="image/png" href="favicon.png"/>
<title>Food Avenue</title>
<link rel="stylesheet" href="CSS/Style.css">
</head>
<body
	style="background-image: url(img/index_bg.jpg); background-size: cover">

	<div style="padding: 40px">
	
		<span> <a href="index.jsp"><img src="img/logo.png"
				height="100" width="200"></a>
				

				<c:choose>
				<c:when test="${sessionScope.email==null}">
				<c:import var ="notlogin" url="res/notLogin.html"/>
			<c:out value="${notlogin}" escapeXml="false"/>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${sessionScope.type=='customer'}">
			<div class="dropdown pull-right" style="display: inline">
			<div style="color:white; margin-top: 40px">
							<h4>Hello, &nbsp;<c:out value="${sessionScope.fname}" escapeXml="false"/>!</h4>
							</div>
			<c:import var ="afterloginCust" url="res/afterLoginCust.html"/>
			<c:out value="${afterloginCust}" escapeXml="false"/>
			
			</div> 
			</c:when>
			<c:when test="${sessionScope.type=='restaurant'}">
			<div class="dropdown pull-right" style="display: inline">
			<div style="color:white; margin-top: 40px">
							<h4>Hello, &nbsp;<c:out value="${sessionScope.fname}" escapeXml="false"/>!</h4>
							</div>
			<c:import var ="afterloginRest" url="res/afterLoginRest.html"/>
			<c:out value="${afterloginRest}" escapeXml="false"/>
			</div> 
			</c:when>
			</c:choose>
			</c:otherwise>
			</c:choose>
		</span>
		
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br>
			<div>
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-sm-push-3 text-center"
							style="background-color: rgba(255, 255, 255, 0.4); border-radius: 20px; border: 2px solid white; color: white">
							<h1>A Better Way to Order Your Food</h1>
							<br>
							<form name="searchForm" action="Search"
								>

								<div class="input-group">
									<input name="search" type="search" class="form-control input-lg"
										placeholder="Search your restaurant..."
										style="border-radius: 8px"> <span
										class="input-group-btn input-space">
										<button type="submit" class="btn btn-lg btn-primary green"
											style="border-radius: 8px">
											<i class="glyphicon glyphicon-search"></i> Search
										</button>
									</span>
								</div>
							</form>
							<br> <br>
						</div>

					</div>
				</div>
			
		</div>
	</div>
	

</body>
</html>