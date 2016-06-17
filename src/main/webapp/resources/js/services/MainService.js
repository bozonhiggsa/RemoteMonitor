(function () {
    'use strict';

    angular.module('mainApp').factory('MainService', MainService);

    MainService.$inject = ['$http', '$q'];
    function MainService($http, $q) {

        return {
            getCurrentData: getCurrentData
        };

        function getCurrentData() {
            return $q(function (resolve, reject) {
                $http({
                    url: 'data/current',
                    method: "GET"
                }).then(function (response) {
                    var data = response.data;
                    resolve(data);

                }, function (reason) {
                    resolve(null);
                })
            });
           
        }
    }
})();
