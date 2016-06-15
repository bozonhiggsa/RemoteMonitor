(function (angular) {
    'use strict';

    angular.module('mainApp').controller('ArchiveController', ArchiveController);

    ArchiveController.$inject = ['$scope', 'cfg', 'ArchiveService', '$state'];
    function ArchiveController($scope, cfg, ArchiveService, $state) {
        $scope.bundles = cfg.bundles;

        $scope.lineTooltip = true;
        $scope.graficData = {
            labels: [],
            series: []
        };

        $scope.graficOptions = {
            stretch: true,
            height: '500px',
            showPoint: false,
            lineSmooth: false

        };


        $scope.getData = function () {

            ArchiveService.getTableData().then(function (result) {
                var arr = [];
                var lb = [];
                angular.forEach(result.data, function (value) {
                    arr.push(value.integerTag);
                    lb.push(value.timeStamp);
                });
                $scope.graficData.series = [arr];
                $scope.graficData.labels = lb;
            }, function (reason) {
                if (reason.data.message == 'Valid token not found' || reason.data.message == 'Access denied') {
                    $state.go('login', {message: reason.data.message})
                }
            });
        }

    }
})(angular);