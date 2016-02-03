(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name atrium.controller:clientDetailController
     * @description
     * # clientDetailController
     * Controller of the atrium
     */

    vApp.controller('clientDetailController', ['$scope', '$q', '$compile', '$log', 'restService', 'clientDetailService', '$timeout',
    		     function ($scope, $q, $compile, $log, restService, clientDetailService,$timeout) {

        /*
        * Controller : loadDashboardList
        * State: ""
        * This controller will retrieve and display the dashboard list
        */
      
      $scope.loadClientDetail = function(){
		  var data = clientDetailService.getClientDetail();
		  data.then(function(success){
			  $scope.clientDetail = success;
		  });
      };
      
      $scope.loadClientProfile = function(){
		  var data = clientDetailService.getClientProfile();
		  data.then(function(success){
			  $scope.clientProfile = success;
		  });
      };
      
    }]);
})();