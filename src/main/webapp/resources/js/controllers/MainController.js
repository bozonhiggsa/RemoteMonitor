(function () {
    'use strict';

    angular.module('mainApp').controller('MainController', MainController);

    MainController.$inject = ['cfg', '$state'];
    function MainController(cfg, $state) {
        /* jshint validthis: true */
        var vm = this;

        vm.bundles = cfg.bundles;

        vm.lineOfOn = 'kuku1';
        vm.speedCurrent = 'kuku2';
        vm.paperConsumption = 'kuku3';
        vm.periodWorkWithMaterial = "kuku4";
        vm.downtime = "kuku5";
        vm.turnOnTimeToday = "kuku6";
        vm.turnOffTime = "kuku7";
        
    }
})();