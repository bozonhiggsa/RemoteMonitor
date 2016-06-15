(function () {
    'use strict';

    angular.module('mainApp').factory('AdminService', AdminService);

    AdminService.$inject = ['$http'];
    function AdminService ($http) {

        return {
            saveNewUserData: function (request) {
                return $http.post('index/restricted/save-user', request);
            }
        };
    }
})();