package pregunta2;

import org.javalite.activejdbc.Base;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Main app
 */
public
class App {
    public static
    void main( String[] args ) {

        before(( req, res ) -> {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2", "celia", "celia");
        });

        after(( req, res ) -> {
            Base.close();
        });

        get("/", ( req, res ) -> {
            Map map = new HashMap();
            return new ModelAndView(map, "./views/bienvenido.mustache");
        }, new MustacheTemplateEngine());

        get("/registrar", ( req, res ) -> {
            Map map = new HashMap();
            return new ModelAndView(map, "./views/registrar.mustache");
        }, new MustacheTemplateEngine());

        post("/registrarFin", ( req, res ) -> {
            User u = new User();
            u.set("nick", req.queryParams("user"));
            u.set("pass", req.queryParams("password"));
            u.set("recordClassic", 0);
            u.set("recordChallenge", 0);
            u.set("userType", "common");
            u.saveIt();
            Map map = new HashMap();
            return new ModelAndView(map, "./views/registrarFin.mustache");
        }, new MustacheTemplateEngine());

        get("/logout", ( req, res ) -> {
            req.session().removeAttribute("user_id");
            Map map = new HashMap();
            return new ModelAndView(map, "./views/logout.mustache");
        }, new MustacheTemplateEngine());

        post("/user", ( req, res ) -> {
            Map  map = new HashMap();
            User u   = User.findFirst("nick = ?", req.queryParams("user"));
            if ( u != null && req.queryParams("password").equals(u.getPass()) ) {
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
        }, new MustacheTemplateEngine());

        get("/user", ( req, res ) -> {
            Map  map = new HashMap();
            User u   = User.findById(req.session().attribute("user_id"));
            map.put("recordChallenge", u.getRecordChallenge());
            map.put("recordClassic", u.getRecordClassic());
            return new ModelAndView(map, "./views/user.mustache");
        }, new MustacheTemplateEngine());

        post("/juego", ( req, res ) -> {
            System.out.println("-----User id: " + req.session().attribute("user_id"));
            Map map = new HashMap();
            return new ModelAndView(map, "./views/juego.mustache");
        }, new MustacheTemplateEngine());

        post("/challengeWelcome", ( req, res ) -> {
            System.out.println("-----User id: " + req.session().attribute("user_id"));
            Game.deleteAll();
            List< Integer > listIdQuestion = pregunta2.Game.randomID(1, Question.count().intValue(), 10);
            for ( int i = 0; i < 10; i++ ) {
                Game g = new Game();
                g.set("question_id", listIdQuestion.get(i));
                g.save();
            }
            Game g   = Game.first("question_id > 0");
            Map  map = new HashMap();
            map.put("count", 0);
            map.put("recordC", 0);
            map.put("game_id_actual", g.getId());
            return new ModelAndView(map, "./views/challengeWelcome.mustache");
        }, new MustacheTemplateEngine());

        post("/challenge", ( req, res ) -> {
            System.out.println("-----User id: " + req.session().attribute("user_id"));
            Map     map = new HashMap();
            Integer i   = Integer.valueOf(req.queryParams("count")) + 1;
            if ( i <= 10 ) {
                Game     g = Game.findById(req.queryParams("game_id_actual"));
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
                User    u       = User.findById(req.session().attribute("user_id"));
                Integer recordC = Integer.valueOf(req.queryParams("recordC"));
                if ( u.getRecordChallenge() < recordC ) {
                    u.setRecordChallenge(recordC);
                    u.saveIt();
                }
                map.put("count", 0);
                map.put("recordC", 0);
                return new ModelAndView(map, "./views/challengeFin.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/responder", ( req, res ) -> {
            System.out.println("-----User id: " + req.session().attribute("user_id"));
            Game     g   = Game.findById(req.queryParams("game_id_actual"));
            Question q   = Question.findById(g.getQuestion_id());
            Integer  i   = Integer.valueOf(req.queryParams("game_id_actual"));
            Map      map = new HashMap();
            map.put("count", req.queryParams("count"));
            map.put("respuestaCorrecta", q.getAnswer());
            map.put("game_id_actual", i + 1);
            if ( req.queryParams("respuesta").equals(q.getAnswer()) ) {
                Integer i2 = Integer.valueOf(req.queryParams("recordC"));
                map.put("recordC", i2 + 1);
                return new ModelAndView(map, "./views/responderBienChallenge.mustache");
            } else {
                map.put("recordC", req.queryParams("recordC"));
                return new ModelAndView(map, "./views/responderMalChallenge.mustache");
            }

        }, new MustacheTemplateEngine());

        post("/classicWelcome", ( req, res ) -> {
            System.out.println("-----User id: " + req.session().attribute("user_id"));
            Game.deleteAll();
            List< Integer > listIdQuestion = pregunta2.Game.randomID(1, Question.count().intValue(), Question.count().intValue());
            for ( int i = 0; i < Question.count().intValue(); i++ ) {
                Game g = new Game();
                g.set("question_id", listIdQuestion.get(i));
                g.save();
            }
            Game g   = Game.first("question_id > 0");
            Map  map = new HashMap();
            map.put("count", 0);
            map.put("conditionNext", true);
            map.put("recordC", 0);
            map.put("game_id_actual", g.getId());
            return new ModelAndView(map, "./views/classicWelcome.mustache");
        }, new MustacheTemplateEngine());

        post("/classic", ( req, res ) -> {
            System.out.println("-----User id: " + req.session().attribute("user_id"));
            Map map = new HashMap();
            map.put("conditionNext", req.queryParams("conditionNext"));
            Integer i = Integer.valueOf(req.queryParams("count")) + 1;
            if ( ( i <= Question.count().intValue() ) && ( Boolean.valueOf(req.queryParams("conditionNext")) ) ) {
                Game     g = Game.findById(req.queryParams("game_id_actual"));
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
                User    u       = User.findById(req.session().attribute("user_id"));
                Integer recordC = Integer.valueOf(req.queryParams("recordC"));
                if ( u.getRecordClassic() < recordC ) {
                    u.setRecordClassic(recordC);
                    u.saveIt();
                }
                map.put("count", 0);
                map.put("recordC", 0);
                return new ModelAndView(map, "./views/classicFin.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/responderClassic", ( req, res ) -> {
            System.out.println("-----User id: " + req.session().attribute("user_id"));
            Game     g   = Game.findById(req.queryParams("game_id_actual"));
            Question q   = Question.findById(g.getQuestion_id());
            Integer  i   = Integer.valueOf(req.queryParams("game_id_actual"));
            Map      map = new HashMap();
            map.put("count", req.queryParams("count"));
            map.put("respuestaCorrecta", q.getAnswer());
            map.put("game_id_actual", i + 1);
            if ( req.queryParams("respuesta").equals(q.getAnswer()) ) {
                map.put("conditionNext", req.queryParams("conditionNext"));
                Integer i2 = Integer.valueOf(req.queryParams("recordC"));
                map.put("recordC", i2 + 1);
                return new ModelAndView(map, "./views/responderBienClassic.mustache");
            } else {
                map.put("conditionNext", false);
                map.put("recordC", req.queryParams("recordC"));
                return new ModelAndView(map, "./views/responderMalClassic.mustache");
            }
        }, new MustacheTemplateEngine());

    }
}
