<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page session="false"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Stock Market Prediction System</title>

<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<link href="<c:url value="/resources/dashboard/css/layout.css" />"
	rel="stylesheet">
<script
	src="<c:url value="/resources/dashboard/js/jquery-1.5.2.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/dashboard/js/hideshow.js" />"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/dashboard/js/jquery.equalHeight.js" />"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/dashboard/js/jquery.tablesorter.min.js" />"
	type="text/javascript"></script>
<!-- adding market on demand -->

<script
	src="<c:url value="/resources/dashboard/js/markitondemand/MarkitQuoteServiceSample.js" />"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/dashboard/js/markitondemand/MarkitTimeseriesServiceSample.js" />"
	type="text/javascript"></script>


<script type="text/javascript">
	$(document).ready(function() {
		//alert("Hi");
		$(".tablesorter").tablesorter();

	});

	$(document).ready(function() {
		//When page loads...
		$(".tab_content").hide(); //Hide all content
		$("ul.tabs li:first").addClass("active").show(); //Activate first tab
		$(".tab_content:first").show(); //Show first tab content

		//On Click Event
		$("ul.tabs li").click(function() {

			$("ul.tabs li").removeClass("active"); //Remove any "active" class
			$(this).addClass("active"); //Add "active" class to selected tab
			$(".tab_content").hide(); //Hide all tab content

			var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
			$(activeTab).fadeIn(); //Fade in the active ID content
			return false;
		});
		
		$("#registerFormId").hide();
		//$("#loginFormId").hide();
		$("#addUserId").click(function() {
			$("#registerFormId").fadeIn('slow');
			$("#loginFormId").fadeOut('slow');
		});
		
		$("#loginId").click(function() { 
			$("#registerFormId").fadeOut('slow');
			$("#loginFormId").fadeIn('slow');
		});
	});
</script>
<script type="text/javascript">
	$(function() {
		$('.column').equalHeight();
	});
</script>

</head>
<body>
	<header id="header">
		<hgroup>
			<h1 class="site_title">
				
			</h1>
			<h2 class="section_title">Stock Market Prediction System</h2>
			<div class="btn_view_site">
				<a href="#">View Site</a>
			</div>
		</hgroup>
	</header>
	<!-- end of header bar -->

	<section id="secondary_bar">
		<div class="user">
			<p>No User</p>
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs">
				<a href="home">Home</a>
				<div class="breadcrumb_divider"></div>
				<a href="#">News</a>
				<div class="breadcrumb_divider"></div>
				<a href="#">FAQ</a>
			</article>
		</div>
	</section>
	<!-- end of secondary bar -->
	<aside id="sidebar" class="column">
		<hr />
		<h3>Latest RSS Feed</h3>
		<footer>
			<hr />
			<p>
				<strong>Copyright &copy; Sai Stock Project</strong>
			</p>
		</footer>
	</aside>
	<!-- end of sidebar -->
	<section id="main" class="column">
			<c:if test="${ERROR_KEY !=null}">
				<h4 class="alert_error">${ERROR_KEY}</h4>
			</c:if>
			<c:if test="${SUCCESS_KEY !=null}">
				<h4 class="alert_success">${SUCCESS_KEY}</h4>
			</c:if>
			
			
		<article class="module width_3_quarter">
			<img alt="Stock Market Image" src="${pageContext.request.contextPath}/resources/dashboard/images/Stock-Market-Guide.jpeg"/>
		</article>
		<article class="module width_quarter" id="loginFormId">
			<header>
				<h3>Login Form</h3>
			</header>

			<div class="module_content">
				<form action="login" class="post_message" method="post">
					<input id="emailAddrId" type="text" placeholder="Enter Your Email"
						name="emailAddr"> <br> <br> <input
						id="userPwdId" type="text" placeholder="Enter Password"
						name="userPassword"> <br> <br>
					<div align="center">
						<input type="submit" value="Login" style="align: right">
					</div>
				</form>

				<a href="#">Forgot Password</a> &nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; <a href="#" id="addUserId">Register</a>
			</div>
			</article>
			<article class="module width_quarter" id="registerFormId">
				<header>
					<h3>Register Me</h3>
				</header>
				<div class="module_content" >
					<form action="addUser" class="post_message" method="post">
						<input id="fnameId" type="text" placeholder="First Name"
							name="firstName"> <br> <br>
						<input id="lnameId" type="text" placeholder="Last Name"
							name="LastName"> <br> <br>	
					 <input id="dobId" type="text" placeholder="Date of Birth"
							name="dob"> <br> <br>
						 <input id="pwdId" type="password"
							placeholder="Enter Password" name="password"> <br> <br>
						<input id="rePwdId" type="password" placeholder="Re-Enter Password"
							name="repassword"> <br> <br>
						<input id="emailId"
							type="text" placeholder="Enter Your Email" name="email">
						<br> <br>
						<input id="reemailId"
							type="password" placeholder="Re-Enter Your Email" name="reemail">
						<br> <br>
						<div align="center">
							<input type="submit" value="Register" style="align: right"> 
						</div>
					</form>

 				<a href="#" id="loginId">Login</a>
				</div>
			</article>

			<!-- end of stats article -->

			<!-- end of messages article -->

			<div class="clear"></div>

			<!-- end of post new article -->

			<!-- end of styles article -->
			<div class="spacer"></div>
	</section>

</body>

</html>
