(function() {
  'use strict';

  angular
    .module('school-management')
    .directive('acmeNavbar', acmeNavbar);

  /** @ngInject */
  function acmeNavbar() {
    var directive = {
      restrict: 'E',
      templateUrl: 'scripts/app/components/navbar/navbar.html',
      controller: 'mainCtrl',
      bindToController: true
    };

    return directive;
  }

})();
