//Establish the WebSocket connection and set up event handlers
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/game1Vs1");

//Si llega algún mensaje de webSocket, reacarga la pagina a todos menos el usuario que envio peticion (sender)
webSocket.onmessage = function (msg) {
    var data = JSON.parse(msg.data);
    data.userlist.forEach(function (user) {
        if (user != data.sender) {
            location.reload();
        }
    });
};