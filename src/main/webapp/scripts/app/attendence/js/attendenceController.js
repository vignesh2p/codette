(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name atrium.controller:dashboardListController
     * @description
     * # dashboardListController
     * Controller of the atrium
     */

    vApp.controller('attendenceController', ['$scope', '$q', '$compile', '$log', 'restService', 'dashboardListService', '$timeout','$controller',
    		     function ($scope, $q, $compile, $log, restService, dashboardListService,$timeout,$controller) {

    	  angular.extend(this, $controller('dashboardListController', { $scope: $scope }));
    	  
    	$scope.mainTemplate = function(type1,type2){
  	    		$scope.template1 ={
  						     "pageDetails":"scripts/app/attendence/view/"+type2+".html"
  						   };  
  			};
  	  
	  
    }]);
})();