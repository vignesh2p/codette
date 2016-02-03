(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:dashboardListService
     * @description
     * # dashboardListService
     * Service in the atrium.
     */
    vApp.service('dashboardListService', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : getDashboardList
        * This service will retrieve the dashboard list
        */
/*        this.getDashboardList = function () {
            var deferred = $q.defer();
			var url = "/api/dashboard/dashboard";
			var data = restService.restCall("",url,'GET');
			data.$promise.then(function(response){
				console.log("response111111--------->>>>>>"+JSON.stringify(response));
				deferred.resolve(response);
			},
            function (error) {
				console.log("error--------->>>>>>"+JSON.stringify(error));
                if (error.status == "401") {
                    $state.go('login');
                } else {
                    LxNotificationService.warning(error.data.Message);
                }
                deferred.reject('ajaxError');
            });
            return deferred.promise;
        };*/
        
        this.getStudentsList = function (standardId, sectionId) {
            var deferred = $q.defer();
  		    var url = 'api/student/standard/'+standardId+'/section/'+sectionId;
		    var data = restService.restCall("",url,'GETLIST');
			data.$promise.then(function(response){
				deferred.resolve(response);
			},
            function (error) {
                if (error.status == "401") {
                    $state.go('login');
                } else {
                    LxNotificationService.warning(error);
                }
                deferred.reject('ajaxError');
            });
            return deferred.promise;
        };

        this.getClassList = function(){
        	var deferred = $q.defer();
  		    var url = "api/student/classes";
		    var data = restService.restCall("",url,'GETLIST');
			data.$promise.then(function(response){
				deferred.resolve(response);
			},
            function (error) {
                if (error.status == "401") {
                    $state.go('login');
                } else {
                    LxNotificationService.warning(error);
                }
                deferred.reject('ajaxError');
            });
            return deferred.promise;
        };
        
        this.getStudentsByStaff = function(){
        	var deferred = $q.defer();
  		    var url = "api/student/classTeacher";
		    var data = restService.restCall("",url,'GETLIST');
			data.$promise.then(function(response){
				deferred.resolve(response);
			},
            function (error) {
                if (error.status == "401") {
                    $state.go('login');
                } else {
                    LxNotificationService.warning(error);
                }
                deferred.reject('ajaxError');
            });
            return deferred.promise;
        };
        
        this.getActivityList = function () {
            var deferred = $q.defer();
  		    var url = "/api/dashboard/activity";
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
        
        this.getEvaluationList = function () {
            var deferred = $q.defer();
  		    var url = "/api/dashboard/evaluation";
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
