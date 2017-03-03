var app = angular.module('myApp', [])

app.factory('factoryReference', ['$http', '$q', function($http, $q) {

	var dataFactory = {};
    dataFactory.getAllStockList = function(cb) {
            $http.post('autoCompleteServlet').success(cb);
    };
    dataFactory.getStockData = function(tckr) {
    		console.log(tckr);
        	var data = $http({
    			method: 'POST',
    			url: 'javaAngularJS',
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
    	console.log('button clicked');
    	console.log($scope.selected);
    	$scope.getStockData($scope.selected);
    });

    $('#tickerSelected').hide();
    $('#tickerInput').keypress(function(){
    	console.log('key pressed');
    	$('#tickerSelected').show();
    });
    
    var clock = function(){
    	$scope.date = new Date();
    	$timeout(clock, 1000);
    }
    $timeout(clock, 1000);
    factoryReference.getAllStockList(function (response) {
   		console.log(response);
   		var res =angular.fromJson(response.finData);
        $scope.stockList = res;

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
    	$('#spinner').show();
    	$scope.showTable = false;
    	$scope.selected = tckr.replace(/[\s]/g, '');
    	factoryReference.getStockData(tckr.replace(/[\s]/g, '')).then(function(result) {
    		console.log("getStockData.success()");
    		console.log(result);
    		$scope.finance = result.data;
    		$scope.symbol = $scope.finance.ticker;
    		$scope.showTable = true;
    		$('#spinner').hide();
        }, function(result) {
        	console.log("getStockData.error()");
    		$scope.showTable = false;
    		$scope.errorText = "Symbol does not exist. Please enter a valid stock symbol.";
        });
    };

}]);

