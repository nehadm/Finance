var app = angular.module('myApp', []);
app.controller('financeController', function($scope, $http) {
    $scope.finance = {ticker:"intc"};
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