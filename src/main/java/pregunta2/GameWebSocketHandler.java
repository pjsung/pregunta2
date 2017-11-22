package pregunta2;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class GameWebSocketHandler {
    private String sender, msg;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        String username = "User" + App.nextUserNumber++;
        App.userUsernameMap.put(user, username);
        App.broadcastMessage(sender = username, msg = ("Nothing"));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        App.userUsernameMap.remove(user);
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
    }
}
