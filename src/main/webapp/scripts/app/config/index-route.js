(function() {
  'use strict';

  angular
    .module('school-management')
    .config(['$routeProvider','$httpProvider','$logProvider', function ($routeProvider,$httpProvider,$log) {
    	function checkLoggedIn($q, $log, loginService) {
            var deferred = $q.defer();
            console.log(localStorage.getItem("userName"));
            if (localStorage.getItem("isAuthenticated") == "false") {
            	$log.log('authentication required. redirect to login');
                deferred.reject({ needsAuthentication: true });
            } else if(localStorage.getItem("userName") == null){
            	$log.log('authentication required. redirect to login');
                deferred.reject({ needsAuthentication: true });
            }else {
                deferred.resolve();
            }
 
            return deferred.promise;
        }
 
        $routeProvider.whenAuthenticated = function (path, route) {
            route.resolve = route.resolve || {};
            angular.extend(route.resolve, { isLoggedIn: ['$q', '$log', 'loginService', checkLoggedIn] });
            return $routeProvider.when(path, route);
        };
        
	  $routeProvider
      .when('/', { templateUrl: 'scripts/app/login/login.html', controller: 'loginCtrl'})
      .when('/login', {templateUrl: 'scripts/app/login/login.html', controller: 'loginCtrl'})
      .when('/logout', {templateUrl: 'scripts/app/login/login.html', controller: 'loginCtrl'})
      .when('/forgotPassword', { templateUrl: 'scripts/app/login/forgotPassword.html', controller: 'loginCtrl'})
      .when('/checkEmail', { templateUrl: 'scripts/app/login/checkEmail.html', controller: 'loginCtrl'})
      .when('/resetPassword', { templateUrl: 'scripts/app/login/resetPassword.html', controller: 'loginCtrl'})
      .when('/sessionTimeout', { templateUrl: 'scripts/app/sessionTimeout.html', controller: 'mainCtrl'})
      .whenAuthenticated(
    		  '/dashboard', {
    			  templateUrl: 'scripts/app/main.html',
    			  controller: 'mainCtrl', 
    			  controllerAs: 'mainCtrl'
			  }
      )
      .whenAuthenticated(
		  '/main/dashboard', {
			  templateUrl: 'scripts/app/main.html',
			  controller: 'dashboardCtrl', 
			  controllerAs: 'dashboardCtrl'
		  }
      )
      .whenAuthenticated(
    		  '/worker', {
    			  templateUrl: 'scripts/app/main.html',
    			  controller: 'mainCtrl', 
    			  controllerAs: 'mainCtrl'
			  }
      )
      .whenAuthenticated(
		  '/main/worker', {
			  templateUrl: 'scripts/app/main.html',
			  controller: 'workerCtrl', 
			  controllerAs: 'workerCtrl'
		  }
      )
       .whenAuthenticated(
    		  '/client', {
    			  templateUrl: 'scripts/app/main.html',
    			  controller: 'mainCtrl', 
    			  controllerAs: 'mainCtrl'
			  }
      )
      .whenAuthenticated(
		  '/main/client', {
			  templateUrl: 'scripts/app/main.html',
			  controller: 'clientCtrl', 
			  controllerAs: 'clientCtrl'
		  }
      )
      .otherwise({ redirectTo: '/'});
  }] );
})();

