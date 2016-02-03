(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name atrium.controller:workerDetailController
     * @description
     * # workerDetailController
     * Controller of the atrium
     */

    vApp.controller('workerDetailController', ['$scope', '$q', '$compile', '$log', 'restService', 'workerDetailService', '$timeout',
    		     function ($scope, $q, $compile, $log, restService, workerDetailService,$timeout) {

        /*
        * Controller : loadDashboardList
        * State: ""
        * This controller will retrieve and display the dashboard list
        */
      
      $scope.getPersonInfo = function(){
		  var data = workerDetailService.loadPersonInfo();
		  data.then(function(success){
			  $scope.personInfo = success;
		  });
      };
      
      $scope.getPersonProfile = function(){
		  var data = workerDetailService.loadPersonProfile();
		  data.then(function(success){
			  $scope.personProfile = success;
		  });
      };
     
      $scope.getEvaluationStatus = function(){
		  var data = workerDetailService.loadEvaluationStatus();
		  data.then(function(success){
			  $scope.evaluationStatus = success;
		  });
      };
	  
    }]);
})();