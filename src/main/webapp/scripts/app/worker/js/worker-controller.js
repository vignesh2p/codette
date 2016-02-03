(function() {
  'use strict';
/**
 * @ngdoc function
 * @name atrium.controller:workerController
 * @description
 * # workerController
 * Controller of the atrium
 */
  vApp.controller('workerController', [ '$scope','$translate','restService','$translatePartialLoader','$log','$location','$timeout','$controller','ntStaffservice', function($scope,$translate,restService,$translatePartialLoader,$log,$location,$timeout,$controller,ntStaffservice) {
	  $translate.refresh();
	    var tabs = [
	                { title: 'Profile', content: "scripts/app/worker/view/profile.html"},
	                { title: 'Engagements',content: "scripts/app/worker/view/engagements.html"},
	                { title: 'Documents',content: "scripts/app/worker/view/documents.html"},
	              ],
	              selected = null,
	              previous = null;
	          $scope.tabs = tabs;
	          $scope.selectedIndex = 2;
	          angular.extend(this, $controller('workerListController', { $scope: $scope }));
	          angular.extend(this, $controller('workerDetailController', { $scope: $scope }));
	          
	          $scope.$watch('selectedIndex', function(current, old){
	            previous = selected;
	            selected = tabs[current];
	            if ( old + 1 && (old != current)) $log.debug('Goodbye ' + previous.title + '!');
	            if ( current + 1 )                $log.debug('Hello ' + selected.title + '!');
	          });
	          $scope.tabNavigation=function(tabLabel){
	        	$scope.mainTemplate('details',tabLabel.toLowerCase());
	          };
	          
	          $scope.mainTemplate = function(type1,type2){
	        	  console.log('type1---------'+type1+'type2------------->'+type2);
	  	    		$scope.template1={
	  						  //   "pageList":"scripts/app/worker/view/"+type1+".html",
	  						     "pageDetails":"scripts/app/worker/view/"+type2+".html"
	  						   };  
	  				 //  angular.element(".org-details-title .dropdown").show();
	  			};
	  			
	  			$scope.religion = [
	  			                   { religion: 'Hindu',      },
	  			                   { religion: 'Christian',    },
	  			                   { religion: 'Muslim',  },
	  			                   { religion: 'Jain',  },
	  			                   { religion: 'Buddhism', }
	  			                   ];
	  			
	  			$scope.community = [
	  			                    { community: 'OC',      },
	  			                    { community: 'BC',    },
	  			                    { community: 'FC',  },
	  			                    { community: 'MBC',  },
	  			                    { community: 'SC/ST', }
	  			                    ];
	  			
	  			$scope.designation = [
	  			                      { designation: 'Principal', },
	  			                      { designation: 'Professor',    },
	  			                      { designation: 'HeadMaster',  },
	  			                      { designation: 'Head of the Department',  }
	  			                     ];
	  			
	  			$scope.tchngStaff = {};
	  			
	  			$scope.save = function(){
	  				console.log("SUBMITTED");
	  				var data = ntStaffservice.createStaff($scope.tchngStaff,'teaching');
	  				data.then(function(success){	
	  					$scope.mainTemplate('','list');
	  					});
	  				};
	  			
	} ]);
 })(); 