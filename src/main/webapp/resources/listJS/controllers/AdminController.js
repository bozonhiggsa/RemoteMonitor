(function () {
    'use strict';


    angular.module('mainApp').controller('AdminController', AdminController);

    AdminController.$inject = ['$scope', 'cfg', 'AdminService', '$state'];
    function AdminController($scope, cfg, AdminService, $state) {
        $scope.bundles = cfg.bundles;

        $scope.saveNewUserData = function () {
            AdminService.saveNewUserData().then(function (result) {
                var a = result;
            }, function (reason) {
                if (reason.data.message == 'Valid token not found' || reason.data.message == 'Access denied') {
                    $state.go('login', {message: reason.data.message})
                }
            });
        }

    }
})();