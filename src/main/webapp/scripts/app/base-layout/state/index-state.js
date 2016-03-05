
(function () {
    'use strict';

    vApp.config(['$stateProvider', '$httpProvider', '$logProvider', '$urlRouterProvider','$mdThemingProvider', function ($stateProvider, $httpProvider, $log, $urlRouterProvider,$mdThemingProvider) {
        
        var home = {
            name: "home",
            abstract: true,
            views: {
                'left': {
                    templateUrl: 'scripts/app/components/navbar/navbar.html',
                    controller: 'mainCtrl'
                },
                'right': {
                    templateUrl: 'scripts/app/base-layout/view/main.html',
                    controller: 'mainCtrl'
                }
            },
            data: {
                requireLogin: true
            }

        };
       // $urlRouterProvider.otherwise('/');atrium-frontend

        $stateProvider
            .state('app', {
                url: '/',
                views: {
                    'right': {
                        templateUrl: 'scripts/app/login/view/login.html',
                        controller: 'loginController' 
                    }
                },
                data: {
                    requireLogin: false
                }
            })
        .state('login', {
            url: '/login',
            views: {
                'right': {
                    templateUrl: 'scripts/app/login/view/login.html',
                    controller: 'loginController' 
                }
            },
            data: {
                requireLogin: false
            }
        })
         .state('logout', {
                url: '/logout',
                views: {
                    'right': {
                        templateUrl: 'scripts/app/login/view/login.html',
                        controller: 'loginController'
                    }
                },
                data: {
                    requireLogin: false
                }
            })
              .state('forgotPassword', {
                url: '/forgotPassword',
                views: {
                    'right': {
                        templateUrl: 'scripts/app/login/view/forgotPassword.html',
                        controller: 'loginController'
                    }
                },
                data: {
                    requireLogin: false
                }
            })
             .state('checkEmail', {
                url: '/checkEmail',
                views: {
                    'right': {
                        templateUrl: 'scripts/app/login/view/checkEmail.html',
                        controller: 'loginController'
                    }
                },
                data: {
                    requireLogin: false
                }
            })
             .state('resetPassword', {
                url: '/resetPassword',
                views: {
                    'right': {
                        templateUrl: 'scripts/app/login/view/resetPassword.html',
                        controller: 'loginController'
                    }
                },
                data: {
                    requireLogin: false
                }
            })
              .state('sessionTimeout', {
                url: '/sessionTimeout',
                views: {
                    'right': {
                        templateUrl: 'scripts/app/login/base-layout/view/sessionTimeout.html',
                        controller: 'mainCtrl'
                    }
                },
                data: {
                    requireLogin: false
                }
            })
         .state(home)
        .state('home.dashboard', {
            url: '/students',
            parent: 'home',
            views: {
                'viewPage': {
                    templateUrl: 'scripts/app/dashboard/view/dashboard.html',
                    controller: 'dashboardCtrl',
                   
                }
            }
        })
          .state('home.teachingStaffs', {
            url: '/teachingStaffs',
            parent: 'home',
            views: {
                'viewPage': {
                    templateUrl: 'scripts/app/worker/view/t-staff.html',
                    controller: 'workerController',
                   
                }
            }
        })
           .state('home.ntStaffs', {
            url: '/ntStaffs',
            parent: 'home',
            views: {
                'viewPage': {
                    templateUrl: 'scripts/app/ntStaff/view/ntStaffs.html',
                    controller: 'ntStaffController',
                   
                }
            }
        })
        
         .state('home.attendence', {
            url: '/attendence',
            parent: 'home',
            views: {
                'viewPage': {
                    templateUrl: 'scripts/app/attendence/view/attendence.html',
                    controller: 'attendenceController',
                   
                }
            }
        })
        
        .state('home.profile',{
        url: '/profile',
        parent:'home',
        views:{
        	'viewPage': {
        		templateUrl :'scripts/app/profile/view/profile.html',
        		controller:'profileController'
        	}
        }
        })
        
        .state('home.lms',{
        url: '/lms',
        parent:'home',
        views:{
        	'viewPage': {
        		templateUrl :'scripts/app/lms/view/lms.html',
        		controller:'lmsController'
        	}
        }
        })
        
         .state('home.fms',{
        url: '/fms',
        parent:'home',
        views:{
        	'viewPage': {
        		templateUrl :'scripts/app/fms/view/fms.html',
        		controller:'fmsController'
        	}
        }
        })
        
        .state('home.exams',{
        url: '/exams',
        parent:'home',
        views:{
        	'viewPage': {
        		templateUrl :'scripts/app/exam/view/exam.html',
        		controller:'examController'
        	}
        }
        })
        .state('home.logs', {
            url: '/noticeboard',
            parent: 'home',
            views: {
                'viewPage': {
                    templateUrl: 'scripts/app/logs/view/noticeBoard.html',
                    controller: 'noticeBoardController',
                   
                }
            }
        })
        
}] );
})();
