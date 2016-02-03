(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:clientDetailService
     * @description
     * # clientDetailService
     * Service in the atrium.
     */
    vApp.service('ntStaffservice', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : getDashboardList
        * This service will retrieve the dashboard list
        */
    	this.createStaff = function(obj,staff){
    		
    		console.log('service------------>'+JSON.stringify(obj));
    	    $rootScope.master = angular.copy(obj);
    		var deferred = $q.defer();
  		    var url = "/api/staff/createStaff/"+staff;
  		    var data = restService.restCall($rootScope.master, url, 'POST');
			data.$promise.then(function(response){
				deferred.resolve(response);
			},
			function(error){
				LxNotificationService.warning('Error in Creating User'+error);
			});
            return deferred.promise;
		};
		
	       this.getStaffList = function () {
				console.log("------------------------------INSIDE SERVICE-------------------")
 
	    	   var deferred = $q.defer();
	  		    var url = "/api/staff/staffs";
			    var data = restService.restCall("",url,'GETLIST');
				data.$promise.then(function(response){
					deferred.resolve(response);
					console.log(JSON.stringify(response));
				},
	            function (error) {
	                if (error.status == "401") {
	                    $state.go('login');
	                } else {
	                    LxNotificationService.warning(error.data.Message);
	                }
	                deferred.reject('ajaxError');
	            });
	            return deferred.promise;
	        };
	             
    }]);

})();
