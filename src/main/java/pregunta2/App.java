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

        post("/user", ( req, res ) -> {
            Map  map = new HashMap();
            User u   = new User(req.queryParams("user"));
            map.put("user", u.getNick());
            map.put("userId", u.getId());
            req.session(true);
            req.session().attribute("u.getNick()", "u.getId()");
            if ( req.queryParams("password").equals(u.getPass()) ) {
                return new ModelAndView(map, "./views/user.mustache");
            } else {
                return new ModelAndView(map, "./views/bienvenido.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/juego", ( req, res ) -> {
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            return new ModelAndView(map, "./views/juego.mustache");
        }, new MustacheTemplateEngine());

        post("/challengeWelcome", ( req, res ) -> {
            req.session().attribute("user");
            List< Long > listIdQuestion = pregunta2.Game.randomID(1L, Question.count(), 10L);
            for ( Long i = 0L; i < 2L; i++ ) {
                Game g = new Game(i + 1L, listIdQuestion.get(i.intValue()));
                g.saveIt();
            }
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", 0);
            return new ModelAndView(map, "./views/challengeWelcome.mustache");
        }, new MustacheTemplateEngine());

        post("/challenge", ( req, res ) -> {
            req.session().attribute("user");
            List< Long > listIdQuestion = pregunta2.Game.randomID(1L, Question.count(), 1L);
            Question     q              = new Question(listIdQuestion.get(0));
            Map          map            = new HashMap();
            Integer      i              = Integer.valueOf(req.queryParams("count")) + 1;
            map.put("userId", req.queryParams("userId"));
            map.put("count", i);
            map.put("pregunta", q.getQuestion());
            map.put("preguntaId", q.getId());
            map.put("opcion1", q.getOption1());
            map.put("opcion2", q.getOption2());
            map.put("opcion3", q.getOption3());
            map.put("opcion4", q.getOption4());
            if ( i <= 10 ) {
                return new ModelAndView(map, "./views/challenge.mustache");
            } else {
                return new ModelAndView(map, "./views/challengeFin.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/responder", ( req, res ) -> {
            req.session().attribute("user");
            Question q   = new Question(Long.parseLong(req.queryParams("preguntaId")));
            Map      map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", req.queryParams("count"));
            map.put("respuestaCorrecta", q.getAnswer());
            if ( req.queryParams("respuesta").equals(q.getAnswer()) ) {
                return new ModelAndView(map, "./views/responderBienChallenge.mustache");
            } else {
                return new ModelAndView(map, "./views/responderMalChallenge.mustache");
            }

        }, new MustacheTemplateEngine());

        post("/challengeFin", ( req, res ) -> {
            req.session().attribute("user");
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", 0);
            return new ModelAndView(map, "./views/challengeFin.mustache");
        }, new MustacheTemplateEngine());

        post("/classicWelcome", ( req, res ) -> {
            req.session().attribute("user");
            List< Long > listIdQuestion = pregunta2.Game.randomID(1L, Question.count(), 60L);
            for ( Long i = 0L; i < 2L; i++ ) {
                Game g = new Game(i + 1L, listIdQuestion.get(i.intValue()));
                g.saveIt();
            }
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", 0);
            map.put("conditionNext", true);
            return new ModelAndView(map, "./views/classicWelcome.mustache");
        }, new MustacheTemplateEngine());

        post("/classic", ( req, res ) -> {
            req.session().attribute("user");
            List< Long > listIdQuestion = pregunta2.Game.randomID(1L, Question.count(), 1L);
            Question     q              = new Question(listIdQuestion.get(0));
            System.out.println("------------" + q.getCategory_id());
            Category c   = new Category(q.getCategory_id());
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
            req.session().attribute("user");
            Question q   = new Question(Long.parseLong(req.queryParams("preguntaId")));
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
            req.session().attribute("user");
            Map map = new HashMap();
            map.put("userId", req.queryParams("userId"));
            map.put("count", 0);
            map.put("conditionNext", true);
            return new ModelAndView(map, "./views/classicFin.mustache");
        }, new MustacheTemplateEngine());
    }
}
