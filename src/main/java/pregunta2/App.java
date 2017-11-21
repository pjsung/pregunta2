package pregunta2;

import org.eclipse.jetty.websocket.api.Session;
import org.javalite.activejdbc.Base;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        webSocket("/game1Vs1", GameWebSocketHandler.class);
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

        post("/nuevoJuego1vs1", Service::createOneVsOne, new MustacheTemplateEngine());

        post("/joinGame1Vs1", (Request req, Response res) -> {
            Object game_id = req.queryParams("game_id");
            Object user1_nick = req.queryParams("user1_nick");
            GameTable gT = GameTable.findById(game_id);
            gT.set("user2_id", req.session().attribute("user_id"));
            gT.saveIt();
            Object userId = gT.get("user1_id");
            List<Game> game = Game.where("question_id > 0 and game_mode = 3 and user_id = ?", userId);
            Game g = game.get(0);
            Map map = new HashMap();
            map.put("gameTable_id", gT.getId());
            map.put("record1Vs1", 0);
            map.put("game_id_actual", g.getId());
            map.put("count", req.queryParams("count"));
            return new ModelAndView(map, "./views/game1Vs1Welcome.mustache");
        }, new MustacheTemplateEngine());

        post("/game1Vs1", (Request req, Response res) -> {
            Map map = new HashMap();
            map.put("gameTable_id", req.queryParams("gameTable_id"));
            Integer i = Integer.valueOf(req.queryParams("count")) + 1;
            if (i <= 10) {
                Game g = Game.findById(req.queryParams("game_id_actual"));
                Question q = Question.findById(g.getQuestion_id());
                map.put("count", i);
                map.put("pregunta", q.getQuestion());
                map.put("preguntaId", q.getId());
                map.put("opcion1", q.getOption1());
                map.put("opcion2", q.getOption2());
                map.put("opcion3", q.getOption3());
                map.put("opcion4", q.getOption4());
                map.put("game_id_actual", g.getId());
                map.put("record1Vs1", req.queryParams("record1Vs1"));
                return new ModelAndView(map, "./views/game1Vs1.mustache");
            } else { //(i > 10)
                GameTable gT = GameTable.findById(req.queryParams("gameTable_id"));
                if (gT.get("user1_id") == req.session().attribute("user_id")) {
                    if (gT.get("user1_score") == null) {
                        gT.set("user1_score", req.queryParams("record1Vs1"));
                        gT.saveIt();
                    }
                    GameTable gT2 = GameTable.findById(req.queryParams("gameTable_id"));
                    if (gT2.get("user2_score") == null) {
                        return new ModelAndView(map, "./views/waitOpponent.mustache");
                    } else { //(gT2.get("user2_score") != null)
                        map.put("count", 0);
                        map.put("record1Vs1", 0);
                        Integer user1_score = (Integer) gT2.get("user1_score");
                        Integer user2_score = (Integer) gT2.get("user2_score");
                        if (user1_score.equals(user2_score)) {
                            return new ModelAndView(map, "./views/tie.mustache");
                        } else {
                            if (user1_score > user2_score) {
                                return new ModelAndView(map, "./views/win.mustache");
                            } else {
                                return new ModelAndView(map, "./views/lose.mustache");
                            }
                        }
                    }
                } else { //(gT.get("user1_id") != req.session().attribute("user_id"))
                    if (gT.get("user2_score") == null) {
                        gT.set("user2_score", req.queryParams("record1Vs1"));
                        gT.saveIt();
                    }
                    GameTable gT2 = GameTable.findById(req.queryParams("gameTable_id"));
                    if (gT2.get("user1_score") == null) {
                        return new ModelAndView(map, "./views/waitOpponent.mustache");
                    } else {
                        map.put("count", 0);
                        map.put("record1Vs1", 0);
                        Integer u1_score = (Integer) gT2.get("user1_score");
                        Integer u2_score = (Integer) gT2.get("user2_score");
                        if (u1_score.equals(u2_score)) {
                            return new ModelAndView(map, "./views/tie.mustache");
                        } else {
                            if (u2_score > u1_score) {
                                return new ModelAndView(map, "./views/win.mustache");
                            } else {
                                return new ModelAndView(map, "./views/lose.mustache");
                            }
                        }
                    }
                }
            }
        }, new MustacheTemplateEngine());

        post("/answer1Vs1", (Request req, Response res) -> {
            Game g = Game.findById(req.queryParams("game_id_actual"));
            Question q = Question.findById(g.getQuestion_id());
            Integer i = Integer.valueOf(req.queryParams("game_id_actual"));
            Map map = new HashMap();
            map.put("gameTable_id", req.queryParams("gameTable_id"));
            map.put("count", req.queryParams("count"));
            map.put("respuestaCorrecta", q.getAnswer());
            map.put("game_id_actual", i + 1);
            if (req.queryParams("respuesta").equals(q.getAnswer())) {
                Integer i2 = Integer.valueOf(req.queryParams("record1Vs1"));
                map.put("record1Vs1", i2 + 1);
                return new ModelAndView(map, "./views/responseCorrect1Vs1.mustache");
            } else {
                map.put("record1Vs1", req.queryParams("record1Vs1"));
                return new ModelAndView(map, "./views/responseIncorrect1Vs1.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/deleteMyGame", (Request req, Response res) -> {
            Object userId = req.session().attribute("user_id");
            GameTable.delete("user1_id = ?", userId);
            List<Game> game = Game.where("question_id > 0 and game_mode = 3 and user_id = ?", userId);
            for (int j = 0; j < 10; j++) {
                Game game1 = game.get(j);
                game1.delete();
            }
            Map map = new HashMap();
            return new ModelAndView(map, "./views/deleteMyGame.mustache");
        }, new MustacheTemplateEngine());
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
