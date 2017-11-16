package pregunta2;

import org.eclipse.jetty.websocket.api.Session;
import org.javalite.activejdbc.Base;
import org.json.JSONObject;
import spark.template.mustache.MustacheTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;
import static spark.Spark.*;

/**
 * Main app
 */
public class App {
    // this map is shared between sessions and threads, so it needs to be thread-safe (http://stackoverflow.com/a/2688817)
    static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    static int nextUserNumber = 1; //Assign to username for next connecting user

    public static void main(String[] args) {

        staticFiles.location("/public"); //index.html is served at localhost:4567 (default port)
        webSocket("/1Vs1Welcome2", GameWebSocketHandler.class);
        init();

        before((req, res) -> Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2", "celia", "celia"));

        after((req, res) -> Base.close());

        get("/", Service::welcome, new MustacheTemplateEngine());

        get("/registrar", Service::register, new MustacheTemplateEngine());

        post("/registrarFin", Service::registerEnd, new MustacheTemplateEngine());

        get("/logout", Service::loguot, new MustacheTemplateEngine());

        post("/user", Service::postUser, new MustacheTemplateEngine());

        get("/user", Service::getUser, new MustacheTemplateEngine());

        post("/juego", Service::game, new MustacheTemplateEngine());

        post("/challengeWelcome", Service::challengeWelcome, new MustacheTemplateEngine());

        post("/challenge", Service::challenge, new MustacheTemplateEngine());

        post("/responder", Service::answerChallenge, new MustacheTemplateEngine());

        post("/classicWelcome", Service::classicWelcome, new MustacheTemplateEngine());

        post("/classic", Service::classic, new MustacheTemplateEngine());

        post("/responderClassic", Service::answerClassic, new MustacheTemplateEngine());

        post("/1Vs1Welcome", Service::oneVsOneWelcome, new MustacheTemplateEngine());

        post("/nuevoJuego1vs1", Service::crearUnoVsUno, new MustacheTemplateEngine());

    }

    //Sends a message from one user to all users, along with a list of current usernames
    public static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userlist", userUsernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Builds a HTML element with a sender-name, a message, and a timestamp,
    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
                b(sender + " says:"),
                span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
                p(message)
        ).render();
    }

}
