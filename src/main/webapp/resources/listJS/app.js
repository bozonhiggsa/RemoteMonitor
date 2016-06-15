(function () {
    angular.module('mainApp', [
        'ui.router',
        'ui.bootstrap',
        'blockUI',
        'ncy-angular-breadcrumb',
        'toaster',
        'angular-smart-chartist'

    ]);

    angular.module('mainApp').constant('cfg', {
        apiPath: ''
    });
})();

