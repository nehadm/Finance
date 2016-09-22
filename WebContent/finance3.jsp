<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link rel="SHORTCUT ICON" href="images/fin.png">
<title>Welcome to Neha's Finance Website</title>
<!-- Bootstrap Core CSS -->
<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="js/w3codecolors.js"></script> 
<script src="js/financeController.js"></script>
</head>
<body ng-app="myApp">

<!-- Header -->

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
<header id="myHeader">
  <div>
  	<h4>Stock Analyzer</h4	>
  	<h6>Get a quick snapshot of your portfolio. Make wise decisions.</h6>
  </div>
</header>
</nav>
<div>
<!-- Show stock details here -->
<jsp:include page="stockTab.jsp"></jsp:include>

<br/><!-- Footer -->
<footer>
  <h5>Copyright &#169;</h5>
  <p>If you find this information useful, contact me at 617-909-7123</p>
</footer>
</div>
</body>
</html>