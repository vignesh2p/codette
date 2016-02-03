(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:workerDetailService
     * @description
     * # workerDetailService
     * Service in the atrium.
     */
    vApp.service('workerDetailService', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : getDashboardList
        * This service will retrieve the dashboard list
        */
    	
        this.loadPersonInfo = function () {
            var deferred = $q.defer();
    		var url = "/api/person/getperson";
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
        
        this.loadPersonProfile = function () {
            var deferred = $q.defer();
    		var url = "/api/person/getPersonProfile";
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
        
        this.loadEvaluationStatus = function () {
            var deferred = $q.defer();
    		var url = "/api/person/getEvaluationStatus";
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
        
    }]);

})();
