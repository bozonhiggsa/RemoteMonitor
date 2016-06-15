(function () {
    'use strict';

    angular.module('mainApp').controller('HomeController', HomeController);

        HomeController.$inject = ['$scope', 'cfg', 'LoginService', '$state'];
        function HomeController ($scope, cfg, LoginService, $state) {
            $scope.bundles = cfg.bundles;
            $scope.username = cfg.username;

            $scope.logout = function () {
                LoginService.logout();
                $state.go('login');
            };


        }
})();