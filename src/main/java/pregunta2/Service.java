package pregunta2;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    public static ModelAndView welcome(Request req, Response res) {
        Map map = new HashMap();
        return new ModelAndView(map, "./views/bienvenido.mustache");
    }

    public static ModelAndView register(Request req, Response res) {
        Map map = new HashMap();
        return new ModelAndView(map, "./views/registrar.mustache");
    }

    public static ModelAndView registerEnd(Request req, Response res) {
        User u = new User();
        u.set("nick", req.queryParams("user"));
        u.set("pass", req.queryParams("password"));
        u.set("recordClassic", 0);
        u.set("recordChallenge", 0);
        u.set("record1Vs1", 0);
        u.set("userType", "common");
        u.saveIt();
        Map map = new HashMap();
        return new ModelAndView(map, "./views/registrarFin.mustache");
    }

    public static ModelAndView loguot(Request req, Response res) {
        req.session().removeAttribute("user_id");
        Map map = new HashMap();
        return new ModelAndView(map, "./views/logout.mustache");
    }

    public static ModelAndView postUser(Request req, Response res) {
        Map map = new HashMap();
        User u = User.findFirst("nick = ?", req.queryParams("user"));
        if (u != null && req.queryParams("password").equals(u.getPass())) {
            map.put("user", u.getNick());
            map.put("recordChallenge", u.getRecordChallenge());
            map.put("recordClassic", u.getRecordClassic());
            req.session(true);
            req.session().attribute("user_id", u.getId());
            return new ModelAndView(map, "./views/user.mustache");
        } else {
            map.put("state", "Incorrect user or pass, try again");
            return new ModelAndView(map, "./views/bienvenido.mustache");
        }
    }

    public static ModelAndView getUser(Request req, Response res) {
        Map map = new HashMap();
        User u = User.findById(req.session().attribute("user_id"));
        map.put("recordChallenge", u.getRecordChallenge());
        map.put("recordClassic", u.getRecordClassic());
        return new ModelAndView(map, "./views/user.mustache");
    }

    public static ModelAndView game(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        Map map = new HashMap();
        return new ModelAndView(map, "./views/juego.mustache");
    }

    public static ModelAndView challengeWelcome(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        List<Integer> listIdQuestion = Game.randomID(1, Question.count().intValue(), 10);
        for (int i = 0; i < 10; i++) {
            Game g = new Game();
            g.set("question_id", listIdQuestion.get(i));
            g.set("user_id", req.session().attribute("user_id"));
            g.set("game_mode", 2);
            g.saveIt();
        }
        Object userId = req.session().attribute("user_id");
        List<Game> game = Game.where("question_id > 0 and game_mode = 2 and user_id = ?", userId);
        Game g = game.get(0);
        Map map = new HashMap();
        map.put("count", 0);
        map.put("recordC", 0);
        map.put("game_id_actual", g.getId());
        return new ModelAndView(map, "./views/challengeWelcome.mustache");
    }

    public static ModelAndView challenge(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        Map map = new HashMap();
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
            map.put("recordC", req.queryParams("recordC"));
            return new ModelAndView(map, "./views/challenge.mustache");
        } else {
            User u = User.findById(req.session().attribute("user_id"));
            Integer recordC = Integer.valueOf(req.queryParams("recordC"));
            if (u.getRecordChallenge() < recordC) {
                u.setRecordChallenge(recordC);
                u.saveIt();
            }
            map.put("recordCFinal", req.queryParams("recordC"));
            map.put("count", 0);
            map.put("recordC", 0);
            Object userId = req.session().attribute("user_id");
            List<Game> game = Game.where("question_id > 0 and game_mode = 2 and user_id = ?", userId);
            for (int j = 0; j < 10; j++) {
                Game game1 = game.get(j);
                game1.delete();
            }
            return new ModelAndView(map, "./views/challengeFin.mustache");
        }
    }

    public static ModelAndView answerChallenge(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        Game g = Game.findById(req.queryParams("game_id_actual"));
        Question q = Question.findById(g.getQuestion_id());
        Integer i = Integer.valueOf(req.queryParams("game_id_actual"));
        Map map = new HashMap();
        map.put("count", req.queryParams("count"));
        map.put("respuestaCorrecta", q.getAnswer());
        map.put("game_id_actual", i + 1);
        if (req.queryParams("respuesta").equals(q.getAnswer())) {
            Integer i2 = Integer.valueOf(req.queryParams("recordC"));
            map.put("recordC", i2 + 1);
            return new ModelAndView(map, "./views/responderBienChallenge.mustache");
        } else {
            map.put("recordC", req.queryParams("recordC"));
            return new ModelAndView(map, "./views/responderMalChallenge.mustache");
        }
    }

    public static ModelAndView classicWelcome(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        List<Integer> listIdQuestion = Game.randomID(1, Question.count().intValue(), Question.count().intValue());
        for (int i = 0; i < Question.count().intValue(); i++) {
            Game g = new Game();
            g.set("question_id", listIdQuestion.get(i));
            g.set("user_id", req.session().attribute("user_id"));
            g.set("game_mode", 1);
            g.saveIt();
        }
        Object userId = req.session().attribute("user_id");
        List<Game> game = Game.where("question_id > 0 and game_mode = 1 and user_id = ?", userId);
        Game g = game.get(0);
        Map map = new HashMap();
        map.put("count", 0);
        map.put("conditionNext", true);
        map.put("recordC", 0);
        map.put("game_id_actual", g.getId());
        return new ModelAndView(map, "./views/classicWelcome.mustache");
    }

    public static ModelAndView classic(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        Map map = new HashMap();
        map.put("conditionNext", req.queryParams("conditionNext"));
        Integer i = Integer.valueOf(req.queryParams("count")) + 1;
        if ((i <= Question.count().intValue()) && (Boolean.valueOf(req.queryParams("conditionNext")))) {
            Game g = Game.findById(req.queryParams("game_id_actual"));
            Question q = Question.findById(g.getQuestion_id());
            Category c = Category.findById(q.getCategory_id());
            map.put("count", i);
            map.put("pregunta", q.getQuestion());
            map.put("preguntaId", q.getId());
            map.put("categoryId", c.getId());
            map.put("category", c.getName());
            map.put("opcion1", q.getOption1());
            map.put("opcion2", q.getOption2());
            map.put("opcion3", q.getOption3());
            map.put("opcion4", q.getOption4());
            map.put("game_id_actual", g.getId());
            map.put("recordC", req.queryParams("recordC"));
            return new ModelAndView(map, "./views/classic.mustache");
        } else {
            User u = User.findById(req.session().attribute("user_id"));
            Integer recordC = Integer.valueOf(req.queryParams("recordC"));
            if (u.getRecordClassic() < recordC) {
                u.setRecordClassic(recordC);
                u.saveIt();
            }
            map.put("recordCFinal", req.queryParams("recordC"));
            map.put("count", 0);
            map.put("recordC", 0);
            Object userId = req.session().attribute("user_id");
            List<Game> game = Game.where("question_id > 0 and game_mode = 1 and user_id = ?", userId);
            for (int j = 0; j < 60; j++) {
                Game game1 = game.get(j);
                game1.delete();
            }
            return new ModelAndView(map, "./views/classicFin.mustache");
        }
    }

    public static ModelAndView answerClassic(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        Game g = Game.findById(req.queryParams("game_id_actual"));
        Question q = Question.findById(g.getQuestion_id());
        Integer i = Integer.valueOf(req.queryParams("game_id_actual"));
        Map map = new HashMap();
        map.put("count", req.queryParams("count"));
        map.put("respuestaCorrecta", q.getAnswer());
        map.put("game_id_actual", i + 1);
        if (req.queryParams("respuesta").equals(q.getAnswer())) {
            map.put("conditionNext", req.queryParams("conditionNext"));
            Integer i2 = Integer.valueOf(req.queryParams("recordC"));
            map.put("recordC", i2 + 1);
            return new ModelAndView(map, "./views/responderBienClassic.mustache");
        } else {
            map.put("conditionNext", false);
            map.put("recordC", req.queryParams("recordC"));
            return new ModelAndView(map, "./views/responderMalClassic.mustache");
        }
    }

    //Método que muestra la vista de partidas existentes en forma dinámica (Si hay juego creado, se muestra,
    //sino, no lo muestra)
    public static ModelAndView oneVsOneWelcome(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        List<GameTable> lGameTable = GameTable.findAll();
        List<Map> games = new ArrayList<>();
        for (int i = 0; i < GameTable.count(); i++) {
            GameTable gTable = lGameTable.get(i);
            User u = User.findById(gTable.get("user1_id"));
            Map map2 = new HashMap();
            map2.put("game_id", gTable.getId());
            map2.put("user1_nick", u.get("nick"));
            games.add(i, map2);
        }
        Map map = new HashMap();
        map.put("games", games);
        map.put("count", 0);
        return new ModelAndView(map, "./views/1Vs1Welcome.mustache");
    }

    //Método que crea el juego 1 Vs 1, en el caso de que 1 ya tiene el juego creado, le retorna la vista esperando
    //al contrincante, sino crea el juego. Si hay alguien que se une el juego, retorna la vista para empezar a jugar.
    public static ModelAndView createOneVsOne(Request req, Response res) {
        Object user1Id = req.session().attribute("user_id");
        List<GameTable> lGameTable = GameTable.where("user1_id = ?", user1Id);
        Boolean noExiste = lGameTable.isEmpty();
        Map map = new HashMap();
        map.put("count", 0);
        if (!noExiste) {
            if (lGameTable.get(0).get("user2_id") == null) {
                return new ModelAndView(map, "./views/waitOpponent.mustache");
            } else {
                Object userId = req.session().attribute("user_id");
                List<GameTable> l2GameTable = GameTable.where("user1_id = ?", userId);
                GameTable gTable = l2GameTable.get(0);
                map.put("gameTable_id", gTable.getId());
                List<Game> game = Game.where("question_id > 0 and game_mode = 3 and user_id = ?", userId);
                Game g = game.get(0);
                map.put("record1Vs1", 0);
                map.put("game_id_actual", g.getId());
                return new ModelAndView(map, "./views/game1Vs1Welcome.mustache");
            }

        } else {
            GameTable gTable = new GameTable();
            gTable.set("user1_id", req.session().attribute("user_id"));
            gTable.saveIt();
            List<Integer> listIdQuestion = Game.randomID(1, Question.count().intValue(), 10);
            for (int j = 0; j < 10; j++) {
                Game g = new Game();
                g.set("question_id", listIdQuestion.get(j));
                g.set("user_id", req.session().attribute("user_id"));
                g.set("game_mode", 3);
                g.saveIt();
            }
            Object userId = req.session().attribute("user_id");
            List<Game> game = Game.where("question_id > 0 and game_mode = 3 and user_id = ?", userId);
            Game g = game.get(0);
            map.put("gameTable_id", gTable.getId());
            map.put("count", 0);
            map.put("record1Vs1", 0);
            map.put("game_id_actual", g.getId());
            return new ModelAndView(map, "./views/waitOpponent.mustache");
        }

    }

    //Método que une el jugador 2 al juego de jugador uno.
    public static ModelAndView joinGame1Vs1(Request req, Response res) {
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
        return new ModelAndView(map, "./views/game1Vs1WelcomeUser2.mustache");
    }

    //Método que muestra las preguntas al jugador, y recibe respuesta. En caso de que termina de preguntar,
    //retorna la vista correspondiente al jugador 1 o jugador 2, si gana, pierde o empata.
    public static ModelAndView game1Vs1(Request req, Response res) {
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
    }

    //Método que revisa si la respuesta es correcta o no, lo guarda y retorna la vista correspondiente
    public static ModelAndView answer1Vs1(Request req, Response res) {
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
    }

    //Método que elimina el juego 1 vs 1 creado por si mismo
    public static ModelAndView deleteMyGame(Request req, Response res) {
        Object userId = req.session().attribute("user_id");
        GameTable.delete("user1_id = ?", userId);
        List<Game> game = Game.where("question_id > 0 and game_mode = 3 and user_id = ?", userId);
        for (int j = 0; j < 10; j++) {
            Game game1 = game.get(j);
            game1.delete();
        }
        Map map = new HashMap();
        return new ModelAndView(map, "./views/deleteMyGame.mustache");
    }

}
