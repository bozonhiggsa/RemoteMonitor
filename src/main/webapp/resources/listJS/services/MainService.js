(function () {
    'use strict';

    angular.module('mainApp').factory('MainService', MainService);

    MainService.$inject = ['$http'];
    function MainService($http) {

        return {};
    }
})();
