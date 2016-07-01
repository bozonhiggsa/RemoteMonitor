(function () {
    'use strict';

    angular.module('mainApp').controller('MainController', MainController);

    MainController.$inject = ['$scope', 'cfg', '$state', 'MainService', 'currentData'];
    function MainController($scope, cfg, $state, MainService, currentData) {
        /* jshint validthis: true */
        var vm = this;

        vm.bundles = cfg.bundles;

        vm.serverConnection = '##';
        vm.connectionStatusOk = false;

        // webSocket.onclose = function (e) {
        //     $scope.$apply(function () {
        //         vm.serverConnection = vm.bundles['server.status.web.socket.error'];
        //         vm.connectionStatusOk = false;
        //     });
        // };
        //
        // webSocket.onmessage = function (e) {
        //     console.log('message', e.data);
        //     if (e.data) {
        //         $scope.$apply(function () {
        //             vm.currentData = JSON.parse(e.data);
        //            
        //             vm.serverConnection = vm.bundles['server.status.ok'];
        //             vm.connectionStatusOk = true;
        //         });
        //
        //
        //     } else {
        //         $scope.$apply(function () {
        //             vm.serverConnection = vm.bundles['server.status.error'];
        //             vm.connectionStatusOk = false;
        //             vm.currentData = vm.connectionFailed();
        //         });
        //     }
        //     //webSocket.send("response");
        // };

        vm.isConnectionOk = isConnectionOk;
        vm.connectionFailed = connectionFailed;

        if (currentData != null) {
            vm.currentData = currentData;
            vm.serverConnection = vm.bundles['server.status.ok'];
            vm.connectionStatusOk = true;
        } else {
            vm.serverConnection = vm.bundles['server.status.error'];
            vm.connectionStatusOk = false;
            vm.currentData = vm.connectionFailed();
        }



        function isConnectionOk() {
            if (vm.connectionStatusOk) {
                return "success-text";
            } else {
                return "error-text";
            }

        }

        function connectionFailed() {
            return {
                lineOnOff: '##',
                currentSpeed: '##',
                expenditureOfMaterial: '##',
                periodWorkWithMaterial: "##",
                downtime: "##",
                turnOnTimeToday: "##",
                turnOffTime: "##",
                materialOn: "##"
            };
        }
    }
})();