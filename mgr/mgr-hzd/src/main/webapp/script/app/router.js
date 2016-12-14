'use strict';
define(['app', 'lazy-load'], function(app, lazyLoad) {
    return app.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$controllerProvider',
        function($stateProvider, $urlRouterProvider, $locationProvider, $controllerProvider) {
            $urlRouterProvider.otherwise('/404');
            $stateProvider

            // system management router begin
            .state('sys', lazyLoad.config('/sys', 'view/shared/blank.html', 'controller/sys/sys', { directives: [], services: [], filters:[] }, true))
            .state('sys.role', lazyLoad.config('/role', 'view/shared/blank.html', 'controller/sys/role/role', { directives: [], services: [], filters:[] }, true))
            .state('sys.role.list', lazyLoad.config('/list', 'view/sys/role/list.html', 'controller/sys/role/list', { directives: [], services: ['service/sysService'], filters:[] }))
            .state('sys.user', lazyLoad.config('/user', 'view/shared/blank.html', 'controller/sys/user/user', { directives: [], services: [], filters:[] }, true))
            .state('sys.user.list', lazyLoad.config('/list', 'view/sys/user/list.html', 'controller/sys/user/list', { directives: [], services: ['service/sysService'], filters:[] }))
            .state('sys.log', lazyLoad.config('/log', 'view/shared/blank.html', 'controller/sys/log/log', { directives: [], services: [], filters:[] }, true))
            .state('sys.log.list', lazyLoad.config('/list', 'view/sys/log/list.html', 'controller/sys/log/list', { directives: [], services: ['service/sysService'], filters:[] }))
            .state('sys.meta', lazyLoad.config('/meta', 'view/shared/blank.html', 'controller/sys/meta/meta', { directives: [], services: [], filters:[] }, true))
            .state('sys.meta.list', lazyLoad.config('/list', 'view/sys/meta/list.html', 'controller/sys/meta/list', { directives: [], services: ['service/sysService'], filters:[] }))
            // system management router end
            
            .state('my', lazyLoad.config('/my', 'view/shared/blank.html', 'controller/my/my', { directives: [], services: [], filters:[] }, true))
            .state('my.password', lazyLoad.config('/password', 'view/my/password.html', 'controller/my/password', { directives: [], services: ['service/sysService'], filters:[] }))



            ;

            //单独配置控制器
            lazyLoad.configCtrls([
                {name: 'PageCtrl', path:'controller/cms/page/edit'},
                {name: 'TerminalCtrl', path:'controller/cms/terminal/edit'},
                {name: 'RecommendCtrl', path: 'controller/cms/recommend/edit_p'},
                {name: 'RateCtrl', path: 'controller/cms/recommend/rate'},
                {name: 'LoadingCtrl', path:'controller/shared/loading'}
            ]);
        }
    ]);
});
