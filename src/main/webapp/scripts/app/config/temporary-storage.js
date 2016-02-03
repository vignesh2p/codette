(function(){
	'use strict';
	/**
	 * @ngdoc service
	 * @name atrium.controller:temporaryStorage
	 * @description
	 * # temporaryStorage
	 * Service in the atrium.
	 */
	 /** @ngInject */
	vApp.factory('temporaryStorage', function() {
		 var savedData = {};
		 function set(data) {
		   savedData = data;
		 }
		 function get() {
		  return savedData;
		 }

		 return {
		  setDetails: set,
		  getDetails: get
		 };

		});
}());