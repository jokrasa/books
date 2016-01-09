var app = angular.module('myApp', [ 'ngGrid', 'restangular' ]);

var bookData;


app.controller('gridCtrl', [ '$scope', function($scope) {
	$scope.myData = [];

	$scope.gridOptions = {
		data : 'myData',
		enableColumnResize : true,
        columnDefs: [{ field: 'title', displayName: 'Title', enableCellEdit: false },
                     { field: 'author', displayName: 'Author', enableCellEdit: false },
                     { field: 'genre', displayName: 'Genre', enableCellEdit: false },
                     { field: 'pages', displayName: 'Pages', enableCellEdit: false },
                     { field: 'year', displayName: 'Year', enableCellEdit: false },
                     { field: 'rating', displayName: 'Rating', enableCellEdit: false },
                     { field: 'id', displayName: 'Update', enableCellEdit: false,
                       cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{\'/books/update?id=\'+COL_FIELD}}">UPDATE</a></div>'
                    	  },
                      {field: 'id', displayName: 'Delete', enableCellEdit: false,
                       cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{\'/books/delete?id=\'+COL_FIELD}}">DELETE</a></div>'
                       	  }]
	};

	console.log(">>>>>>>>>>>>>>>>gridCtrl");

	$scope.$on('bookData', function(event, bookData) {
		console.log(">>>>>>>>>>>>>>>>>>>fires on bookData");
		$scope.myData = bookData.plain();
		console.log("$scope.myData = " + $scope.myData);
		$scope.gridOptions = {
			data : 'myData',
			enableColumnResize : true
		};
		// $scope.$apply;

	});

} ]);

app.controller('searchController', function($scope, $http, $location,
		Restangular) {

	$scope.bookData = {};

	// $scope.listView = function(){
	// $location.path("/search");
	// }

	$scope.processSearchForm = function() {
		console.log(">>>>>>>>>>>>>>>>processSearchForm");
		//$scope.bookData.href = "'/books/update?id="+$scope.bookData.id+"'";
		// data from the form works !!
		var aData = $scope.bookData.author;
		var gData = $scope.bookData.genre;
		var pData = $scope.bookData.pages;
		//var pDataInt = parseInt(pData);
		var yData = $scope.bookData.year;
		var rData = $scope.bookData.rating;
		//var href = $scope.bookData.href;

		console.log(">>>>>>>>>>>>>>>>aData" + aData);
		console.log(">>>>>>>>>>>>>>>>gData" + gData);
		console.log(">>>>>>>>>>>>>>>>pData" + pData);
		console.log(">>>>>>>>>>>>>>>>yData" + yData);
		console.log(">>>>>>>>>>>>>>>>rData" + rData);


		Restangular.all(
				'books/rest/' + aData + '/' + gData + '/' + pData + '/' + yData
						+ '/' + rData).getList().then(
				function(result) {
					$scope.books = result;
					console.log(">>>>>>>>>>>>>>>>books: "
							+ angular.toJson($scope.books, 2));
					bookData = angular.toJson($scope.books, 2);
					// add the href to the result here somehow...

					$scope.$emit('bookData', result);
					$scope.bookData = {};
				});

	}
});


app.controller('addController', function($scope, $http, $location,
		Restangular) {

	$scope.bookData = {};

	// $scope.listView = function(){
	// $location.path("/search");
	// }

	$scope.processAddForm = function() {
		console.log(">>>>>>>>>>>>>>>>processAddForm");
		//$scope.bookData.href = "'/books/update?id="+$scope.bookData.id+"'";
		// data from the form works !!
		var aData = $scope.bookData.author;
		var gData = $scope.bookData.genre;
		var pData = $scope.bookData.pages;
		var yData = $scope.bookData.year;
		var rData = $scope.bookData.rating;
		//var href = $scope.bookData.href;

		console.log(">>>>>>>>>>>>>>>>aData" + aData);
		console.log(">>>>>>>>>>>>>>>>gData" + gData);
		console.log(">>>>>>>>>>>>>>>>pData" + pData);
		console.log(">>>>>>>>>>>>>>>>yData" + yData);
		console.log(">>>>>>>>>>>>>>>>rData" + rData);
 

		Restangular.all(
				'books/rest/' + aData + '/' + gData + '/' + pData + '/' + yData
						+ '/' + rData).getList().then(
				function(result) {
					$scope.books = result;
					console.log(">>>>>>>>>>>>>>>>books: "
							+ angular.toJson($scope.books, 2));
					bookData = angular.toJson($scope.books, 2);
					// add the href to the result here somehow...

					$scope.$emit('bookData', result);
					$scope.bookData = {};
				});

	}
});
