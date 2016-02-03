(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name atrium.controller:dashboardListController
     * @description
     * # dashboardListController
     * Controller of the atrium
     */

    vApp.controller('attendenceController', ['$scope', '$q', '$compile', '$log', 'restService', 'dashboardListService', '$timeout',
    		     function ($scope, $q, $compile, $log, restService, dashboardListService,$timeout) {

        /*
        * Controller : loadDashboardList
        * State: ""
        * This controller will retrieve and display the dashboard list
        */
        
	  $scope.status = 'Absent';
	  
	  $scope.loadStudentsList = function(standardId, sectionId) {
		  console.log('loadStudentsList----------')
		  $scope.loader = true;
          var data = dashboardListService.getStudentsList(standardId, sectionId);
		  data.then(function(success){
			  console.log(angular.toJson(success));
			  $scope.students = success;
			  //$scope.mainTemplate('','list');
			  setTimeout(function(){
				  $scope.loader = false;
			  },1000);
		  });
	  };
	 
	  $scope.selectedTab=0;
	  
	  $scope.loadEvaluationList = function() {
          var data = dashboardListService.getEvaluationList();
          data.then(function (success) {
        	  $scope.evaluationList = success;
          });
	  };
	  
    }]);
})();