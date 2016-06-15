(function () {
    'use strict';

    angular.module('mainApp').controller('MainController', MainController);

    MainController.$inject = ['$scope', 'cfg', '$state'];
    function MainController($scope, cfg, $state) {
        $scope.bundles = cfg.bundles;

        $scope.goArchivePage = function () {
            $state.go('home.archive');
        };

        $scope.goAdminPage = function () {
            $state.go('home.admin');
        }
    }
})();