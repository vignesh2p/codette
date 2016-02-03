(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:clientListService
     * @description
     * # clientListService
     * Service in the atrium.
     */
    vApp.service('clientListService', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : getDashboardList
        * This service will retrieve the dashboard list
        */
        this.getUserList = function () {
            var deferred = $q.defer();
	    	var url = '/api/staff/getStaffsList';
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
        
        this.getClientDetails = function () {
            var deferred = $q.defer();
  			var url ='/api/organization/relatedOrganization/2631b21f-77d6-4f8f-8cf7-8dab675e6e33?relationshipType=CLUB';
  			var data = restService.restCall("",url,'GETLIST');
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
        
        this.getUsersByRoleAC = function (role_type) {
            var deferred = $q.defer();
	    	var url = '/api/person/2631b21f-77d6-4f8f-8cf7-8dab675e6e33/roles?roleType='+role_type;
	    	var data = restService.restCall("",url,'GETLIST');
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
        
        this.getUsersByRoleAD = function (role_type) {
            var deferred = $q.defer();
	    	var url = '/api/person/2631b21f-77d6-4f8f-8cf7-8dab675e6e33/roles?roleType='+role_type;
	    	var data = restService.restCall("",url,'GETLIST');
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
        
        this.getUsersByRoleCA = function (role_type) {
            var deferred = $q.defer();
	    	var url = '/api/person/2631b21f-77d6-4f8f-8cf7-8dab675e6e33/roles?roleType='+role_type;
	    	var data = restService.restCall("",url,'GETLIST');
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
        
        this.getUsersByRoleCMT = function () {
            var deferred = $q.defer();
	    	var url = '/api/person/2631b21f-77d6-4f8f-8cf7-8dab675e6e33/roles?roleType=NRL_ADMIN';
	    	var data = restService.restCall("",url,'GETLIST');
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
        
        this.getClientsList = function () {
            var deferred = $q.defer();
  			var url ='/api/client/list';
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
