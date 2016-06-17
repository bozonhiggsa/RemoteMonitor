(function () {
    'use strict';
    angular.module('mainApp').config(MainRouter);

    MainRouter.$inject = ['$stateProvider', '$urlRouterProvider'];
    function MainRouter ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                abstract: true,
                templateUrl: 'resources/js/templates/home.html',
                controller: 'HomeController',
                controllerAs: 'Home',
                resolve: {
                    bundles: ['HomeService', 'cfg', function (HomeService, cfg) {
                        HomeService.getBundles().then(
                            function (result) {
                                cfg.bundles = result.data;
                                return true;
                            }
                        )
                    }],
                    authorize: ['LoginService', '$state', '$http', 'cfg', function (LoginService, $state, $http, cfg) {
                        return LoginService.getToken().then(function (result) {
                            $http.defaults.headers.common['Auth-Token'] = result.token;
                            cfg.username = result.username;
                        }).catch(function () {
                            $state.go('login');
                        });

                    }]

                }
            })
            .state('home.main', {
                url: '/',
                templateUrl: 'resources/js/templates/main.html',
                controller: 'MainController',
                controllerAs: 'Main',
                //resolve: {
                //    tableData: ['MainService', function (MainService) {
                //        return MainService.getTableData();
                //    }]
                //},
                ncyBreadcrumb: {
                    label: 'Home page'
                }

            })
            .state('login', {
                url: '/login?message',
                templateUrl: 'resources/js/templates/login.html',
                controller: 'LoginController',
                controllerAs: 'Login'
            })


    }
})();