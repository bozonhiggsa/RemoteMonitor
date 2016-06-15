(function () {
    angular.module('mainApp', [
        'ui.router',
        'ui.bootstrap',
        'blockUI',
        'ncy-angular-breadcrumb',
        'toaster'
        
    ]);

    angular.module('mainApp').constant('cfg', {
        apiPath: ''
    });
})();

