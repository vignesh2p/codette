(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:dashboardDetailService
     * @description
     * # dashboardDetailService
     * Service in the atrium.
     */
    vApp.service('dashboardDetailService', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : getDashboardList
        * This service will retrieve the dashboard list
        */
        this.getengagementDetails = function () {
            var deferred = $q.defer();
  		    var url = "/api/dashboard/engagementQuestion";
  		    var data = restService.restCall("",url,'GET');
			data.$promise.then(function(response){
				deferred.resolve(response);
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
        
        this.getStudentCreate = function (student){
        	var deferred = $q.defer();
        	var url = "/api/student/create";
        	var data = restService.restCall(student, url, 'POST');
        	data.$promise.then(function(response){
        		deferred.resolve(response);
        	},
        	function(error){
        		if(error.status == "401"){
        			$state.go('login');
        		}else{
        			LxNotificationService.warning(error.data.Message);
        		}
        	deferred.reject('ajaxError');
        	});
        	 return deferred.promise;
        }
        
    }]);

})();
