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
  vApp.controller('lmsController', [ '$scope','$translate','restService','$translatePartialLoader','$log','$location','$timeout','$controller','$interval','lmsService',
                                 function($scope, $translate, restService, $translatePartialLoader, $log, $location, $timeout, $controller,$interval, lmsService) {
	  
	  $translate.refresh();
	  $scope.ids = [];
	  
	  if(localStorage.getItem("queryparam") != ""){
		  var orgId = localStorage.getItem("queryparam").split('&');
		  for(var i=0;i<orgId.length;i++){
			  $scope.ids.push(orgId[i].split('=')[1]);
		  }
	  }
	  $scope.myDate = new Date();
	  $scope.mainTemplate = function(type1,type2){
		  console.log('type2-------------'+type2);
	    		$scope.template1={
						  //   "pageList":"scripts/app/dashboard/view/"+type1+".html",
						     "pageDetails":"scripts/app/lms/view/"+type2+".html"
						   };  
			};
	  
	  $scope.openDeclrations = function(){
		  $timeout(function(){
			  $scope.opendDialog('questions');
		  },500);
	  };
	  $scope.loader =false;
	  	$scope.orightml = '<p>Hello</p>';
		$scope.htmlcontent = "Hello";
		$scope.disabled = false;
		
		var self = this, j= 0, counter = 0;
	    self.mode = 'query';
	    self.activated = true;
	    self.determinateValue = 30;
	    self.determinateValue2 = 30;
	    self.modes = [ ];
		$scope.roleCheck = function(){
			console.log('role----------->'+localStorage.getItem("userRole"));
			if(localStorage.getItem("userRole")=='TEACHING STAFF'){
				return false;
			}else{
				return true;
			}
		};
		
		$scope.lms ={};	
		
	 $scope.createNewLeaveRequest = function (obj){
		 $scope.loader =true;
		 var data = lmsService.createLeaveRequest(obj);
		 data.then(function(success){	
			 $scope.mainTemplate('', 'list');
			  $scope.loader =false;
			});
		};
	$scope.lmsPending = [];	
	$scope.getPendingLms = function(){
		 var data = lmsService.getLmsList("PENDING");
		 data.then(function(success){
			 console.log(angular.toJson(success));
			 $scope.lmsPending = success;
			  $scope.loader =false;

			});
	};
	$scope.getHistoryLms = function(){
		var data = lmsService.getLmsList("HISTORY");
		 data.then(function(success){	
			 console.log('History-------'+angular.toJson(success));
			 $scope.lmsHistory = success;
			  $scope.loader =false;

		});
	};
	
	$scope.updateLms = function(obj){
		$scope.toUpdateLms = [];
		for(var i=0 ; i<obj.length ; i++){
			var lms ={};  lms.staff={}; 
			lms.id = angular.copy(obj[i].id);
			lms.staff.id = angular.copy(obj[i].staff.id);
			lms.approveStatus = angular.copy(obj[i].approveStatus);
			$scope.toUpdateLms.push(lms);
		}
		var data = lmsService.updateLMS($scope.toUpdateLms);
		data.then(function(success){
			console.log(success);
			// $scope.mainTemplate('', 'list');
			$scope.getPendingLms();
			$scope.getHistoryLms();
			  $scope.loader =false;

		});
	};
	
	
	
		 $interval(function() {
		      self.determinateValue += 1;
		      self.determinateValue2 += 1.5;
		      if (self.determinateValue > 100) self.determinateValue = 30;
		      if (self.determinateValue2 > 100) self.determinateValue2 = 30;
		        // Incrementally start animation the five (5) Indeterminate,
		        // themed progress circular bars
		        if ( (j < 2) && !self.modes[j] && self.activated ) {
		          self.modes[j] = (j==0) ? 'buffer' : 'query';
		        }
		        if ( counter++ % 4 == 0 ) j++;
		        // Show the indicator in the "Used within Containers" after 200ms delay
		        if ( j == 2 ) self.contained = "indeterminate";
		    }, 100, 0, true);
		    $interval(function() {
		      self.mode = (self.mode == 'query' ? 'determinate' : 'query');
		    }, 7200, 0, true);
		
	} ]);
 })(); 
