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

		$("#portfolioFormId").hide();
		$("#addPortfolioId").click(function() {
			$("#portfolioFormId").toggle('slow');
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
				<a href="index.html">Stock Admin</a>
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
			<p>
				${userInfo.firstName}(<a href="#">5 Stocks</a>)
			</p>
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs">
				<a href="dashboard">Home</a>
				<div class="breadcrumb_divider"></div>
				<a href="portfolio">My Portfolio</a>
				<div class="breadcrumb_divider"></div>
				<a href="#">News</a>
				<div class="breadcrumb_divider"></div>
				<a href="#">FAQ</a>
			</article>
		</div>
	</section>
	<!-- end of secondary bar -->
	<aside id="sidebar" class="column">
		<form class="quick_search" action="searchStocks" method="post">
			<input name="searchCriteria" type="text" id="stockSearchId"
				class="input- ui-autocomplete-input"
				placeholder="Enter company name or symbol" />
		</form>
		<hr/>
		<h3>Manage Portfolios</h3>
		<div id="portfolioFormId">
			<form class="post_message" action="addPortfolio" method="post">
				<input type="text" name="pfName"
					placeholder="Enter a Portfolio Name" />
				<div align="center">
					<input type="submit" value="Add Portfolio" />
				</div>
			</form>
		</div>
		<ul class="toggle">
			<li id="addPortfolioId" class="icn_new_article"><a href="#">Add
					New</a></li>
			<li class="icn_edit_article"><a href="#">Edit </a></li>
			<li class="icn_categories"><a href="#">View All</a></li>
			<li class="icn_tags"><a href="#">Tags</a></li>
		</ul>
		<h3>Users</h3>
		<ul class="toggle">
			<li class="icn_add_user"><a href="#">Add New User</a></li>
			<li class="icn_view_users"><a href="#">View Users</a></li>
			<li class="icn_profile"><a href="#">Your Profile</a></li>
		</ul>
		<h3>Admin</h3>
		<ul class="toggle">
			<li class="icn_settings"><a href="#">Options</a></li>
			<li class="icn_security"><a href="#">Security</a></li>
			<li class="icn_jump_back"><a href="#">Logout</a></li>
		</ul>
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
			<header>
				<h3 class="tabs_involved">Portfolio</h3>
				<ul class="tabs">
					<li><a href="#tab1">Current</a></li>
					<li><a href="#tab2">History</a></li>
				</ul>
			</header>
			<div class="tab_container">
				<div id="tab1" class="tab_content">
					<table class="tablesorter" cellspacing="0">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Total Stocks</th>
								<th>Total Value</th>
								<th>Loss/Gain</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox"></td>
								<td>CP</td>
								<td>Canadian Pacific Railway Limited</td>
								<td>5th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"> <input type="image"
									src="images/icn_trash.png" title="Sell"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>COST</td>
								<td>Costco Wholesale Corporation</td>
								<td>6th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>COKE</td>
								<td>Coca-Cola Bottling Co. Consolidated</td>
								<td>10th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>CAT</td>
								<td>Caterpillar, Inc.</td>
								<td>16th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>CBF</td>
								<td>Capital Bank Financial Corp.</td>
								<td>16th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- end of #tab1 -->

				<div id="tab2" class="tab_content">
					<table class="tablesorter" cellspacing="0">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Total Stocks</th>
								<th>Total Value</th>
								<th>Loss/Gain</th>

							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox"></td>
								<td>${stock.code}</td>
								<td>${stock.name}</td>
								<td>${stock.description}</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"> <input type="image"
									src="images/icn_trash.png" title="Sell"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>COST</td>
								<td>Costco Wholesale Corporation</td>
								<td>6th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>COKE</td>
								<td>Coca-Cola Bottling Co. Consolidated</td>
								<td>10th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>CAT</td>
								<td>Caterpillar, Inc.</td>
								<td>16th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>CBF</td>
								<td>Capital Bank Financial Corp.</td>
								<td>16th April 2015</td>
								<td><input type="image" src="images/icn_edit.png"
									title="Edit"><input type="image"
									src="images/icn_trash.png" title="Trash"></td>
							</tr>
						</tbody>
					</table>

				</div>
				<!-- end of #tab2 -->

			</div>
			<!-- end of .tab_container -->

		</article>
		<article class="module width_quarter">
			<header>
				<h3>RSS Feed</h3>
			</header>
			<div class="message_list">
				<div class="module_content">
					<c:forEach items="${items}" var="item">
						<div class="message">
							<p>
								<a href="${item.link}" target="_blank">${item.title}</a><br>
							</p>
							<p>
								<strong>${item.publishedDate}</strong>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>
			<footer>
				<form class="post_message">
					<input type="text" value="Message"
						onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
					<input type="submit" class="btn_post_message" value=""
						placeholder="Search RSS Feed" />
				</form>
			</footer>
		</article>
		<!-- end of stats article -->

		<article class="module width_3_quarter">
			<header>
				<h3 class="tabs_involved">My Stock Trends</h3>
				<ul class="tabs">
					<li><a href="#tab3">Current</a></li>
					<li><a href="#tab4">History</a></li>
				</ul>
			</header>

			<div class="tab_container">
				<div id="tab3" class="tab_content"></div>
				<!-- end of #tab1 -->

				<div id="tab4" class="tab_content"></div>
				<!-- end of #tab2 -->

			</div>
			<!-- end of .tab_container -->

		</article>
		<!-- end of content manager article -->

		<article class="module width_quarter">
			<header>
				<h3>Chart</h3>
			</header>
			<div class="message_list">
				<div class="module_content">
					<!-- TradingView Widget BEGIN -->
					<script type="text/javascript"
						src="https://d33t3vvu2t2yu5.cloudfront.net/tv.js"></script>
					<script type="text/javascript">
						new TradingView.widget({
							"width" : 300,
							"height" : 250,
							"symbol" : "FX:SPX500",
							"interval" : "D",
							"timezone" : "Etc/UTC",
							"theme" : "White",
							"style" : "1",
							"locale" : "en",
							"toolbar_bg" : "#f1f3f6",
							"hide_top_toolbar" : true,
							"save_image" : false,
							"hideideas" : true
						});
					</script>
					<!-- TradingView Widget END -->

				</div>
			</div>

		</article>
		<!-- end of messages article -->

		<div class="clear"></div>

		<!-- end of post new article -->

		<!-- end of styles article -->
		<div class="spacer"></div>
	</section>


</body>

</html>
