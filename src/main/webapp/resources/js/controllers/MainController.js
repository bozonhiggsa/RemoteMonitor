(function () {
    'use strict';

    angular.module('mainApp').controller('MainController', MainController);

    MainController.$inject = ['cfg', '$state'];
    function MainController(cfg, $state) {
        /* jshint validthis: true */
        var vm = this;

        vm.bundles = cfg.bundles;

        
    }
})();