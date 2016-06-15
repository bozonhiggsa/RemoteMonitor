(function () {
    'use strict';

    angular.module('mainApp').factory('ArchiveService', ArchiveService);

    ArchiveService.$inject = ['$http'];
    function ArchiveService ($http) {

        return {
            getTableData: function () {
                return $http.post('index/get-archive-data');
            }
        };
    }
})();