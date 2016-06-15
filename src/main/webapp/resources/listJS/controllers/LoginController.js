(function () {
    'use strict';

    angular.module('mainApp').controller('LoginController', LoginController);

    LoginController.$inject = ['$scope', 'cfg', 'LoginService', '$state', '$stateParams'];
    function LoginController($scope, cfg, LoginService, $state, $stateParams) {

        if (angular.isDefined($stateParams.message)) {
            $scope.message = $stateParams.message;
            $scope.errorIs = true;
        }
        else {
            $scope.errorIs = false;
            $scope.message = '';
        }


        $scope.ok = function () {
            var request = {
                login: $scope.login,
                password: $scope.password
            };

            $scope.message = '';
            $scope.errorIs = false;

            LoginService.sentAutentificationRequest(request).then(function (result) {
                $scope.token = result.data;
                return LoginService.saveToken(result.data);

            }, function (reason) {
                $scope.message = reason.data.message;
                $scope.errorIs = true;

            }).then(function (result) {
                //$window.location.href = '/index/org.html';
                $state.go('home.main');
            }, function (reason) {
                $scope.message = reason.message;
                $scope.errorIs = true;

            });


        }

    }
})();