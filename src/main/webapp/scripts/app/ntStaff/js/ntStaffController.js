(function() {
	'use strict';
	/**
	 * @ngdoc function
	 * @name atrium.controller:clientController
	 * @description
	 * # clientController
	 * Controller of the atrium
	 */

	vApp.controller('ntStaffController', [ '$scope','$translate','restService','$translatePartialLoader','$log','$location','$timeout','$controller','ntStaffservice', function($scope,$translate,restService,$translatePartialLoader,$log,$location,$timeout,$controller,ntStaffservice) {
		$translate.refresh();
		var tabs = [
		            { title: 'Users', content: "scripts/app/worker/view/profile.html"},
		            { title: 'Email Templates',content: "scripts/app/worker/view/engagements.html"},
		            { title: 'Engagement Form',content: "scripts/app/worker/view/documents.html"},
		            ],
		            selected = null,
		            previous = null;
		$scope.tabs = tabs;
		$scope.selectedIndex = 2;	          
		$scope.$watch('selectedIndex', function(current, old){
			previous = selected;
			selected = tabs[current];
			if ( old + 1 && (old != current)) $log.debug('Goodbye ' + previous.title + '!');
			if ( current + 1 )                $log.debug('Hello ' + selected.title + '!');
		});

		$scope.usersDisplayByRole = function(){
			$scope.loadUsersByRoleAC("NRL_ADMIN");
			$scope.loadUsersByRoleAD("USER");
			$scope.loadUsersByRoleCA("ADMIN");
			$scope.loadUsersByRoleCMT();
		};
		
		$scope.nonTchngStaff = {};

		$scope.save = function(){
			var data = ntStaffservice.createStaff($scope.nonTchngStaff,'nonTeaching');
			data.then(function(success){	
					$scope.mainTemplate('','list');
					});
		};

		$scope.tabNavigation=function(tabLabel){
			var tablabel = tabLabel.replace(' ','');
			$scope.viewTemplate('details',tablabel.toLowerCase());
		}

		$scope.mainTemplate = function(type){
			console.log("type>>>>>>>>>>>>>>"+type);
			$scope.template={
					"pageView":"scripts/app/ntStaff/view/"+type+".html",
			};  
		};
		$scope.viewTemplate = function(type1,type2){
			$scope.template1={
					"pageList":"scripts/app/ntStaff/view/"+type1+".html",
					"pageDetails":"scripts/app/ntStaff/view/"+type2+".html"
			};  
			angular.element(".org-details-title .dropdown").show();
		};
		$scope.role="IW_user";
		$scope.clientView=function(){
			$scope.mainTemplate("clientAtriumUserView");
		}

		$scope.nonTeachingStaff = function() {
			console.log("INSIDE CONTRLLER----------------"); 
			$scope.loader = true;
			var data = ntStaffservice.getStaffList();
			data.then(function(success){						 
				$scope.staffList = success;
				setTimeout(function(){
					$scope.loader = false;
				},100);
			});
		}

	} ]);
})(); 

vApp.directive('autoComplete',['$http','restService','clientService',function($http,restService,clientService){
	return {
		restrict:'AE',
		scope:{
			selectedTags:'=model'
		},
		templateUrl:'bower_components/angular-autocomplete-tag-input-master/public/views/autocomplete-template.html',
		link:function(scope,elem,attrs){

			scope.suggestions=[];
			scope.selectedTags=[];
			scope.selectedIndex=-1;
			scope.userList_CMT = clientService.getDetails();


			scope.removeTag=function(index){
				scope.selectedTags.splice(index,1);
			}

			scope.search=function(){
				//$http.get(attrs.url+'?term='+scope.searchText).success(function(data){
					for(var i=0; i <scope.userList_CMT.length;i++){
						if(scope.userList_CMT[i].indexOf(scope.searchText)===-1){
							scope.userList_CMT[i].unshift(scope.searchText);
						}
						scope.suggestions=data;
						scope.selectedIndex=-1;
					}
					// });
			}

			scope.addToSelectedTags=function(index){
				if(scope.selectedTags.indexOf(scope.suggestions[index])===-1){
					scope.selectedTags.push(scope.suggestions[index]);
					scope.searchText='';
					scope.suggestions=[];
				}
			}

			scope.checkKeyDown=function(event){
				if(event.keyCode===40){
					event.preventDefault();
					if(scope.selectedIndex+1 !== scope.suggestions.length){
						scope.selectedIndex++;
					}
				}
				else if(event.keyCode===38){
					event.preventDefault();
					if(scope.selectedIndex-1 !== -1){
						scope.selectedIndex--;
					}
				}
				else if(event.keyCode===13){
					scope.addToSelectedTags(scope.selectedIndex);
				}
			}

			scope.$watch('selectedIndex',function(val){
				if(val!==-1) {
					scope.searchText = scope.suggestions[scope.selectedIndex];
				}
			});

		}

	}
}]);