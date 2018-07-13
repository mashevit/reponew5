<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<title>Travel Organizer</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">

<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="./css/FileOne.css">





</head>


<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">AllTrip</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#about">ABOUT</a></li>
					<li><a href="#services">SERVICES</a></li>
					<li><a href="#portfolio">PORTFOLIO</a></li>
					<li><a href="#pricing">PRICING</a></li>
					<li><a href="#contact">CONTACT</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="jumbotron text-center">
		<h1>Travels</h1>
		<p>Travel And not Forget</p>
	</div>

	<!-- Container (About Section) -->


	<div id="about" class="container-fluid bg-grey">
		<div class="row slideanim">
			<div class="col-sm-4">
				<span class="glyphicon glyphicon-globe logo"></span>
			</div>
			<div class="col-sm-8">
				<h2>City</h2>
				<h4>
					<strong>All who travelled </strong> here
				</h4>
				<p>
					<strong>Traveled </strong>and remembered
				</p>
			</div>
		</div>
	</div>

		<c:forEach items="${tarrl}" var="current">


			<div id="portfolio" class="container-fluid text-center bg-grey">

				<h2>
					Trip on the
					<c:out value="${current.tripdate}" />
				</h2>
				<c:if test="${not empty current.piclist}">
					<div class="container">
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							<!-- Indicators -->
							<ol class="carousel-indicators">
								<c:forEach begin="0" end="${current.sizel -1}" varStatus="loop">
									<li data-target="#myCarousel"
										data-slide-to="<c:out value="${loop.index}" />"
										${loop.first ? 'class="active"' : ''}></li>

								</c:forEach>
							</ol>


							<!-- Wrapper for slides -->

							<div class="carousel-inner" role="listbox">
								<c:forEach items="${current.piclist}" var="listp"
									varStatus="status">
									<div class="item ${status.first ? 'active' : '' }">
										<img src="<c:out value="${listp.picsAddr}" />" alt="trip pic"
											width="400" height="300" style="width: 100%;" />
									</div>
								</c:forEach>
							</div>



							<!-- Left and right controls -->
							<a class="left carousel-control" href="#myCarousel" role="button"
								data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a> <a class="right carousel-control" href="#myCarousel"
								role="button" data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</c:if>	
			</div>

		</c:forEach>
	<!-- 	<script>
		function myMap() {
			var myCenter = new google.maps.LatLng(31.252973, 34.791462);
			var mapProp = {
				center : myCenter,
				zoom : 14,
				scrollwheel : false,
				draggable : false,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("googleMap"),
					mapProp);
			var marker = new google.maps.Marker({
				position : myCenter
			});
			marker.setMap(map);
			
		}
	</script> 
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwHq23WcDuRdLYKkxKC-fqeGB4LWtlQD8&callback=myMap"></script>
	-->
	<footer class="container-fluid text-center">
		<a href="#myPage" title="To Top"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a>
		<p>
			Bootstrap Theme Made By <a href="https://www.w3schools.com"
				title="Visit w3schools">www.w3schools.com</a>
		</p>
	</footer>
	<script>
		$(document).ready(
				function() {
					// Add smooth scrolling to all links in navbar + footer link
					$(".navbar a, footer a[href='#myPage']").on('click',
							function(event) {
								// Make sure this.hash has a value before overriding default behavior
								if (this.hash !== "") {
									// Prevent default anchor click behavior
									event.preventDefault();

									// Store hash
									var hash = this.hash;

									// Using jQuery's animate() method to add smooth page scroll
									// The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
									$('html, body').animate({
										scrollTop : $(hash).offset().top
									}, 900, function() {

										// Add hash (#) to URL when done scrolling (default click behavior)
										window.location.hash = hash;
									});
								} // End if
							});

					$(window).scroll(function() {
						$(".slideanim").each(function() {
							var pos = $(this).offset().top;

							var winTop = $(window).scrollTop();
							if (pos < winTop + 600) {
								$(this).addClass("slide");
							}
						});
					});
				})
	</script>
</body>
</html>