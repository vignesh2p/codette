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
  vApp.controller('examController', [ '$scope','$translate','restService','$translatePartialLoader','$log','$location','$timeout','$controller','$interval',
                                 function($scope, $translate, restService, $translatePartialLoader, $log, $location, $timeout, $controller,$interval) {
	  
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
		
		$scope.user = {
				firstName :'Vignesh',
				lastName : 'Periyanayagam',
				email : 'viki19nesh@gmail.com',
				designation : 'Junior Software Developer',
				address : 'madurai',
				contacts : '9629400474',
				biography : 'First WebApp with Angular'
		}
		var self = this, j= 0, counter = 0;
	    self.mode = 'query';
	    self.activated = true;
	    self.determinateValue = 30;
	    self.determinateValue2 = 30;
	    self.modes = [ ];
		
	    $scope.roleCheck = function(){
			console.log(localStorage.getItem("userRole"));
			if(localStorage.getItem("userRole")=='TEACHING STAFF'){
				$scope.display = false;
			}else{
				$scope.display = true;
			}
		};
	 $scope.createNewLeaveRequest = function (){
			
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
