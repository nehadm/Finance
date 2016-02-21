<!DOCTYPE html>
<html lang="en">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<head>

<style>
.high {
    background-color: #FF9B9B;
}
.normal {
    background-color: #B2F0FF;
}
table, td, th {
  border: 1px solid grey;
  border-collapse: collapse;
  padding: 5px;
}
.indexBlock {
	border: medium;
	background: red;
	border-width: 2px;
}

</style>
</head>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">

<body ng-app="myApp" >

<div ng-controller="marketIndicesController">
	<table class="w3-table">
		<tr>
	 	  	<th>Index Name</th> 
	 	  	<th>Symbol</th>
	 	  	<th>Last value</th>
    	  </tr> 
<!-- 	  <tr ng-repeat="x in indexes"> -->
<!-- 	  <td> -->
<!-- 		  <div class="indexBlock"> -->
<!-- 		  	<h2>{{ x.indexName }}</h2> - <h2>{{ x.symbol }}</h2><br/> -->
<!-- 		  	<h1>{{ x.indexLastValue }}</h1> -->
<!-- 		  </div> -->
<!-- 	  </td> -->
	  
	  <tr ng-repeat="x in indexes">
	    <td ng-if="$odd" style="background-color:#B2F0FF">{{ x.indexName }}</td>
	    <td ng-if="$odd" style="background-color:#B2F0FF">{{ x.symbol }}</td>
	    <td ng-if="$odd" style="background-color:#B2F0FF">{{ x.indexLastValue }}</td>
	    
	    <td ng-if="$even">{{ x.indexName }}</td>
	    <td ng-if="$even">{{ x.symbol }}</td>
	    <td ng-if="$even">{{ x.indexLastValue }}</td>
	  </tr>
	</table>
</div>
<br/><br/>
<div ng-controller="financeController" ng-init="showTable=false">

  <form>
    Enter the Stock Symbol:<br>
    <input type="text" ng-model="finance.ticker" required>&nbsp;
    <button ng-click="add()" >Check</button>
  </form>   
  <br/>
  <label ng-model="errorText">{{errorText}}</label>
  <h1 ng-show="showTable" >{{finance.companyName}}</h1>
  <h2 ng-show="showTable" >Last price : {{finance.stockPrice | currency}}</h2>
  <table ng-show="showTable" class="w3-table">
  	<tr>
  		<th>Symbol</th>
  		<th>ATR</th>
  		<th>Short Float</th>
  		<th>Target Price</th>
  		<th>Earnings Call</th>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">{{ finance.ticker | uppercase}}</td>
    	<td ng-class="(finance.atr >= 5.0) ? 'high' : 'normal'">{{ finance.atr }}</td>
    	<td ng-class="(finance.shortFloat >= 20.0) ? 'high' : 'normal'">{{ finance.shortFloat }}</td>
    	<td style="background-color:#B2F0FF">{{ finance.targetPrice }}</td>
    	<td style="background-color:#B2F0FF">{{ finance.earningsCall }}</td>
  	</tr>
  </table>
  <br/>
  <span ng-show="showTable">** Red indicates that the value is too high, hence the trade can become risky.</span><br/>
  <span ng-show="showTable">** AMC - After Market Close; BMO - Before Market Open</span>
  <br/><br/>
  <table ng-show="showTable" class="w3-table">
  	<tr colspan="2">
  		<th>ATR Calculation Table</th>
  	</tr>
 	<tr colspan="2">
  		<th>Risked Amount per day($)</th>
  		<th>Position Size</th>
  		<th>Amount invested</th>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">100</td>
    	<td style="background-color:#B2F0FF" ng-bind="100 / finance.atr"></td>
    	<td style="background-color:#B2F0FF" ng-bind="(100 / finance.atr) * finance.stockPrice"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">150</td>
    	<td style="background-color:#B2F0FF" ng-bind="150 / finance.atr"></td>
    	<td style="background-color:#B2F0FF" ng-bind="(150 / finance.atr) * finance.stockPrice"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">200</td>
    	<td style="background-color:#B2F0FF" ng-bind="200 / finance.atr"></td>
    	<td style="background-color:#B2F0FF" ng-bind="(200 / finance.atr) * finance.stockPrice"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">250</td>
    	<td style="background-color:#B2F0FF" ng-bind="250 / finance.atr"></td>
    	<td style="background-color:#B2F0FF" ng-bind="(250 / finance.atr) * finance.stockPrice"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">300</td>
    	<td style="background-color:#B2F0FF" ng-bind="300 / finance.atr"></td>
    	<td style="background-color:#B2F0FF" ng-bind="(300 / finance.atr) * finance.stockPrice"></td>
  	</tr>
  	
  	  	<tr>
    	<td style="background-color:#B2F0FF"><input type="text" ng-model="riskAmount" ng-show="showTable"></td>
    	<td style="background-color:#B2F0FF" ng-init="0" ng-bind="riskAmount / finance.atr"></td>
    	<td style="background-color:#B2F0FF" ng-init="0" ng-bind="(riskAmount / finance.atr) * finance.stockPrice"></td>
  	</tr>
  	
  </table>
  <br/>
  
   <table ng-show="showTable && finance.divData != null" class="w3-table">
  	<tr colspan="2">
  		<th>Dividend Information</th>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">Ex Dividend Date</td>
    	<td style="background-color:#B2F0FF" ng-bind="finance.divData.exDivDate"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">Pay Date</td>
    	<td style="background-color:#B2F0FF" ng-bind="finance.divData.payDate"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">Annual Dividend</td>
    	<td style="background-color:#B2F0FF" ng-bind="finance.divData.annualDividend | currency"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">Dividend Yield (%)</td>
    	<td style="background-color:#B2F0FF" ng-bind="finance.divData.divYield"></td>
  	</tr>
  	<tr>
    	<td style="background-color:#B2F0FF">Years Paying</td>
    	<td style="background-color:#B2F0FF" ng-bind="finance.divData.yearsPaying"></td>
  	</tr>
  </table>

</div>

<script>
var app = angular.module('myApp', []);
app.controller('financeController', function($scope, $http) {
    $scope.finance = {ticker:"googl"};
    $scope.errorText = "";
    $scope.add = function() {
     
		$http({
			method: 'POST',
			url: 'javaAngularJS',
			//headers: {'Content-Type': 'application/json'},
			data: $scope.finance
		}).success(function (data) {
			$scope.finance = data;
			$scope.showTable = true;
		}).error(function (data) {
			$scope.showTable = false;
			$scope.errorText = "Please enter a proper stock symbol";
		});
	};
});

app.controller('marketIndicesController', function($scope, $http) {

		$http({
			method: 'POST',
			url: 'marketIndexesServlet'
		}).success(function (data) {
			$scope.indexes = data;
		}).error(function (data) {
			$scope.showTable = false;
		});
});

</script>

</body>
</html>
