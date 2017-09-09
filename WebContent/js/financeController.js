
var app = angular.module('myApp', [])

app.factory('factoryReference', ['$http', '$q', function($http, $q) {

	var dataFactory = {};
    dataFactory.getAllStockList = function() {
            //$http.post('autoCompleteServlet').success(cb);
    	var allStocksList = $http.get('resources/stockList.json');
    	return allStocksList;

    };
    dataFactory.getStockData = function(tckr) {
    		console.log(tckr);
        	var data = $http({
    			method: 'POST',
    			url: 'rest/financeService/finDataSymbol',
    			data: {ticker : tckr}
    		});
			return data;
    };

    dataFactory.getMarketIndices = function() {
    	var marketData = $http({
			method: 'POST',
			url: 'marketIndexesServlet'
    	});
    	return marketData;
    };

    dataFactory.getDowWatchlist = function() {
    	var dow30list = $http.get('resources/dow30.json');
    	return dow30list;
    }

    dataFactory.getSpx100Watchlist = function() {
    	var spx100List = $http.get('resources/spx100.json');
    	return spx100List;
    }

    return dataFactory;
}]);

app.controller("financeController", ['$scope', '$http', '$timeout', 'factoryReference',
	                                        function($scope, $http, $timeout, factoryReference) {

	var fin = {ticker:"fcx"};
    $scope.finance = {ticker:"fcx"};
    $scope.errorText = "";
    $scope.regex = '[a-zA-Z]*';
    $scope.date = new Date();
    $scope.accountSize = 5000;
    $scope.limit = 10;
    $scope.changeStyle = function() {
    	$('#tableAutoComplete').show();
    }
    $('#buttonSearch').click(function (){
    	console.log('button clicked' + $('#tickerInput').val());
    	var ticker = $('#tickerInput').val();
    	console.log(ticker + "ticker is:");
//    	if(!ticker.match("[a-zA-Z]*")) {
//    		console.log("ticker symbol does not match");
//    	}
    	var matches = ticker.match(/^[a-zA-Z ]*$/);
    	if (matches == null) {
    		//alert("ticker symbol cannot contain special characters or numbers");
    		$('.spinner').hide();
    		$scope.showTable = false;
    		$scope.showError = true;
    		$scope.errorText = "Symbol does not exist. Please enter a valid stock symbol.";

    	} else {
    		$scope.getStockData(ticker);
        	$('#hiddenTicker').val(ticker);
    	}
    });

    $('#tickerSelected').hide();
    $('#tickerInput').keypress(function(){
    	console.log('key pressed');
    	$('#tickerSelected').show();
    });

    var clock = function(){
    	var optionsLocal = {
		    weekday: "long", year: 'numeric', month: 'long', day: 'numeric',
		    hour: 'numeric', minute: 'numeric'//, second: 'numeric',
    	};

    	var formatterLocal = new Intl.DateTimeFormat([], optionsLocal);
    	$scope.dateLocal = formatterLocal.format(new Date());

    	//for firefox : remove the undefined.: instead of using another js library.
    	$scope.dateLocal = $scope.dateLocal.replace('undefined','');

    	var optionsNY = {
    		    timeZone: 'America/New_York', timeZoneName: 'short',
    		    weekday: "short", year: 'numeric', month: 'long', day: 'numeric',
    		    hour: 'numeric', minute: 'numeric'//, second: 'numeric',
    		};

    	formatterNY = new Intl.DateTimeFormat([], optionsNY);
    	$scope.dateNY = formatterNY.format(new Date());
    	//console.log(($scope.dateLocal));
    	//console.log(($scope.dateNY));
    	$timeout(clock, 1000);
    }
    $timeout(clock, 1000);
    factoryReference.getAllStockList().then(function (result) {
   		console.log(result);
   		//var res =angular.fromJson(response.finData);
        $scope.stockList = result.data.finData;

     }, function(result){
    	 console.log("getAllStockList():error");
     });

    factoryReference.getMarketIndices().then(function (result) {
    	console.log(result.data);
    	console.log("getMarketIndices()");
    	$scope.marketIndices = result.data;
    	console.log($scope.marketIndices);
    }, function(result){
    	console.log("getMarketIndices():error");
    });

    factoryReference.getDowWatchlist().then(function(result){
    	console.log("getDowWatchList()");
    	$scope.dow30List = result.data.dow30;
    });

    factoryReference.getSpx100Watchlist().then(function(result){
    	console.log("getSpx100Watchlist()");
    	$scope.spx100List = result.data.spx100;
    });

    $scope.getStockData = function(tckr) {
    	$('.spinner').show();
    	$scope.showTable = false;
    	$('.autoCompleteSuggestionsTable').hide();
    	if(tckr != undefined) {
    		$scope.selected = tckr.replace(/[\s]/g, '');
    	}
    	factoryReference.getStockData(tckr.replace(/[\s]/g, '')).then(function(result) {
    		console.log("getStockData.success()");
    		console.log(result);
    		$scope.finance = result.data;
    		$scope.symbol = $scope.finance.ticker;
    		$('.spinner').hide();
    		if($scope.finance == "null"){
    			$scope.showTable = false;
        		$scope.errorText = "Symbol does not exist. Please enter a valid stock symbol.";
    		} else {
        		$scope.showTable = true;
        		$scope.showError = false;
        		$("#hiddenTicker").val($scope.finance.ticker);
//        		var scriptTagArrs = $("#modalDiv").find("script" );
//        		for (var n = 0; n < scriptTagArrs.length; n++)
//        		    eval(scriptTagArrs[n].innerHTML)//run script inside div

        		$('.spinner').hide();
        		$('#tickerInput').val('');
    		}
        }, function(result) {
        	console.log("getStockData.error()");
    		$scope.showTable = false;
    		$scope.errorText = "Symbol does not exist. Please enter a valid stock symbol.";
        });
    };

}]);

