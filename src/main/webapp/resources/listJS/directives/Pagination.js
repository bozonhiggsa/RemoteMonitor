(function () {
    'use strict';
    angular.module('mainApp').directive('customPagination', ['$parse', '$q', function ($parse, $q) {
        return {
            restrict: 'EA',
            templateUrl: 'resources/js/templates/pagination.html',
            link: function (scope, element, attrs, ngModelCtrl) {

                scope.pageData = {
                    inputPage: 1,
                    currentPage: 1
                };

                scope.newSearch = function (page) {
                    scope.selectPage(page);
                };

                scope.enterPageNumber = function (event, page) {
                    if (event.keyCode == 13 && !isNaN(page)) {
                        scope.selectPage(parseInt(page));
                    }
                };

                scope.selectPage = function (page) {
                    if (typeof scope.totalPages === 'undefined' || scope.totalPages == 0) {
                        scope.totalPages = 1;
                    }
                    if (typeof page !== 'undefined' && page > 0 && page <= scope.totalPages) {
                        scope.pageData.currentPage = page;
                        $q.when(scope.formPopulate(page, scope.searchData)).then(function (result) {
                            if (result != null) {
                                scope.tableData = result.tableData;
                                scope.totalPages = result.totalPages;
                                scope.hideShowPagenation();
                            }

                        });

                    }
                    scope.resetInputValue(scope.pageData.currentPage);

                };

                scope.resetInputValue = function (value) {
                    scope.pageData.inputPage = value;
                };

                scope.hideShowPagenation = function () {
                    scope.showPagination = typeof scope.totalPages !== 'undefined' && scope.totalPages > 1;
                };

                scope.hideShowPagenation();

            }
        };
    }]);
})();