<!-- 	pageEncoding="windows-1255"%> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html lang="en">
<head>
<title>Travel Organizer</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">

<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="./FileOne.css?Math.floor(Date.now() / 1000)">
</head>


<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">
	<c:if test="${empty ti}"><jsp:forward
			page="/CSC?myaction=listOfusers&init=true"></jsp:forward></c:if>
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
		<form class="form-inline" action="CSC" method="post">
			<div class="input-group">
				<!-- <input type="text" class="form-control" size="50" placeholder="your name" required> -->

				<select class="form-control" id='trvlr' name='traveller'>
					<option value="-1">Select traveler</option>
					<c:forEach items="${user}" var="trs">
						<option value="<c:out value="${trs.idtraveler}" />"
							<c:if test="${trs.idtraveler eq ti.ind}"><c:out value=" selected"/></c:if>><c:out
								value="${trs}" />
						</option>
					</c:forEach>
				</select>
				<div class="input-group-btn">
					<button type="submit" class="btn btn-danger" name="action" value="usr">Submit user</button>
				</div>
			</div>
		</form>
	</div>

	<!-- Container (About Section) -->
	<div class="container-fluid">
		<div class="row slideanim">
			<div class="col-sm-4">
				<h2>About This Site</h2>
				<h4>Here are my Travels</h4>
				<p>And More Should Come</p>
				<button class="btn btn-default btn-lg">Edit a Trip</button>
			</div>
			<div class="col-sm-4">
			<form action="CSC" method="post">"${ti.ind ne 1 ? ti.ind : '' }"
				<label for="removetrvl">remove</label> <input type="hidden" id="removetrvl" name="userid" value=   "${ti.ind ne 1 ? ti.ind : '' }"   >
				<h3><c:if test="${ti.ind eq 1}">can't remove </c:if>${ti.tr}</h3><c:if test="${ti.ind ne 1}">
				<button class="btn btn btn-warning btn-lg" name="action" type="submit" value="rmvusr">remove</button></c:if>
				</form>
			</div>
			<div class="col-sm-4">
				<span class="glyphicon glyphicon-signal logo"></span>
			</div>
		</div>
	</div>

	<div id="about" class="container-fluid bg-grey">
		<div class="row slideanim">
			<div class="col-sm-4">
				<span class="glyphicon glyphicon-globe logo"></span>
			</div>
			<div class="col-sm-4">
				<h2>My Trips</h2>
				<h4>
					<strong>Last added city</strong> from city list
				</h4>
				<p>
					<strong>Chosen trip</strong> Randomly from best
				</p>
			</div>
			<div class="col-sm-4">
				<label for="newcity">new city</label> <input type="text"
					class="form-control" id="newcity" name="newcity"
					placeholder="add new city">
				<button class="btn btn-default btn-lg">Add a City</button>

			</div>
			<p>
				<!-- <button class="btn btn-default btn-lg">Add a City</button> -->
			</p>
		</div>
	</div>
	<form action="CSC" method="post">
		<div id="services" class="container-fluid text-center">
			<h2>SERVICES</h2>
			<h4>add a trip</h4>
			<br>

			<div class="row slideanim">
				<div class="col-sm-4">
					<span class="glyphicon glyphicon-tower logo-small"></span>
					<h4>City</h4>

					<label for="Inputselect">city</label> <select class="form-control"
						id="city" name="city" required>
						<option value="-1">Select city</option>
						<c:forEach items="${cities}" var="citys">
							<option value="${citys.idcities}">
		${citys.cityName}
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-4">
					<span class="glyphicon glyphicon-glass logo-small"></span>
					<h4>hotel</h4>
					<label for="Name2">hotel</label> <input type="text"
						class="form-control" id="hotel" name="hotel"
						placeholder="hotel name" required>

				</div>
				<div class="col-sm-4">
					<span class="glyphicon glyphicon-euro logo-small"></span>
					<h4>budget</h4>
					<label for="Name2">budget</label> <input type="text"
						class="form-control" id="budget" name="budget"
						placeholder="enter money spent" required>

				</div>
			</div>
			<br> <br>
			<div class="row slideanim">
				<div class="col-sm-4">
					<span class="glyphicon glyphicon-time logo-small"></span>
					<h4>date</h4>
					<label for="Name2">date</label> <input type="text"
						class="form-control" id="date" name="date"
						placeholder="dd-mm-yyyy" required>

				</div>
				<div class="col-sm-4">
					<span class="glyphicon glyphicon-calendar logo-small"></span>
					<h4>duration</h4>
					<label for="Name2">number of days</label> <input type="text"
						class="form-control" id="duration" name="duration"
						placeholder="number of days" required>

				</div>
				<div class="col-sm-4">
					<span class="glyphicon glyphicon-road logo-small"></span>
					<h4>transit time</h4>
					<label for="Name2">transit time</label> <input type="text"
						class="form-control" id="transit" name="transit"
						placeholder="enter transit time" required>

				</div>

			</div>
		</div>
		<input type="hidden" name="user" value="${ti.ind}">
		<div class="container-fluid text-center">
			<button type="submit" class="btn btn-lg btn-success" name="action"
				value="createt">Submit Information</button>
		</div>
	</form>
	<c:if test="${not empty lpc }">
		<div id="portfolio" class="container-fluid text-center bg-grey">
			<h2>Portfolio</h2>
			<h4>Selected Sightseeings</h4>

			<div class="row text-center slideanim">
				<c:forEach var="current" items="${lpc}">
					<div class="col-sm-4">
						<div class="thumbnail">
							<img src="<c:out value="${current.picsAddr}" />" width="400"
								height="300">
							<p>
								<strong><a
									href="CExpCon?city=<c:out
									value="${current.tripSightseeing.sightseeing.city.idcities}" />"><c:out
											value="${current.tripSightseeing.sightseeing.city}" /></a></strong>
							</p>
							<p>
								<c:out value="${current.tripSightseeing.sightseeing}" />
							</p>
						</div>
					</div>
				</c:forEach>
			</div>

			<h2>Selected Trips</h2>
			<div id="myCarousel" class="carousel slide text-center slideanim"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${tu}" var="current" varStatus="loop">
						<div class="item ${loop.first ? 'active' : '' }">
							<h4>
								<c:out value="${current}" />
								<br> <span style="font-style: normal;"><a
									href="CExpCon?city=<c:out value="${current.city.idcities}"/>">to
										city <c:out value="${current.city}" />
								</a></span>
							</h4>
						</div>
					</c:forEach>
				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			<div id="pricing" class="container-fluid slideanim">
				<div class="text-center">
					<h2>3 Chosen Cities</h2>
					<h4>Trips From 3 Chosen Cities</h4>
				</div>
				<div class="row slideanim">
					<div class="col-sm-4">

						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<h1>
									<c:out value="${c1.cname}" />
								</h1>
							</div>
							<div class="panel-body">
								<c:forEach items="${c1.travels}" var="current">
									<p>
										<strong><c:out value="${current.tripDate}" /></strong> for
										<c:out value="${current.tripNumdays}" />
										days
									</p>
								</c:forEach>
							</div>
							<div class="panel-footer">
								<h3>num</h3>
								<h4>days total</h4>
								<form action="CExpCon" method="get">
									<button class="btn btn-lg" type="submit" name="city"
										value="<c:out value="${c1.cind}" />">explore</button>
								</form>
							</div>
						</div>

					</div>
					<div class="col-sm-4">

						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<h1>
									<c:out value="${c2.cname}" />
								</h1>
							</div>
							<div class="panel-body">
								<c:forEach items="${c2.travels}" var="current">
									<p>
										<strong><c:out value="${current.tripDate}" /></strong> for
										<c:out value="${current.tripNumdays}" />
										days
									</p>
								</c:forEach>
							</div>
							<div class="panel-footer">
								<h3>num</h3>
								<h4>days total</h4>
								<form action="CExpCon" method="get">
									<button class="btn btn-lg" type="submit" name="city"
										value="<c:out value="${c2.cind}" />">explore</button>
								</form>
							</div>
						</div>

					</div>
					<div class="col-sm-4">

						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<h1>
									<c:out value="${c3.cname}" />
								</h1>
							</div>
							<div class="panel-body">
								<c:forEach items="${c3.travels}" var="current">
									<p>
										<strong><c:out value="${current.tripDate}" /></strong> for
										<c:out value="${current.tripNumdays}" />
										days
									</p>
								</c:forEach>
							</div>
							<div class="panel-footer">
								<h3>num</h3>
								<h4>days total</h4>
								<form action="CExpCon" method="get">
									<button class="btn btn-lg" type="submit" name="city"
										value="<c:out value="${c3.cind}" />">explore</button>
								</form>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div id="contact" class="container-fluid bg-grey slideanim">
		<h2 class="text-center">Create new user</h2>
		<div class="row ">
			<div class="col-sm-5">
				<p>The default ours place and contact:</p>
				<p>
					<span class="glyphicon glyphicon-map-marker"></span> Be'er-Sheva,
					Israel
				</p>
				<p>
					<span class="glyphicon glyphicon-phone"></span> +00 1515151515
				</p>
				<p>
					<span class="glyphicon glyphicon-envelope"></span>
					myemail@something.com
				</p>
			</div>
			<form action="CSC" method="post">
				<div class="col-sm-7">
					<div class="row ">
						<div class="col-sm-6 form-group">
							<input class="form-control" id="name" name="name"
								placeholder="Name" type="text" required>
						</div>
						<div class="col-sm-6 form-group">
							<input class="form-control" id="date" name="birth_d"
								placeholder="dd-mm-yyyy" type="text" required>
						</div>
					</div>
					<textarea class="form-control" id="comments" name="comments"
						placeholder="Comment, maybe be ignored meanwhile" rows="1"></textarea>
					<br>
					<div class="row ">
						<div class="col-sm-12 form-group">
							<button class="btn btn-default pull-right" type="submit"
								name="action" value="newuser">create</button>
						</div>
					</div>
				</div>

			</form>
		</div>
	</div>
	<!-- Add Google Maps -->
	<div id="googleMap" style="height: 400px; width: 100%;"></div>
	<script>
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