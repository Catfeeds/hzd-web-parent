define(['common/session', 'service/config', 'common/path-helper', 'components/waitingDialog'], function(session, config, ph, waitingDialog) {
    return ['$rootScope', '$scope', '$window', '$state', '$stateParams', '$timeout', '$http', '$modal', 'defaultService', function($rootScope, $scope, $window, $state, $stateParams, $timeout, $http, $modal, defaultService) {

        // add 'ie' classes to html
        var isIE = !!navigator.userAgent.match(/MSIE/i);
        isIE && angular.element($window.document.body).addClass('ie');
        isSmartDevice($window) && angular.element($window.document.body).addClass('smart');

        // config
        $scope.app = {
            name: '易销云盟后台管理',
            version: '1.0.0',
            // for chart colors
            color: {
                primary: '#7266ba',
                info: '#23b7e5',
                success: '#27c24c',
                warning: '#fad733',
                danger: '#f05050',
                light: '#e8eff0',
                dark: '#3a3f51',
                black: '#1c2b36'
            },
            settings: {
                // themeID: 5,
                // navbarHeaderColor: 'bg-success',
                // navbarCollapseColor: 'bg-white-only',
                // asideColor: 'bg-dark',
                themeID: 1,
                navbarHeaderColor: 'bg-black',
                navbarCollapseColor: 'bg-white-only',
                asideColor: 'bg-black',
                headerFixed: true,
                asideFixed: true,
                asideFolded: false,
                asideDock: false,
                container: false,
                showAsideUser: true
            },
            terminals: []
        }

        //初始化菜单数据
        var initTerminals = function() {
            if (!session.checkIsLogged()) {
                return false;
            }
            waitingDialog.show();
            $timeout(function() {
                var terminals = [];
                $http({
                    method: 'POST',
                    url: config.cms_domain + '/terminal/getTerminalList',
                    data: { pageNo: 1, pageSize: 100 }
                }).then(
                    function (resp) {
                        if (resp) {
                            terminals = resp.data.items;
                            defaultService.setTerminals(terminals);
                            $scope.app.terminals = terminals;
                            //terminals改变，通知下级控制器
                            $scope.$broadcast('terminalsChanged');
                        } else {
                            return serverErrorData;
                        }
                    },
                    function (errResp) {
                        console.error('request terminal list error!');
                    }
                )
                waitingDialog.hide();
            }, 1000);
        };

        initTerminals();

        //当前用户
        //放到$rootScope中，子控制器共用
        $rootScope.curUser = session.getLoginUserInfo() || {};
        $scope.$on('login', function(event, userInfo) {
            $rootScope.curUser = userInfo;
            session.rememberLoginUser(userInfo);
            //登录后处理
            initTerminals();
        });

        function isSmartDevice($window) {
            // Adapted from http://www.detectmobilebrowsers.com
            var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
            // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
            return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
        }

        /**
         * if include current state name or not
         * @param  {string or array} stateName state name or name list
         * @param  {string or array} exclude   the exclude state name or name list
         */
        $scope.includes = function(stateName, exclude) {
            var includeState = false;
            if (Object.prototype.toString.call(stateName) === '[object Array]') {
                stateName.forEach(function(item) {
                    if ($state.includes(item)) {
                        includeState = true;
                        return;
                    }
                });
            } else
                includeState = $state.includes(stateName);

            var matchExclude = false;
            if (exclude) {
                if (Object.prototype.toString.call(stateName) === '[object Array]') {
                    exclude.forEach(function(item) {
                        if (item == $state.current.name) {
                            matchExclude = true;
                            return;
                        }
                    });
                } else
                    matchExclude = ($state.current.name == exclude);
            }
            return includeState && !matchExclude;
        };

        //面包屑
        $rootScope.crumbs = [];
        //面包屑跳转
        $scope.breadGo = function(item) {
            if (item.disabled)
                return false;
            $rootScope.crumbs.splice(item.state.length - item.state.replace(/\./g, '').length, $rootScope.crumbs.length);
            if (item.params) {
                $state.go(item.state, item.params);
            } else {
                $state.go(item.state);
            }
        };

        //参数是不是当前终端
        $scope.isTerminal = function(id) {
            return $stateParams.t == id;
        };

        /**
         * show logout modal dialog
         */
        $scope.logout = function() {
            $modal.open({
                templateUrl: 'view/shared/confirm.html',
                size: 'sm',
                controller: function($scope, $modalInstance) {
                    $scope.confirmData = {
                        text: '确定注销登录？',
                        processing: false
                    };
                    $scope.cancel = function() {
                        $modalInstance.dismiss();
                        return false;
                    }

                    $scope.ok = function() {
                        logout($modalInstance);
                        return true;
                    }
                }
            });
        };

        /**
         * logout
         * @param {object} modalinstance: modal dialog instance
         */
        var logout = function(modalInstance) {
            try {
                $http({
                        url: config.sys_domain + '/logout',
                        method: 'GET'
                    })
                    .then(function(res) {
                        if (!res || res.status != 200)
                            console.error(res ? ('status: ' + res.status + ', msg: ' + res.msg) : 'logout error!');
                    }, function() {
                        console.error('request error!');
                    });
            } catch (err) {
                console.log(err);
            } finally {
                modalInstance.dismiss();
                session.logout();
                $state.go('login', {}, {
                    reload: true
                });
            }
        };
    }];
});
