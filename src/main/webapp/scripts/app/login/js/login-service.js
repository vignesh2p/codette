(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:loginAuthService
     * @description
     * # loginAuthService
     * Service in the atrium.
     */
    vApp.service('loginAuthService', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : getDashboardList
        * This service will retrieve the dashboard list
        */
    	
        this.getLoginAuthentication = function (user) {
            var deferred = $q.defer();
            $rootScope.master = angular.copy(user);
            var url ='/api/authentication';
            var data = restService.restCall($rootScope.master,url,'POST');
			data.$promise.then(function(response){
				deferred.resolve(response);
			},
            function (error) {
                if (error.status == "401") {
                    $state.go('login');
                } else {
                   // LxNotificationService.warning(error.data.Message);
                    LxNotificationService.warning('Error in Signing In');
                }
                deferred.reject('ajaxError');
            });
            return deferred.promise;
        };
        
        this.mailVerification = function (userName){
            var deferred = $q.defer();
            var url ='/api/common/'+userName+'/email-verification';
            var data = restService.restCall('', url, 'POST');
			data.$promise.then(function(response){
				deferred.resolve(response);
			});
            return deferred.promise;
        };
      
        
        this.logout = function(){
        	 var deferred = $q.defer();
             var url ='/api/logout';
             var data = restService.restCall('', url, 'GET');
 			 data.$promise.then(function(response){
 				deferred.resolve(response);
 			});
             return deferred.promise;
        }
        
    }]);

})();
