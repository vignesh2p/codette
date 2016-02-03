(function() {
  'use strict';
/**
 * @ngdoc service
 * @name atrium.factory:loginService
 * @description
 * # loginService
 * Factory in the atrium.
 */
  vApp.factory('loginService', [ '$rootScope','$resource','$q','$log', function( $rootScope,$resource,$q,$log){
		return new Login($rootScope, $resource,$q,$log);
  }] );
 
function Login( rootScope,resource,$q,$log ) {
	var loggedInUser = {
            isAuthenticated: false,
            name: ''
    };
	rootScope.loggedInUser = loggedInUser; 
	$log.debug("Controller isAuthenticated int " + loggedInUser.isAuthenticated); 
	this.init = function (isAuthenticated, userName) {
		$log.debug("Controller isAuthenticated" + isAuthenticated); 
    	loggedInUser.isAuthenticated = isAuthenticated;
    	localStorage.setItem("isAuthenticated", isAuthenticated);
    	if(localStorage.getItem("isAuthenticated") == "false"){
  		  localStorage.setItem("userName", "");
		  localStorage.setItem("userRole", "");
    	}
    	//localStorage.getItem("isAuthenticated");
    	loggedInUser.name = userName;
    };
    
    this.isAuthenticated = function () {
        return loggedInUser.isAuthenticated;
    };
}
})();
