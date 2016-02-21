<!DOCTYPE html>
<!-- saved from url=(0043)http://www.w3schools.com/w3css/demo_red.htm -->
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<style>
.high {
    background-color: #F91505;
}
.normal {
    background-color: #FFFFFF;
}
</style>
<title>Welcome to Neha's Finance Website</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/w3-theme-teal.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/custom.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="js/w3codecolors.js"></script> 
<script src="js/financeController.js"></script> 
</head>
<body ng-app="myApp" >

<!-- Header -->
<header class="w3-container w3-theme w3-padding" id="myHeader">
  
  <div class="w3-center">
  <h4>Get a quick snapshot of your portfolio. Make wise decisions.</h4>
  <h1 class="w3-xxxlarge w3-animate-bottom">Stock Analyzer</h1>
  </div>
</header>

<!-- Modal -->
<div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-card-8 w3-animate-top">
      <header class="w3-container w3-theme"> 
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-closebtn">×</span>
        <h4>Send me an email with your questions regarding Stocks!</h4>
      </header>
      <div class="w3-group">
      	<form class="w3-container w3-card-4" name="questionsForm">
			<input class="w3-input" type="email" name="myAddress">
    		<span ng-show="questionsForm.myAddress.$error.email">Not a valid e-mail address</span>
	        <label class="w3-label w3-validate">Email address</label><br/>
	        <textarea class="w3-input" required=""></textarea>
	        <label class="w3-label w3-validate">Question</label>
			<br/>
			<button class="w3-btn w3-theme w3-padding-small">Send</button>
		</form>
	  </div>
    </div>
</div>

<div class="w3-container">
<hr>

<div class="w3-topnav w3-large w3-theme">
	<ul class="w3-navbar w3-border w3-black">
		<li class="w3-quarter"><a class="w3-btn w3-theme" onclick="openTab('marketTab')">Market</a></li>
		<li class="w3-quarter"><a class="w3-btn w3-theme" onclick="openTab('stockTab')">Stock</a></li>
		<li class="w3-quarter"><a class="w3-btn w3-theme" onclick="openTab('etfTab')">ETFs</a></li>
		<li class="w3-quarter"><a class="w3-btn w3-theme" onclick="openTab('currencyTab')">Currencies</a></li>
	</ul>
</div>
<br/>
<!-- Show market indexes here -->
<div class="w3-container tab" id="marketTab" ng-controller="marketIndicesController">
  <hr>
  <div class="w3-center">
    <h4>Market Indexes</h4>	
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>
<tr class="w3-theme">
  <th>Index Name</th>
  <th>Symbol</th>
  <th>Last Value</th>
</tr>
</thead>
<tbody>
	  <tr ng-repeat="x in indexes">
	    <td ng-if="$odd" ng-class="w3-theme-light">{{ x.indexName }}</td>
	    <td ng-if="$odd" ng-class="w3-theme-light">{{ x.symbol }}</td>
	    <td ng-if="$odd" ng-class="w3-theme-light">{{ x.indexLastValue }}</td>
	    
	    <td ng-if="$even">{{ x.indexName }}</td>
	    <td ng-if="$even">{{ x.symbol }}</td>
	    <td ng-if="$even">{{ x.indexLastValue }}</td>
	  </tr>

</tbody>
</table>
</div>

<hr>
<br/>
</div>
<div id="stockTab" class="tab" ng-controller="financeController" ng-init="showTable=false">
<!-- Show the stock input text and button here-->
<div class="w3-center">
	<form class="w3-container w3-card-4">
	
	  <div class="w3-group">      
	    <input class="w3-input" type="text" required="" ng-model="finance.ticker" >
	    <label class="w3-label w3-validate">Stock Symbol</label>
		<br/><br/>
		<button class="w3-btn w3-theme" ng-click="add()" >Check</button>
	  </div>
	  
	</form>
</div>

<div class="w3-row-padding w3-center w3-margin-top">
	<div ng-show="showTable" class="w3-container m2 w3-text-dark-grey">
		<h2 class="w3-theme-light"> {{finance.companyName}}</h2>
		<h4 ng-show="showTable" >Last price : {{finance.stockPrice | currency}}</h4>
	</div>
	<div class="w3-center">
		<div class="w3-half" ng-show="showTable">
		  <div class="w3-card-2 w3-padding-top" style="min-height:460px">
		  <h2 class="w3-theme-light">General Data</h2>
		  <hr>
		  <p>Ticker : {{ finance.ticker | uppercase}}</p>
		  <p>ATR : {{ finance.atr }}</p>
		  <p>Short Float : {{ finance.shortFloat }}</p>
		  <p>Target Price : {{ finance.targetPrice }}</p>
		  <p>Earnings Call : {{ finance.earningsCall }}</p>
		  </div>
		</div>
	
		<div ng-show="showTable && finance.divData != null" class="w3-half">
		  <div class="w3-card-2 w3-padding-top" style="min-height:460px">
		  <h2 class="w3-theme-light">Dividend Data</h2><hr>
		  <p>Ex Div Date : {{ finance.divData.exDivDate }}</p>
		  <p>Pay Date : {{ finance.divData.payDate }}</p>
		  <p>Annual Dividend : {{ finance.divData.annualDividend | currency }}</p>
		  <p>Div Yield(%) : {{ finance.divData.divYield }}</p>
		  <p>Years Paying : {{ finance.divData.yearsPaying }}</p>
		  </div>
		</div>
	</div>
	<div class="w3-center" ng-show="showTable">
	  <div class="w3-card-2 w3-padding-top" style="min-height:460px">
	  <h2 class="w3-theme-light">ATR Calculation</h2><hr>
		<table class="w3-table w3-bordered w3-theme-light">
	 	<tr colspan="3">
	  		<th>Risked Amount per day($)</th>
	  		<th>Position Size</th>
	  		<th>Amount invested($)</th>
	  	</tr>
	  	<tr>
	    	<td>100</td>
	    	<td ng-bind="100 / finance.atr"></td>
	    	<td ng-bind="(100 / finance.atr) * finance.stockPrice"></td>
	  	</tr>
	  	<tr>
	    	<td >150</td>
	    	<td ng-bind="150 / finance.atr"></td>
	    	<td ng-bind="(150 / finance.atr) * finance.stockPrice"></td>
	  	</tr>
	  	<tr>
	    	<td >200</td>
	    	<td ng-bind="200 / finance.atr"></td>
	    	<td ng-bind="(200 / finance.atr) * finance.stockPrice"></td>
	  	</tr>
	  	<tr>
	    	<td>250</td>
	    	<td ng-bind="250 / finance.atr"></td>
	    	<td ng-bind="(250 / finance.atr) * finance.stockPrice"></td>
	  	</tr>
	  	<tr>
	    	<td>300</td>
	    	<td ng-bind="300 / finance.atr"></td>
	    	<td ng-bind="(300 / finance.atr) * finance.stockPrice"></td>
	  	</tr>
	  	
	  	<tr>
	    	<td><input type="text" ng-model="riskAmount" ng-show="showTable"></td>
	    	<td ng-init="0" ng-bind="riskAmount / finance.atr || 0 | number:0"></td>
	    	<td ng-init="0" ng-bind="(riskAmount / finance.atr) * finance.stockPrice || 0 | number:0"></td>
	  	</tr>
	  	
	  </table>
	  </div>
	</div>
</div>
</div>

<div id="etfTab" class="tab">
ETF Tab
</div>

<div id="currencyTab" class="tab">
Currency Tab
</div>

<br/><br/><br/><br/><br/>
<br/><!-- Footer -->
<footer class="w3-container w3-theme-light">
  <h5>Copyright &#169;</h5>
  <p class="w3-text-grey">If you find this information useful, contact me at 617-909-7123</p>
  <div class="w3-padding-32">
      <button class="w3-btn w3-tiny w3-theme" onclick="document.getElementById('id01').style.display='block'" style="font-weight:900;">Click here to send me an email</button>
  </div>
</footer>
</div>
<script>

window.onLoad = openTab('marketTab'); 
function openTab(tabName) {
  var i;
  var x = document.getElementsByClassName("tab");
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";  
  }
  x = document.getElementById(tabName).style.display = "block";  
}
</script>
</body>
</html>