﻿'use strict';

define([
        'angular',
        'resource',
        'sanitize',
        'ui-router',
        'module/common',
        'components/ui-bootstrap-tpls',
        'controller/main',
        'lazy-load',
        'common/session',
        'components/select.min'
    ],
    function(angular, resource, sanitize, uiRouter, commonModule, uiBootstrap, mainCtrl, lazyLoad, session, select) {
        var app = angular.module('mgr', ['ngResource', 'ngSanitize', 'ui.router', 'mgr.bootstrap', 'ui.bootstrap', 'ui.select'], function($controllerProvider, $provide, $compileProvider, $filterProvider) {
            lazyLoad.init(arguments);
        });

        app.config(function($httpProvider) {
                //跨域请求统一参数配置
                //允许传入cookie值到服务端
                $httpProvider.defaults.withCredentials = true;

                $httpProvider.interceptors.push(['$rootScope', '$q', '$injector', 'toaster', function($rootScope, $q, $injector, toaster) {
                    return {
                        response: function(response) {
                            //统一处理401未授权问题，清cookie并返回首页登录
                            if (response && response.data && response.data.status == 401) {
                                //不能直接注入，有循环引用问题
                                var $state = $injector.get('$state');
                                var stateName = $state.current.name;
                                //保证多个异步接口返回401时，只提示并跳转一次
                                if (stateName != 'login' && !$rootScope.isRedirectingToLogin) {
                                    //正在跳转
                                    //在$stateChangeSuccess时再改为false
                                    $rootScope.isRedirectingToLogin = true;

                                    toaster.pop('error', '登录超时，请重新登录！');
                                    if (stateName == 'login')
                                        stateName = 'home';
                                    $state.go('login', {
                                        r: stateName
                                    }, {
                                        reload: true
                                    });
                                }
                                //不再返回给原始请求
                                var defer = $q.defer();
                                return defer.promise;
                            }
                            return response || $q.when(response);
                        }
                    };
                }]);
            })
            .config(function($urlMatcherFactoryProvider) {
                //不区分大小写
                $urlMatcherFactoryProvider.caseInsensitive(true);
                //取消严格模式(忽略斜杠)
                $urlMatcherFactoryProvider.strictMode(false);
            });

        //启动应用程序
        app.run(['$rootScope', '$location', '$state', '$timeout', function($rootScope, $location, $state, $timeout) {
            $rootScope.$on("$stateChangeStart", function(evt, toState, toParams, fromState, fromParams) {
                // //刷新cookie过期时间
                // session.refreshTicket();
                //如果cookie失效，跳到login页
                if (toState.name != 'login' && !session.checkIsLogged()) {
                    //apply with $timeout
                    //i do not know why...
                    $timeout(function() {
                        $state.go('login', {}, {
                            reload: true
                        });
                    });
                }
            });
            $rootScope.$on("$stateChangeSuccess", function(evt, toState, toParams, fromState, fromParams) {
                if (toState.name == 'login') {
                    $rootScope.isRedirectingToLogin = false;
                    $rootScope.showAll = false;
                } else {
                    $rootScope.showAll = true;
                }
            });
            $rootScope.$on("$stateChangeError", function(event, toState, toParams, fromState, fromParams, error) {
                $timeout(function() {
                    $state.go('error');
                });
            });
        }]);

        app.controller('mainCtrl', mainCtrl);
        return app;
    }
);
