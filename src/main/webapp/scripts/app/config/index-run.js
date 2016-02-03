(function() {
  'use strict';

  vApp.run(runBlock);

  /** @ngInject */
  function runBlock($location, $rootScope, $log,$route,$translate, $translatePartialLoader) {

    $log.debug('runBlock end');
    
    $rootScope.$on('$routeChangeError', function (ev, current, previous, rejection) {
        if (rejection && rejection.needsAuthentication === true) {
            var returnUrl = $location.url();
            $log.log('returnUrl=' + returnUrl);
            localStorage.setItem("linkParam",returnUrl);
            $location.path('/login').search({ returnUrl: returnUrl });
        }
    });
    
    $translatePartialLoader.addPart('global');
    $translatePartialLoader.addPart('login');
    //$translatePartialLoader.addPart('label');
    $translatePartialLoader.addPart('error');
    $translate.use('en');
  }

})();
