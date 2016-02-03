(function() {
  'use strict';
/**
 * @ngdoc function
 * @name atrium.controller:loginController
 * @description
 * # loginController
 * Controller of the atrium
 */
  vApp.controller('loginController', [ '$scope','$translate','restService','loginService','$translatePartialLoader','$log','$location','temporaryStorage',
                             'LxDialogService','LxNotificationService','loginAuthService','$state', function($scope,$translate,restService,loginService,$translatePartialLoader,
                            		 $log,$location,temporaryStorage,LxDialogService,LxNotificationService,loginAuthService,$state) {
	  $translate.refresh();
	  var currentUserName = "";	
	  localStorage.setItem("isAuthenticated", false);
	  localStorage.setItem("userName", "");
	  localStorage.setItem("userRole", "");
	  $scope.helpDesk="Please describe your problem here and a School Management representative will get back to you as soon as possible";
	  $scope.spin =false;
	  $scope.login = function (user) {
		  $scope.spin =true;
		  var data = loginAuthService.getLoginAuthentication(user);
    	  data.then(function(response){
    		  $scope.loginResponse = response;
    		  $log.debug("Controller Log success"); 
    		  localStorage.setItem("userName", response.firstName + ' ' +response.lastName);
    		  localStorage.setItem("userRole", response.userRole);
    		  loginService.init(true, response.firstName + ' ' +response.lastName);
    		  $scope.authenticationError = false;
    		 // $location.path('/dashboard');
    		  if(localStorage.getItem('userRole')=='TEACHING STAFF' || localStorage.getItem('userRole')=="NON TEACHING STAFF"){
    			  $state.go('home.profile');
    		  }else if(localStorage.getItem('userRole')=='ADMIN'){
    			  $state.go('home.dashboard');
    		  }
    	  },
    	  function(error){
    		  $log.debug("Controller Log promise" + error.status); 
    		  loginService.init(false,'');
    		  $location.path('/login');
    		  $scope.spin=false;
    		  $scope.authenticationError = true;
    	  });
      };
      
      $scope.userEmailAddress = function(userName){
    	  currentUserName = userName;
    	  var data = loginAuthService.mailVerification(userName);
    	  data.then(function(response){
    		  console.log(response.status);
    		  if(response.status ='200'){
    			  temporaryStorage.setDetails(currentUserName);
    			  $location.path('/checkEmail');
    		  }else{
    			  $scope.error = "Email Id does not exists";
    		  }
    	  },
    	  function(error){
    		  $scope.error = "Email Id does not exists";
    	  });
      };
      
      $scope.getMailedTo = function(){
    	  $scope.mailedTo = temporaryStorage.getDetails(currentUserName);
      };
    
      $scope.changedPassword = function(confirmUserSecret){
    	  $scope.currentUserSecret = confirmUserSecret;
    	  $scope.resetCredential={};
    	  $scope.resetCredential.userName = temporaryStorage.getDetails(currentUserName);
    	  $scope.resetCredential.userSecret = $scope.currentUserSecret;
    	  $scope.credential = angular.toJson($scope.resetCredential);
      };
      
		 $scope.emailValidation = function(email)
	        {
			 var emailId = false;
			 $scope.error = "";
			 if(email == undefined || email == ""){
				 emailId = true;
			 }else{
				 emailId =  /^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$/.test(email);
			 }
			 if(emailId == true){
				 $scope.userEmailAddress(email);
			 }else{
				 $scope.error = "Invalid emailid";
			 }
	        };
      
      $scope.inputType = 'password';
      $scope.buttonLabel = 'Show';
	    // Hide & show password function
	    $scope.hideShowPassword = function(){
	      if ($scope.inputType == 'password'){
	    	  $scope.inputType = 'text';
		      $scope.buttonLabel = 'Hide';
	      }else{
	    	  $scope.inputType = 'password';
		      $scope.buttonLabel = 'Show';
	      }
	    }
	    
	    $scope.opendDialog = function(dialogId)
	     {
	         LxDialogService.open(dialogId);
	     };

	     $scope.closingDialog = function()
	     {
	         LxNotificationService.info('Dialog closed!');
	     };
	} ]);
	 
      

  vApp.directive("compareTo", function(){
		    return {
		      require: "ngModel",
		      scope: {
		    	  confirmUserSecret: "=compareTo"
		      },
		      link: function(scope, element, attributes, ngModel) {
	
		        ngModel.$validators.compareTo = function(modelValue) {
		          return modelValue == scope.confirmUserSecret;
		        };
	
		        scope.$watch("confirmUserSecret", function() {
		          ngModel.$validate();
		        });
		      }
		    };
	});

 })(); 
