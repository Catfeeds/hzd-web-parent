define(['service/config'], function(config) {
    return ['sysService', ['$http', '$q', function($http, $q) {
        var serverErrorData = {
            status: 500,
            msg: '服务器连接失败，请检查服务是否可用或联系管理员！'
        };

        return {
            /**
             * search role list
             * @param  {object} condition [query condition]
             */
            searchRole: function(condition) {
                return $http({
                        method: "POST",
                        url: config.sys_domain + '/role/page',
                        data: condition,
                        withCredentials: true,
                        crossDomain: true
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * get role detail info from server
             * @param  {int} id [role id]
             */
            getRole: function(id) {
                return $http({
                        method: "GET",
                        url: config.sys_domain + '/role/' + id
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * add or update role info
             * @param  {object} data   [role object]
             * @param  {string} method [post/put]
             */
            saveRole: function(data, method) {
                return $http({
                        method: method,
                        url: config.sys_domain + '/role',
                        data: data
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * search user list
             * @param  {object} condition [query condition]
             */
            searchUser: function(condition) {
                return $http({
                        method: "POST",
                        url: config.sys_domain + '/user/page',
                        data: condition
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * get department list
             */
            getDepartmentList: function() {
                return $http({
                        url: config.sys_domain + '/department/getList',
                        method: 'GET',
                        withCredentials: false
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });

            },

            /**
             * get role list
             */
            getRoleList: function() {
                return $http({
                        url: config.sys_domain + '/role/getRoleList',
                        method: 'GET',
                        withCredentials: false
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * add or update user info
             * @param  {object} data   [user object]
             * @param  {string} method [post/put]
             */
            saveUser: function(data, method) {
                return $http({
                        method: method,
                        url: config.sys_domain + '/user',
                        data: data
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * get user detail info from server
             * @param  {int} id [user id]
             */
            getUser: function(id) {
                return $http({
                        method: "GET",
                        url: config.sys_domain + '/user/' + id
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * delete user
             * @param  {int} id [user id]
             */
            delUser: function(id) {
                return $http({
                        url: config.sys_domain + '/user/del/' + id,
                        method: "DELETE"
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * search system log
             * @param  {object} condition [search condition]
             */
            searchLog: function(condition) {
                return $http({
                        method: "POST",
                        url: config.sys_domain + '/sysLog/list/page',
                        data: condition
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * search meta data
             * @param  {object} condition [search condition]
             */
            searchMeta: function(condition) {
                return $http({
                        method: "POST",
                        url: config.sys_domain + '/metadata/list/page',
                        data: condition
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * change password
             * @param  {object} condition [search condition]
             */
            searchMeta: function(condition) {
                return $http({
                        method: "POST",
                        url: config.sys_domain + '/metadata/list/page',
                        data: condition
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            },

            /**
             * change my password
             * @param  {object} model [password object]
             */
            changePsw: function(model) {
                return $http({
                        method: 'POST',
                        url: config.sys_domain + "/user/changePsw",
                        data: model
                    })
                    .then(function(res) {
                        return res ? res.data : serverErrorData;
                    }, function(res) {
                        return $q.reject(res);
                    });
            }
        };
    }]];
});
