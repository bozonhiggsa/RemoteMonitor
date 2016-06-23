(function () {
    'use strict';

    angular.module('mainApp').factory('LoginService', LoginService);

    LoginService.$inject = ['$window', '$q', '$http', 'cfg'];
    function LoginService($window, $q, $http, cfg) {

        return {
            sentAutentificationRequest: sentAutentificationRequest,
            saveToken: saveToken,
            getToken: getToken,
            logout: logout

        };

        function logout() {
            $http.post('logout').then(function () {
                delete $http.defaults.headers.common['Auth-Token'];
                cfg.username = '';
                $window.localStorage.clear();
            });
            
        }

        function getToken() {
            try {
                var post = JSON.parse($window.localStorage['jwtToken']);
                return $q.when(post);
            }
            catch (e) {
                return $q.reject(new Error("Token hasn't been found"));
            }

        }

        function saveToken(token) {
            try {
                $window.localStorage['jwtToken'] = JSON.stringify(token);
            }
            catch (e) {
                return $q.reject(new Error("Token save error"));
            }

        }

        function sentAutentificationRequest(request) {
            return $http.post('token', request)
        }
    }
})();