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

        //User u = new User();
        //u.set("username", "Maradona");
        //u.set("password", "messi");
        //u.saveIt();

        //System.out.println(Category.count());

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
            u.set("count", 0);
            u.saveIt();
            Map map = new HashMap();
            return new ModelAndView(map, "./views/registrarFin.mustache");
        }, new MustacheTemplateEngine());

        post("/user", ( req, res ) -> {
            Map  map = new HashMap();
            User u   = User.findFirst("nick = ?", req.queryParams("user"));
            map.put("user", u.getNick());
            map.put("userId", u.getId());
            if ( req.queryParams("password").equals(u.getPass()) ) {
                req.session(true);
                req.session().attribute("user_id", "u.getId()");
                return new ModelAndView(map, "./views/user.mustache");
            } else {
                return new ModelAndView(map, "./views/bienvenido.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/juego", ( req, res ) -> {
            req.session().attribute("user_id");
            System.out.println("-----Session id: " + req.session().id());
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            return new ModelAndView(map, "./views/juego.mustache");
        }, new MustacheTemplateEngine());

        post("/challengeWelcome", ( req, res ) -> {
            req.session().attribute("user_id");
            Game.deleteAll();
            List< Integer > listIdQuestion = pregunta2.Game.randomID(1, Question.count().intValue(), 10);
            for ( int i = 0; i < 10; i++ ) {
                Game g = new Game();
                g.set("question_id", listIdQuestion.get(i));
                g.save();
            }
            Game     g   = Game.first("question_id > 0");
            Question q   = Question.findById(g.getQuestion_id());
            Map      map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", 0);
            map.put("recordC", 0);
            map.put("game_id_actual", g.getId());
            return new ModelAndView(map, "./views/challengeWelcome.mustache");
        }, new MustacheTemplateEngine());

        post("/challenge", ( req, res ) -> {
            req.session().attribute("user_id");
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            Integer i = Integer.valueOf(req.queryParams("count")) + 1;
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
                User    u       = User.findById(req.queryParams("userId"));
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
            req.session().attribute("user_id");
            Game     g   = Game.findById(req.queryParams("game_id_actual"));
            Question q   = Question.findById(g.getQuestion_id());
            Integer  i   = Integer.valueOf(req.queryParams("game_id_actual"));
            Map      map = new HashMap();
            map.put("userId", req.queryParams("userId"));
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

        //        post("/challengeFin", ( req, res ) -> {
        //            req.session().attribute("user_id");
        //            Map map = new HashMap();
        //            map.put("userId", req.queryParams("userId"));
        //            map.put("count", 0);
        //            return new ModelAndView(map, "./views/challengeFin.mustache");
        //        }, new MustacheTemplateEngine());

        post("/classicWelcome", ( req, res ) -> {
            req.session().attribute("user_id");
            List< Integer > listIdQuestion = pregunta2.Game.randomID(1, Question.count().intValue(), 60);
            //            for ( Long i = 0L; i < 2L; i++ ) {
            //                Game g = new Game(i + 1, listIdQuestion.get(i.intValue()));
            //                g.saveIt();
            //            }
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", 0);
            map.put("conditionNext", true);
            return new ModelAndView(map, "./views/classicWelcome.mustache");
        }, new MustacheTemplateEngine());

        post("/classic", ( req, res ) -> {
            req.session().attribute("user_id");
            List< Integer > listIdQuestion = pregunta2.Game.randomID(1, Question.count().intValue(), 1);
            Question        q              = new Question();
            System.out.println("------------" + q.getCategory_id());
            Category c   = new Category();
            Map      map = new HashMap();
            Integer  i   = Integer.valueOf(req.queryParams("count")) + 1;
            map.put("userId", req.queryParams("userId"));
            map.put("count", i);
            map.put("pregunta", q.getQuestion());
            map.put("preguntaId", q.getId());
            map.put("categoryId", c.getId());
            map.put("category", c.getName());
            map.put("opcion1", q.getOption1());
            map.put("opcion2", q.getOption2());
            map.put("opcion3", q.getOption3());
            map.put("opcion4", q.getOption4());
            map.put("conditionNext", req.queryParams("conditionNext"));
            if ( ( i <= 60 ) && ( Boolean.valueOf(req.queryParams("conditionNext")) ) ) {
                return new ModelAndView(map, "./views/classic.mustache");
            } else {
                return new ModelAndView(map, "./views/classicFin.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/responderClassic", ( req, res ) -> {
            req.session().attribute("user_id");
            Question q   = new Question();
            Map      map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", req.queryParams("count"));
            map.put("respuestaCorrecta", q.getAnswer());
            if ( req.queryParams("respuesta").equals(q.getAnswer()) ) {
                map.put("conditionNext", req.queryParams("conditionNext"));
                return new ModelAndView(map, "./views/responderBienClassic.mustache");
            } else {
                map.put("conditionNext", false);
                return new ModelAndView(map, "./views/responderMalClassic.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/classicFin", ( req, res ) -> {
            req.session().attribute("user_id");
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", 0);
            map.put("conditionNext", true);
            return new ModelAndView(map, "./views/classicFin.mustache");
        }, new MustacheTemplateEngine());
    }
}
