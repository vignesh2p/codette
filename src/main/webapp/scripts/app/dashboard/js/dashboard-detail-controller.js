(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name atrium.controller:dashboardDetailController
     * @description
     * # dashboardDetailController
     * Controller of the atrium
     */

    vApp.controller('dashboardDetailController', ['$scope', '$q', '$compile', '$log', 'restService', 'dashboardDetailService', '$rootScope',
       function ($scope, $q, $compile, $log, restService, dashboardDetailService, $rootScope) {

        /*
        * Controller : getEventDetail
        * State: ""
        * This controller will retrieve and display the dashboard list
        */
     $rootScope.student ={};   
	  $scope.studentCreate = function(student){
		  console.log('student--------'+angular.toJson($scope.student));
		  $scope.loader = true;
		  $scope.create = false;
		  if(student){
			 var data = dashboardDetailService.getStudentCreate($scope.student);
			  data.then(function (success){
				  $scope.mainTemplate('','classlist');
				  $scope.loader = false;
				  $scope.student = {};
			 });
		  }
	  };
	  
    }]);
})();