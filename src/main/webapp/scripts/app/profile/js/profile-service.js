(function () {
    'use strict';
    /**
     * @ngdoc service
     * @name atrium.service:clientDetailService
     * @description
     * # clientDetailService
     * Service in the atrium.
     */
    vApp.service('profileservice', ['$rootScope', '$resource', '$q', '$log', 'restService', 'LxNotificationService', function ($rootScope,
        $resource, $q, $log, restService, LxNotificationService) {

        /*
        * Service : get profile Details
        * This service will retrieve the dashboard list
        */
    	this.loaduserDetail =function(){
  		  var url ='/api/staff/getProfile';
  		  var deferred = $q.defer();
  		  var data = restService.restCall("",url,'GET');
  		  data.$promise.then(function(response){
  				deferred.resolve(response);
  		  },
  		  function(error){
  		  });
  		  return deferred.promise;
  		  
    	}
    	
    	this.updateProfile = function(user){
    		  $rootScope.master = angular.copy(user);
    		  var url ='/api/staff/updateProfile';
    		  var deferred = $q.defer();
    		  var data = restService.restCall($rootScope.master, url, 'PUT');
    		  data.$promise.then(function(response){
    				deferred.resolve(response);
    		  },
    		  function(error){
    		  });
    		  return deferred.promise;
    		  
      	}

	             
    }]);

})();
