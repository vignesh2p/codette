(function(){
	App.controller('noticeBoardController', [ '$scope','$translate','restService','$translatePartialLoader','$log','$location','$timeout','$controller','$interval','lmsService',
                                 function($scope, $translate, restService, $translatePartialLoader, $log, $location, $timeout, $controller,$interval, lmsService) {
		  $translate.refresh();
		  $scope.ids = [];
		  console.log=("controller---------------");
		  
	}]);
	
})();