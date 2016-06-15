(function () {
    'use strict';

    angular.module('mainApp').factory('HomeService', HomeService);

    HomeService.$inject = ['$http'];
    function HomeService ($http) {

        return {
            getBundles: function () {
                return $http.get('getBundles')
            }

        };
    }
})();