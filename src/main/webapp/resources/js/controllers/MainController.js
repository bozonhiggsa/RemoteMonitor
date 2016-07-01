(function () {
    'use strict';

    angular.module('mainApp').controller('MainController', MainController);

    MainController.$inject = ['$scope', '$rootScope', 'cfg', '$state', 'MainService', 'currentData'];
    function MainController($scope, $rootScope, cfg, $state, MainService, currentData) {
        /* jshint validthis: true */
        var vm = this;

        vm.bundles = cfg.bundles;

        vm.serverConnection = '##';
        vm.connectionStatusOk = false;

        $rootScope.$on('wsClosed', function (event) {
            $scope.$apply(function () {
                vm.serverConnection = vm.bundles['server.status.web.socket.error'];
                vm.connectionStatusOk = false;
                vm.currentData = vm.connectionFailed();
            });
        });

        $rootScope.$on('wsNewMessage', function (event, data) {

            if (data) {
                var currentData = vm.processCurrentData(JSON.parse(data));

                $scope.$apply(function () {
                    vm.currentData = currentData;

                    vm.serverConnection = vm.bundles['server.status.ok'];
                    vm.connectionStatusOk = true;
                });

            } else {
                $scope.$apply(function () {
                    vm.serverConnection = vm.bundles['server.status.error'];
                    vm.connectionStatusOk = false;
                    vm.currentData = vm.connectionFailed();
                });
            }

        });


        vm.isConnectionOk = isConnectionOk;
        vm.connectionFailed = connectionFailed;
        vm.processCurrentData = processCurrentData;
        vm.longToTimeString = longToTimeString;

        if (currentData != null) {
            vm.currentData = vm.processCurrentData(currentData);
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

        function processCurrentData(currentData) {
            currentData.periodWorkWithMaterial = vm.longToTimeString(currentData.periodWorkWithMaterial);

            currentData.downtime = vm.longToTimeString(currentData.downtime);

            if (currentData.lineOnOff) {
                currentData.lineOnOff = 'ВКЛ.'
            } else {
                currentData.lineOnOff = 'ВЫКЛ.'
            }

            if (currentData.materialOn) {
                currentData.materialOn = 'ДА'
            } else {
                currentData.materialOn = 'НЕТ'
            }

            
            return currentData;
        }

        function longToTimeString(time) {
            var sec = time / 1000;
            var hours = Math.floor(sec / 60);
            var minutes = Math.floor(sec % 60);
            return hours + ' ч., ' + minutes + ' мин.';
        }
    }
})();