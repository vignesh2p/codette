(function() {
  'use strict';
/**
 * @ngdoc function
 * @name atrium.controller:dashboardCtrl
 * @description
 * # dashboardCtrl
 * Controller of the atrium
 */
  vApp.controller('dashboardCtrl', [ '$scope','$translate','restService','$translatePartialLoader','$log','$location','$timeout','$controller','$filter',
                                 function($scope, $translate, restService, $translatePartialLoader, $log, $location, $timeout, $controller, $filter) {
	  
	  $translate.refresh();
	  angular.extend(this, $controller('dashboardListController', { $scope: $scope }));
	  angular.extend(this, $controller('dashboardDetailController', { $scope: $scope }));
	  $scope.ids = [];
	  var orderBy = $filter('orderBy');
	  
	  $scope.order = function(predicate) {
		    $scope.predicate = predicate;
		    $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
		    $scope.students = orderBy($scope.students, predicate, $scope.reverse);
	};
	  
	  
	  if(localStorage.getItem("queryparam") != ""){
		  var orgId = localStorage.getItem("queryparam").split('&');
		  for(var i=0;i<orgId.length;i++){
			  $scope.ids.push(orgId[i].split('=')[1]);
		  }
	  }
	  $scope.mainTemplate = function(type1, type2){
		  console.log("type1---"+type1+"----type2-----------"+type2);
	    		$scope.template1={
						     "pageDetails":"scripts/app/dashboard/view/"+type2+".html"
						   };  
    	};
	  
	  $scope.openDeclrations = function(){
		  $timeout(function(){
			  $scope.opendDialog('questions');
		  },500);
	  };
	  
	  $scope.scrollFun=function(lxdialogId){
			  if(lxdialogId == 'engagement'){
				  return "scrollDiv";
			  }
		  };
	  	$scope.orightml = '<p>Hello</p>';
		$scope.htmlcontent = "Hello";
		$scope.disabled = false;
		
	} ]);
 })(); 