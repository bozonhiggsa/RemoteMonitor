(function () {
    'use strict';

    angular.module('mainApp').factory('WebSocketService', WebSocketService);

    WebSocketService.$inject = ['$http', '$q'];
    function WebSocketService($http, $q) {

        return {
            initWebsocket: initWebsocket
        };

        function initWebsocket() {
            var socket = new SockJS('/webSocketHandler');
            
            socket.onmessage = function(e) {
                console.log('message', e.data);
            };
            
            return socket;
        }
    }
})();