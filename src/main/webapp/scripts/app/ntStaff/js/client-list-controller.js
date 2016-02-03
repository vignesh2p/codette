(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name atrium.controller:clientListController
     * @description
     * # clientListController
     * Controller of the atrium
     */

    vApp.controller('clientListController', ['$scope', '$q', '$compile', '$log', 'restService', 'clientListService', '$timeout',
    		     function ($scope, $q, $compile, $log, restService, clientListService,$timeout) {

        /*
        * Controller : loadDashboardList
        * State: ""
        * This controller will retrieve and display the dashboard list
        */
	  
	    $scope.loadNTStaffList = function(){
	    	$scope.loader=true;
	    	  var data = clientListService.getUserList();
  	    	  data.then(function(success){
	  	    		 $scope.ntStaffList = success;
	  	    		 setTimeout(function(){$scope.loader=false;},800);
	  	     });
	    };
	    
        $scope.loadClientDetails = function(){
	    	  var data = clientListService.getClientDetails();
  	    	  data.then(function(success){
  	    		$scope.clientDetails = success;
	  	     });
  		   };
      
		    $scope.loadUsersByRoleAC = function(role_type){
	    	    var data = clientListService.getUsersByRoleAC(role_type);
  	    	    data.then(function(success){
  	    	    	$scope.userList_AC = success;
		  	     });
		    }; 
		    $scope.loadUsersByRoleAD = function(role_type){
	    	    var data = clientListService.getUsersByRoleAD(role_type);
  	    	    data.then(function(success){
  	    	    	$scope.userList_AD = success;
		  	     });
		    }; 
		    $scope.loadUsersByRoleCA = function(role_type){
	    	    var data = clientListService.getUsersByRoleCA(role_type);
  	    	    data.then(function(success){
  	    	    	$scope.userList_CA = success;
		  	     });
		    }; 
		    
		    $scope.loadUsersByRoleCMT = function(){
	    	    var data = clientListService.getUsersByRoleCMT();
  	    	    data.then(function(success){
  	    	    	$scope.userList_CMT = success;
	  	    		for(var i=0; i <$scope.userList_CMT.length;i++){
	  	    			clientService.setDetails($scope.userList_CMT[i]);
	  	    		}
		  	     });
		    }; 
		    
		    $scope.loadClientList = function(){
				  var data = clientListService.getClientsList();
				  data.then(function(success){
					  $scope.clientList = success;
				  });
		      };
    }]);
})();