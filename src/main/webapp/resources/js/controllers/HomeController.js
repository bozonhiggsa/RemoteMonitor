(function () {
    'use strict';

    angular.module('mainApp').controller('HomeController', HomeController);

    HomeController.$inject = ['cfg', 'LoginService', '$state'];
    function HomeController(cfg, LoginService, $state) {

        /* jshint validthis: true */
        var vm = this;

        vm.bundles = cfg.bundles;
        vm.username = cfg.username;

        vm.logout = logout;

        function logout() {
            LoginService.logout();
            $state.go('login');
        }


    }
})();