(function () {
    'use strict';

    angular.module('mainApp').factory('LoginService', LoginService);

    LoginService.$inject = ['$window', '$q', '$http'];
    function LoginService($window, $q, $http) {

        return {
            sentAutentificationRequest: function (request) {
                return $http.post('j_spring_security_check', request)
            },
            saveToken: function (token) {
                try {
                    $window.localStorage['jwtToken'] = JSON.stringify(token);
                }
                catch (e) {
                    return $q.reject(new Error("Token save error"));
                }

            },
            getToken: function () {
                try {
                    var post = JSON.parse($window.localStorage['jwtToken']);
                    return $q.when(post);
                }
                catch (e) {
                    return $q.reject(new Error("Token hasn't been found"));
                }

            },
            logout: function () {
                $window.localStorage.clear();
            }

        };
    }
})();