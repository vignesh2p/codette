(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name atrium.controller:dashboardListController
     * @description
     * # dashboardListController
     * Controller of the atrium
     */

    vApp.controller('dashboardListController', ['$scope', '$q', '$compile', '$log', 'restService', 'dashboardListService', '$timeout',
    		     function ($scope, $q, $compile, $log, restService, dashboardListService,$timeout) {

        /*
        * Controller : loadDashboardList
        * State: ""
        * This controller will retrieve and display the dashboard list
        */
        
/*  	  $scope.loadDashboardList = function() {
          var data = dashboardListService.getDashboardList();
          data.then(function (success) {
        	  console.log("success111--------->>>>>>"+JSON.stringify(success));
        	  $scope.dashboardList = success;
          });
	  };*/
	  $scope.status = 'Absent';
	  $scope.create = false;
	  $scope.loadStudentsList = function(standardId, sectionId) {
		  console.log('loadStudentsList----------'+standardId+'--------sectionId---'+sectionId);
		  if(standardId != '' && sectionId != ''){
			  $scope.loader = true;
	          var data = dashboardListService.getStudentsList(standardId, sectionId);
			  data.then(function(success){
				  console.log(angular.toJson(success));
				  $scope.students = success;
				  //$scope.mainTemplate('','list');
					  $scope.loader = false;
			  });
		  }
	  };
	 
	  $scope.standardId =''; 
	  $scope.sectionId ='';
	  
	  $scope.selectedTab=0;
	  
	  $scope.loadClassList =function(){
		  var data = dashboardListService.getClassList();
		  data.then(function(success){
			  console.log(angular.toJson(success));
			  $scope.classList = success;
			  $scope.loadStudentsList($scope.classList[0].standard.id, $scope.classList[0].section.id);
		  });
	  };
	  
	  $scope.getStudentsByStaff = function (){
		  var data = dashboardListService.getStudentsByStaff();
		  data.then(function(success){
			  console.log(angular.toJson(success));
			  $scope.students = success;
		  });
	  };
	  
    }]);
})();