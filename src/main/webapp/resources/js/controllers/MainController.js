(function () {
    'use strict';

    angular.module('mainApp').controller('MainController', MainController);

    MainController.$inject = ['cfg', '$state', '$interval', 'MainService', 'currentData'];
    function MainController(cfg, $state, $interval, MainService, currentData) {
        /* jshint validthis: true */
        var vm = this;

        vm.bundles = cfg.bundles;

        vm.serverConnection = '##';
        vm.connectionStatusOk = false;

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


        var stop = $interval(function () {
            MainService.getCurrentData().then(function (result) {
                if (result !== null) {
                    vm.currentData = result;
                    vm.serverConnection = vm.bundles['server.status.ok'];
                    vm.connectionStatusOk = true;
                } else {
                    vm.serverConnection = vm.bundles['server.status.error'];
                    vm.connectionStatusOk = false;
                    vm.currentData = vm.connectionFailed();
                }

            })
        }, 3000);

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