(function() {
  'use strict';

  vApp.config(config);

  /** @ngInject */
  function config($logProvider, toastr,$translateProvider, $translatePartialLoaderProvider) {
    // Enable log
    $logProvider.debugEnabled(true);
    
    
    $translateProvider.useLoader('$translatePartialLoader', {
        urlTemplate: 'i18n/{lang}/{part}.json'
      });
    $translateProvider.useSanitizeValueStrategy('escaped');
    $translateProvider.preferredLanguage('en');
    
  
    // Set options third-party lib
    toastr.options.timeOut = 3000;
    toastr.options.positionClass = 'toast-top-right';
    toastr.options.preventDuplicates = true;
    toastr.options.progressBar = true;
  }

})();
