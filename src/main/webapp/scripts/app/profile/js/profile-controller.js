/**
 *  Profile Controller
 */

(function() {
  'use strict';
/**
 * @ngdoc function
 * @name atrium.controller:dashboardCtrl
 * @description
 * # dashboardCtrl
 * Controller of the atrium
 */
  vApp.controller('profileController', [ '$scope','$translate','restService','$translatePartialLoader','$log','$location','$timeout','$controller','profileservice',
                                 function($scope, $translate, restService, $translatePartialLoader, $log, $location, $timeout, $controller, profileservice) {
	  
	  $translate.refresh();
	  $scope.ids = [];
	  
	  if(localStorage.getItem("queryparam") != ""){
		  var orgId = localStorage.getItem("queryparam").split('&');
		  for(var i=0;i<orgId.length;i++){
			  $scope.ids.push(orgId[i].split('=')[1]);
		  }
	  }
	
	  $scope.mainTemplate = function(type1,type2){
		  console.log("type1---"+type1+"----type2-----------"+type2);
	    		$scope.template1={
						  //   "pageList":"scripts/app/dashboard/view/"+type1+".html",
						     "pageDetails":"scripts/app/profile/view/"+type2+".html"
						   };  
			};
	  
	  $scope.openDeclrations = function(){
		  $timeout(function(){
			  $scope.opendDialog('questions');
		  },500);
	  };
	  
	  	$scope.orightml = '<p>Hello</p>';
		$scope.htmlcontent = "Hello";
		$scope.disabled = false;
		
		$scope.getProfile = function(){
			var data=profileservice.loaduserDetail();
			data.then(function(success){						 
				$scope.user = success;
			});
			
		};
		
		
		
		$scope.updateProfile = function(user, feild){
			$scope.updateUser = {};
			if(feild == 'contact'){
				$scope.updateUser.contact = angular.copy(user);
			}else if(feild == 'bioGraphy'){
				$scope.updateUser.bioGraphy = angular.copy(user);
			}else if(feild == 'address'){
				$scope.updateUser.address = angular.copy(user);
			}else if(feild == 'experience'){
				$scope.updateUser.experience = angular.copy(user);
			}else if(field == 'emailAddress'){
				$scope.updateUser.emailAddress = angular.copy(user);
			}else if(field == 'dateOJoining'){
				$scope.updateUser.dateOfJoining = angular.copy(user);
			}
			console.log(JSON.stringify($scope.updateUser));
			var data = profileservice.updateProfile($scope.updateUser);
			data.then(function(success){						 
				console.log(success);
			});
		};
		
	} ]);
 })(); 
