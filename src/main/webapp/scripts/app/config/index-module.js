var vApp = angular
    .module('school-management', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ngResource', 'ngRoute', 'ngMaterial','pascalprecht.translate', 'lumx', 'treasure-overlay-spinner', 'ngFileUpload','ui.bootstrap','textAngular','ngMdIcons','ui.router','mgcrea.jquery','datePicker','angular-datepicker','angular-ladda','ngImgCrop'])

vApp.directive('resize', function ($window) {
return function (scope, element) {
	var w = angular.element($window);
	scope.getWindowDimensions = function () {
		return { 'h': w.height(), 'w': w.width() };
	};
	scope.$watch(scope.getWindowDimensions, function (newValue, oldValue) {
		scope.windowHeight = newValue.h;
          scope.windowWidth = newValue.w;
          
          scope.style = function () {
			return { 
                  'height': (newValue.h - 100) + 'px'
              };
		};
          
	}, true);

	w.bind('resize', function () {
		scope.$apply();
		console.log('height > '+scope.windowHeight);
  		});
  	}
 });

vApp.run(run);
function run ($rootScope) {
    $rootScope.spinner = {
      active: false,
      on: function () {
        this.active = true;
      },
      off: function () {
        this.active = false;
      }
    };
 }
vApp.config(function($mdThemingProvider) {
	  $mdThemingProvider.definePalette('NRLTHEME', {
	    '50': 'ffebee',
	    '100': 'ffcdd2',
	    '200': '0E7931',
	    '300': 'e57373',
	    '400': 'ef5350',
	    '500': '0073e1',
	    '600': 'e53935',
	    '700': 'd32f2f',
	    '800': 'c62828',
	    '900': 'b71c1c',
	    'A100': 'ffcdd2',
	    'A200': '0E7931',
	    'A400': 'ff1744',
	    'A700': 'd50000',
	    'contrastDefaultColor': 'light',    // whether, by default, text (contrast)
	                                        // on this palette should be dark or light
	    'contrastDarkColors': ['50', '100', '200', '300', '400', 'A100'],
	    'contrastLightColors': undefined    // could also specify this if default was 'dark'
	  });

	  var pagebackground = $mdThemingProvider.extendPalette('NRLTHEME', {
	    'A100': '#F6FAFA',
	    'A200': '#F6FAFA',
	    '200': '#F6FAFA'
	  });
	  
	  $mdThemingProvider.definePalette('pagebackground', pagebackground);
	  
	  
	  $mdThemingProvider.theme('default')
      .primaryPalette('NRLTHEME')
      .accentPalette('yellow')
      .warnPalette('red')
      .backgroundPalette('pagebackground');
	  
});
