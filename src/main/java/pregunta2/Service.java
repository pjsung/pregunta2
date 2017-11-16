package pregunta2;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

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

    public static ModelAndView oneVsOneWelcome(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        Map map = new HashMap();
        return new ModelAndView(map, "./views/1Vs1Welcome.mustache");
    }

    public static ModelAndView crearUnoVsUno(Request req, Response res) {
        System.out.println("-----User id: " + req.session().attribute("user_id"));
        List<Integer> listIdQuestion = Game.randomID(1, Question.count().intValue(), 10);
        for (int i = 0; i < 10; i++) {
            Game g = new Game();
            g.set("question_id", listIdQuestion.get(i));
            g.set("user_id", req.session().attribute("user_id"));
            g.set("game_mode", 3);
            g.saveIt();
        }
        Object userId = req.session().attribute("user_id");
        List<Game> game = Game.where("question_id > 0 and game_mode = 3 and user_id = ?", userId);
        Game g = game.get(0);
        Map map = new HashMap();
        map.put("count", 0);
        map.put("record1Vs1", 0);
        map.put("game_id_actual", g.getId());
        return new ModelAndView(map, "./views/1Vs1Welcome.mustache");
    }


}
