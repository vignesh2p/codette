/**
 * @ngdoc function
 * @name dtsAngular.controller:organizationCtrl
 * @description
 * # organizationCtrl
 * Controller of the dtsAngular
 */
(function() {
  'use strict';
/**
 * @ngdoc function
 * @name atrium.controller:mainCtrl
 * @description
 * # mainCtrl
 * Controller of the atrium
 */
  var htmlText="";

  vApp.controller('mainCtrl', [ '$scope','$q', '$compile', '$controller', '$templateCache', '$http', '$rootScope','$timeout','$translate','$location','$mdSidenav','$mdUtil','$log','LxDialogService','LxNotificationService','Upload','LxProgressService','$window','restService','$interval','$document','loginService', 'loginAuthService' , '$state', function($scope,$q, $compile, $controller, $templateCache, $http, $rootScope,$timeout,$translate,$location,$mdSidenav,$mdUtil,$log,LxDialogService,LxNotificationService,Upload,LxProgressService,$window,restService,$interval,$document,loginService,loginAuthService,$state) {

	var location= window.location.href.split('#')[1];
    $scope.path=window.location.href.split('#')[1];
    $scope.userName = localStorage.getItem("userName");
    $scope.userRole = localStorage.getItem("userRole")
    var queryparam ="";
    if(localStorage.getItem("linkParam") != "" && localStorage.getItem("linkParam") != null){
    	queryparam = localStorage.getItem("linkParam").split('?')[1];
    	$location.path(localStorage.getItem("linkParam").split('?')[0]);
    	localStorage.setItem("linkParam", "");
    }else{
    	queryparam = $scope.path.split('?')[1];
    }
	if(queryparam != undefined){
	    localStorage.setItem("queryparam", queryparam);
	    $location.url($location.path());
	}else{
		localStorage.setItem("queryparam", "");
		queryparam="";
    }
    $('.modal-trigger').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .5, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
        ready: function() { alert('Ready'); }, // Callback for Modal open
        complete: function() { alert('Closed'); } // Callback for Modal close
      }
    );
    $('.modal-trigger').leanModal();
    $scope.selectedIndex = 0;
    
    $scope.statusClass = function(text){
    	if(text == 'APPROVED'){
    		return 'flag-green';
    	}else if(text == 'DECLINED'){
    		return 'flag-red';
    	}
    };
    
    
    $scope.rolePermission = function(){
    	
    	  var url ='/api/getUserRoles';
		  var data = restService.restCall("",url,'GET');
	  	  data.$promise.then(function(response){
	  		  $scope.permission = response;
	  	  },
	  	  function(error){
	  	  });
    };
    
    $scope.loadReligionList =function(){
		  var url ='/api/common/dropdown/religion';
		  var data = restService.restCall("",url,'GETLIST');
		  data.$promise.then(function(response){
			  $scope.religionList = response;
		  },
		  function(error){
		  });
    };
    
    $scope.loadStandardList =function(){
		  var url ='api/common/dropdown/standard';
		  var data = restService.restCall("",url,'GETLIST');
		  data.$promise.then(function(response){
			  $scope.standardList = response;
		  },
		  function(error){
		  });
  };
    
  $scope.loadSectionList =function(){
	  var url ='api/common/dropdown/section';
	  var data = restService.restCall("",url,'GETLIST');
	  data.$promise.then(function(response){
		  $scope.sectionList = response;
	  },
	  function(error){
	  });
};
    
    $scope.loadDesignationList =function(){
		  var url ='/api/common/dropdown/designation';
		  var data = restService.restCall("",url,'GETLIST');
		  data.$promise.then(function(response){
			  $scope.designationList = response;
		  },
		  function(error){
		  });
  };
    $scope.loadCommunityList =function(){
		  var url ='/api/common/dropdown/community';
		  var data = restService.restCall("",url,'GETLIST');
		  data.$promise.then(function(response){
			  $scope.communityList = response;
		  },
		  function(error){
		  });
  };
    
    
    $scope.changeLanguage = function (key) {
	    $translate.use(key);
	    $translate.refresh();
	  };
	  $scope.selectedClass = function(id){
		  $("#dashboard").parent().addClass("selected-grey");
	    };
	    $scope.addSelectedClass=function(selected){
	    	 if(selected.toLowerCase() == $scope.path.split('/')[1]){
	 	        setTimeout(function(){
	 	        	angular.element("#"+selected).find(".removeClass").addClass("selected-grey");
	 	        	},100);
	    	 }
	    }
	    $scope.changeClass = function(id){
	    	angular.element(".removeClass").removeClass("selected-grey");
	        angular.element("#"+id).find(".removeClass").addClass("selected-grey");
	    };
	 //$translate.use('fr');
	 $translate.refresh();
	 $scope.itemsList=[1,2,3,4,5];
    
	 /*$scope.tabNavigation=function(tabLabel){
		 var tablabel = tabLabel.replace(' ','');
	     $scope.mainTemplate('details',tablabel.toLowerCase());
       }
	 */
	/* 
	 $scope.mainTemplate = function(type1,type2){
	    	var path= window.location.href.split('#')[1];
	    	var folder = path.split('/');
	    		$scope.template1={
						     "pageList":"scripts/app/"+folder[2]+"/"+type1+".html",
						     "pageDetails":"scripts/app/"+folder[2]+"/"+type2+".html"
						   };  
				   angular.element(".org-details-title .dropdown").show();
			};
		 $scope.viewTemplate = function(type){
			 var splitUrl=location.lastIndexOf("/");
			 var folderPath=location.slice(splitUrl);
					   $scope.template={
							     "about":"scripts/app/organization/"+type+".html",
							  };
					   angular.element(".org-details-title .dropdown").show();
			};*/
     $scope.showLinearProgress = function()
     {
         LxProgressService.linear.show('#5fa2db', '#progress');
     };

     $scope.hideLinearProgress = function()
     {
         LxProgressService.linear.hide();
     };
     
     $scope.dynamicFields = function(){
		  var url ='/api/dashboard/dynamicFields';
		  var data = restService.restCall("",url,'GET');
    	  data.$promise.then(function(response){
    		  $scope.dyanamicFields = response;
    	  });
     };
     
     $scope.opendDialog = function(dialogId)
     {
         $scope.dialogId = dialogId;
         $timeout(function(){
        	 LxDialogService.open(dialogId);
         },200);
     };
    
     $scope.closingDialog = function()
     {
         
     };
    $scope.dyanamicFields={
    		    "questions": [
    		                  {
    		                      "qno": "1",
    		                      "question": "Question1???",
    		                      "optionValue": true,
    		                      "type":"isOption",
    		                      "typeList": [
    		                          {
    		                              "options": "yes"
    		                          },
    		                          {
    		                              "options": "no"
    		                          }
    		                      ],
    		                      "multipleChoiceValue": false,
    		                      "multipleChoice": [],
    		                      "isFreeText": false
    		                  },
    		                  {
    		                      "qno": "2",
    		                      "question": "Question2???",
    		                      "optionValue": false,
    		                      "type":"isMultipleChoice",
    		                      "option": [],
    		                      "multipleChoiceValue": true,
    		                      "typeList": [
    		                          {
    		                              "options": "option1"
    		                          },
    		                          {
    		                              "options": "option2"
    		                          }
    		                      ],
    		                      "isFreeText": false
    		                  },
    		                  {
    		                      "qno": "3",
    		                      "question": "Question3???",
    		                      "optionValue": false,
    		                      "type":"isFreeText",
    		                      "typeList": [],
    		                      "multipleChoiceValue": false,
    		                      "multipleChoice": [],
    		                      "isFreeText": true
    		                  }
    		              ]
    		          };
   
    $scope.logout = function(){
	var data = loginAuthService.logout();
  	data.then(function(response){
  		  $log.debug("Controller Logout success"); 
  		  console.log("inside logout function");
  		  $scope.authenticationError = false;
  		  loginService.init(false,'');
  		 // $location.path('/login');
  		  $state.go('logout');
  	  },
  	  function(error){
  		  $log.debug("Controller Logout error" + error.status); 
  		  $location.path($scope.returnUrl);
  		  $scope.authenticationError = false;
  	  });
  };

    
    $scope.checkAdmin = function(){
    	if($scope.userRole == 'ADMIN'){
    		return true;
    	}else{
    		return false;
    	}
    };
    
    $scope.checkTeachingStaff = function(){
    	if($scope.userRole == 'TEACHING STAFF'){
    		return true;
    	}else{
    		return false;
    	}
    };
    
    $scope.checkNonTeachingStaff = function(){
    	if($scope.userRole == 'NON TEACHING STAFF'){
    		return true;
    	}else{
    		return false;
    	}
    };
    
   angular.forEach($scope.dyanamicFields.questions, function(value , key) {
    	if(value.type=="isOption"){
    		$scope.isOption=value.typeList;
    	}
    	if(value.type=="isMultipleChoice"){
    		$scope.isMultipleChoice=value.typeList;
    	}
    	if(value.type=="isFreeText"){
    		$scope.isFreeText=value.typeList;
    	}
    });
    
    $scope.toModel= function(data, callback)
    {
        if (data) {
            callback(data.option);
        }
        else
        {
            callback();
        }
    };		  
    $scope.dropdown={};
	  $scope.toSelection= function(data,callback)
	    {
		  $scope.dropdown.option=  data;
	        if (data) {
	            callback($scope.dropdown);
	        }
	        else{
	            callback();
	        }
	    };	
	    $scope.pw = '';
	    
	    var SESSION_BANNER_TIMEOUT_MINUTES = 5;
	    var timer;
	    var sessionTimer;
	    var SESSION_TIMEOUT = 1800000;
	    var intervalFlag= false;
	     //Method to set session timeout 
	     $scope.setSessionTimeout = function(){
	    	var minutes = (SESSION_TIMEOUT/60000) % 60;
	    	var seconds = (SESSION_TIMEOUT % 60000) / 1000;
	    	if (minutes < 10 && minutes.length != 2) minutes = '0' + minutes;
	    	if (seconds < 10 && seconds.length != 2) seconds = '0' + seconds;
	    	sessionTimer = minutes + ":" + seconds;
	    	 var countDown = document.getElementById("countDown");
	    	    if( countDown != undefined && countDown != null)
	    	        countDown.innerHTML = minutes + ":" + seconds; 	
	    	    $scope.setInt();
	    };

	    //Method for Session time calculator
 var interval = "";
	$scope.setInt=function(){
	     interval = $interval(function() {
	    	 var location1= window.location.href.split('#')[1];
	    	 if(location1 == '/' || location1 == '/login' || location1 == '/logout'){
	    		 $scope.stop();
	    		 //$document.find('body').off('mousemove keydown DOMMouseScroll mousewheel mousedown touchstart');
	    		 $document.find('body').off('keydown');
	    		 $window.location.reload();
	    	 }else{
	    		 if(intervalFlag == true){
	    			 intervalFlag=false;
	    			 $scope.setSessionTimeout();
	    			 $interval.cancel(interval);
	    		 }
	    	    	 var conquer = sessionTimer.split(':');
	    	    	 var minutes = parseInt(conquer[0], 10);
	    	    	 var seconds = parseInt(conquer[1], 10);
	    	    	 var minute = "";
	    	        seconds -= 1;
	    	        if (minutes < 0) return clearInterval(interval);
	    	        if (seconds < 0 && minutes != 0) {
	    	            minutes -= 1;
	    	            seconds = 59;
	    	        }
	    	        if (minutes < 10 && minutes.length != 2) minutes = '0' + minutes;
	    	        if (seconds < 10 && length.seconds != 2) seconds = '0' + seconds;
	    	        sessionTimer = minutes + ":" + seconds;
	    	        
	    	        $('#countDown').html(minutes + ':' + seconds);
	    	        
	    	        if(minutes != 0){
	    	        	$('.second').css("display","none");
	    	        	$('.minute').css("display","inline");
	    	        }else{
	    	        	$('.second').css("display","inline");
	    	        	$('.minute').css("display","none");
	    	        }
	    	        if(minutes >= SESSION_BANNER_TIMEOUT_MINUTES && seconds >= 0){
	    	        	$('.countDownRow').css("display","none");
	    	        	countDown.innerHTML = null;
	    	        }else if(minutes <= SESSION_BANNER_TIMEOUT_MINUTES && seconds <= 59){
	    	            if(minutes == 0){
	    	            	 countDown.innerHTML = seconds;
	    	            }else{
	    	            	countDown.innerHTML = minutes + ":" + seconds;
	    	            }
	    	            $('.countDownRow').css("display","block");
	    	        }
	    	        
	    	        if(minutes == 0 && seconds == 0){
	    	        	clearInterval(interval);
	    	    	     var url ='/api/logout';
	    	    	     var data = restService.restCall("",url,'GET');
	    	    	     data.$promise.then(function(response){
	    	       		  $log.debug("Controller Logout success"); 
	    	       		  console.log("inside logout function");
	    	       		  $scope.authenticationError = false;
	    	       		  loginService.init(false,'');
	    	       		  $location.path('/sessionTimeout');
		    	    		 $scope.stop();
		    	    		 //$document.find('body').off('mousemove keydown DOMMouseScroll mousewheel mousedown touchstart');
		    	    		 $document.find('body').off('keydown mousedown');
		    	    		 $window.location.reload();
	    	       	  });
	    	        };
	    	 }
	        
	    }, 1000);
	 };
	    $scope.stop = function(){
	    	if (angular.isDefined(interval)) {
   	    	   $interval.cancel(interval);
   	    	   interval = undefined;
    	   }
	    };
	    
	    //$document.find('body').on('mousemove keydown DOMMouseScroll mousewheel mousedown touchstart',checkAndResetIdle);
	    $document.find('body').on('keydown mousedown',checkAndResetIdle);
	    function checkAndResetIdle(){
	    	intervalFlag=true; 
	    	//$scope.checkInterval();
	    }
	    
	    $scope.toggleLeft = function() {
	        $mdSidenav('left').toggle()
	                          .then(function(){
	                              $log.debug("toggle left is done");
	                          });
	      };
  } ]);
  
 
  
  vApp.directive('myOptionDirective', function () {
	    return {
	    	transclude : true,
	    	templateUrl:'scripts/app/dynamicFormFields/view/options.html',
	    	bindToController:true,
	    	controller: 'mainCtrl'
	    };
	});
	vApp.directive('myMultiplechoiceDirective', function () {
	    return {
	    	transclude : false,
	    	templateUrl:'scripts/app/dynamicFormFields/view/mutipleChoices.html',
	        controller: 'mainCtrl',
	    };
	});
	vApp.directive('myFreetextDirective', function () {
	    return {
	    	transclude : false,
	        //template:'<div>Reason<textarea></textarea></div>',
	    	templateUrl:'scripts/app/dynamicFormFields/view/freeText.html',
	    };
	});
	
	
	vApp.directive('checkStrength', function () {

	    return {
	        replace: false,
	        restrict: 'EACM',
	        link: function (scope, iElement, iAttrs) {

	            var strength = {
	                colors: ['#F00', '#F90', '#FF0', '#9F0', '#0F0'],
	                mesureStrength: function (p) {

	                    var _force = 0;                    
	                    var _regex = /[$-/:-?{-~!"^_`\[\]]/g;
	                    var _lowerLetters = /[a-z]+/.test(p);                    
	                    var _upperLetters = /[A-Z]+/.test(p);
	                    var _numbers = /[0-9]+/.test(p);
	                    var _symbols = _regex.test(p);
	                    var _flags = [_lowerLetters, _upperLetters, _numbers, _symbols];                    
	                    var _passedMatches = $.grep(_flags, function (el) { return el === true; }).length;                                          
	                    if(p != undefined && _lowerLetters == true){
	                    	$("#lower").css({"color": "#00ff00"});
	                    }else{
	                    	$("#lower").css({"color": "black"});
	                    }
	                    if(p != undefined && _upperLetters == true){
	                    	$("#upper").css({"color": "#00ff00"});
	                    }else{
	                    	$("#upper").css({"color": "black"});
	                    }
	                    if(p != undefined && _numbers == true){
	                    	$("#numbers").css({"color": "#00ff00"});
	                    }else{
	                    	$("#numbers").css({"color": "black"});
	                    }
	                    if(p != undefined && _symbols == true){
	                    	$("#special").css({"color": "#00ff00"});
	                    }else{
	                    	$("#special").css({"color": "black"});
	                    }
	                    if(p != undefined && p.length >= 7){
	                    	$("#seven").css({"color": "#00ff00"});
	                    }else{
	                    	$("#seven").css({"color": "black"});
	                    }
	                    
	                    _force += 2 * p.length + ((p.length >= 10) ? 1 : 0);
	                    _force += _passedMatches * 10;
	                        
	                    // penality (short password)
	                    _force = (p.length <= 6) ? Math.min(_force, 10) : _force;                                      
	                    
	                    // penality (poor variety of characters)
	                    _force = (_passedMatches == 1) ? Math.min(_force, 10) : _force;
	                    _force = (_passedMatches == 2) ? Math.min(_force, 20) : _force;
	                    _force = (_passedMatches == 3) ? Math.min(_force, 40) : _force;
	                    
	                    return _force;

	                },
	                getColor: function (s) {

	                    var idx = 0;
	                    if (s <= 10) { idx = 0; }
	                    else if (s <= 20) { idx = 1; }
	                    else if (s <= 30) { idx = 2; }
	                    else if (s <= 40) { idx = 3; }
	                    else { idx = 4; }

	                    return { idx: idx + 1, col: this.colors[idx] };

	                }
	            };

	            scope.$watch(iAttrs.checkStrength, function () {
	                if (scope.pw === '') {
	                    iElement.css({ "display": "none"  });
	                    $("li").css({"color": "black"});
	                } else {
	                    var c = strength.getColor(strength.mesureStrength(scope.pw));
	                    iElement.css({ "display": "inline" });
	                    iElement.children('li').css({ "background": "#DDD" }).slice(0, c.idx).css({ "background": c.col });
	                }
	            });

	        },
	        template: '<li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li>'
	    };

	});
  vApp.directive('passwordValidate', function () {
	    return {
	        require: 'ngModel',
	        link: function (scope, elm, attrs, ctrl) {
	            ctrl.$parsers.unshift(function (viewValue) {

	                scope.pwdValidLength = (viewValue && viewValue.length >= 8 ? 'valid' : undefined);
	                scope.pwdHasLetter = (viewValue && /[A-z]/.test(viewValue)) ? 'valid' : undefined;
	                scope.pwdHasNumber = (viewValue && /\d/.test(viewValue)) ? 'valid' : undefined;

	                if (scope.pwdValidLength && scope.pwdHasLetter && scope.pwdHasNumber) {
	                    ctrl.$setValidity('pwd', true);
	                    return viewValue;
	                } else {
	                    ctrl.$setValidity('pwd', false);
	                    return undefined;
	                }

	            });
	        }
	    };
	});
  
})(); 

(function () {
	  'use strict';
	  vApp.controller('DemoCtrl', ['$timeout','$q','$log','restService','$scope','$http','$element',function($timeout,$q,$log,restService,$scope,$http,$element){
	    var self = this;
	    self.searchTextChange   = searchTextChange;
	    $scope.search_timeout =undefined;
	    function searchTextChange(text) {
	    	$scope.text=text;
	    	$element.bind("keydown keypress", function (event) {
	    	if(event.which != 123){
	    		$scope.$apply(function (){
				    if($scope.search_timeout != undefined) {
				           clearTimeout($scope.search_timeout);
				       }
				    else{
				    	$scope.search_timeout =  $timeout(function(){
				    		$scope.search_timeout = undefined;
							  var url ='/api/dashboard/repository?text='+$scope.text;
							  var data = restService.restCall("",url,'GETLIST');
					    	  data.$promise.then(function(response){
					    		  $scope.repos = response;
					    	  });
				    	},600);
				    };
	    		});
	    	
		   };
	    });
	    };
	  }]);
})();
