(function () {
    'use strict';

    angular.module('mainApp').factory('WebSocketService', WebSocketService);

    WebSocketService.$inject = ['$http', '$q'];
    function WebSocketService($http, $q) {

        var socket = null;

        return {
            initWebsocket: initWebsocket
        };

        function initWebsocket(token) {
            if(socket === null) {
                var options = {};
                options.headers = {};
                options.headers.pet = 'kuku';
                //{ heartbeatTimeout: 10000 }
                //{headers: {'Auth-Token': 'eruerhgdfjh4r' }}
                //{ 'authToken': 'eruerhgdfjh4r'}
                socket = new SockJS('/webSocketHandler', null, {transports: ['xhr-streaming'], headers: {'Auth-Token': token }});

                socket.onopen = function () {
                    console.log("WebSocket open");
                };

                socket.onclose = function (e) {
                    console.log("WebSocket close");
                };

                socket.onmessage = function (e) {
                    console.log('message', e.data);

                    //webSocket.send("response");
                };
                
            }
            return socket;
        }
    }
})();