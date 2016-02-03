(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:dashboardListService
     * @description
     * # dashboardListService
     * Service in the atrium.
     */
    vApp.service('workerListService', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : getDashboardList
        * This service will retrieve the dashboard list
        */
        this.loadWorkerList = function () {
            var deferred = $q.defer();
            var url = "/api/staff/teachingStaffs";
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
        
    }]);

})();
