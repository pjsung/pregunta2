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

        //        User u = new User("celia");
        //        System.out.print("Imprimiendo usuario id: ");
        //        System.out.println(u.getId());
        //        System.out.print("Nombre de usuario: ");
        //        System.out.println(u.getNick());
        //
        //        Category c = new Category(3);
        //        System.out.print("Imprimiendo categoria: ");
        //        System.out.println(c.getId());
        //        System.out.print("Nombre de la categoria: ");
        //        System.out.println(c.getName());

        //System.out.println(Category.count());
        //
        //        Question q = new Question(43L);
        //        Category c2 = new Category(q.getCategory_id());
        //        System.out.print("Categoria de la pregunta: ");
        //        System.out.println(c2.getName());
        //        System.out.print("Pregunta: ");
        //        System.out.println(q.getQuestion());
        //        System.out.print("Opción 1: ");
        //        System.out.println(q.getOption1());
        //        System.out.print("Opción 2: ");
        //        System.out.println(q.getOption2());
        //        System.out.print("Opción 3: ");
        //        System.out.println(q.getOption3());
        //        System.out.print("Opción 4: ");
        //        System.out.println(q.getOption4());
        //        System.out.print("Respuesta correcta: ");
        //        System.out.println(q.getAnswer());


        //        System.out.println(randomID(1L, 60L, 10L));
        //        System.out.println(randomID(1L, 60L, 10L));
        //        System.out.println(randomID(1L, 60L, 10L));
        //          pregunta2.Game.Challenge();
        //        Classic();
        //
        //        get(("/hello"), (req, res) -> "Hello World");


        //        map.put("getAnswer", "");
        //        userAnswer = sc.nextInt();
        //        if ( userAnswer == q.getAnswer() ) {
        //            System.out.println("¡Respuesta Correcta!");
        //        } else {
        //            System.out.println("Respuesta Incorrecta.");
        //            System.out.print("La respuesta correcta es: ");
        //            System.out.println(q.getAnswer());
        //        }
        get("/", ( req, res ) -> {
            Map map = new HashMap();
            return new ModelAndView(map, "./views/bienvenido.mustache");
        }, new MustacheTemplateEngine());

        post("/user", ( req, res ) -> {
            Map  map = new HashMap();
            User u   = new User(req.queryParams("user"));
            map.put("username", u.getNick());
            map.put("userID", u.getId());
            req.session(true);
            req.session().attribute("u.getNick()", "u.getPass()");
            if ( req.queryParams("password").equals(u.getPass()) ) {
                return new ModelAndView(map, "./views/user.mustache");
            } else {
                return new ModelAndView(map, "./views/bienvenido.mustache");
            }
        }, new MustacheTemplateEngine());

        post("/challenge", ( req, res ) -> {
            req.session().attribute("user");
            List< Long > listIdQuestion = pregunta2.Game.randomID(1L, Question.count(), 10L);
            Map          map            = new HashMap();
            return new ModelAndView(map, "./views/challenge9.mustache");
        }, new MustacheTemplateEngine());

        post("/challenge9", ( req, res ) -> {
            req.session().attribute("user");
            List< Long > listIdQuestion = pregunta2.Game.randomID(1L, Question.count(), 1L);
            Question     q              = new Question(listIdQuestion.get(0));
            Map          map            = new HashMap();
            map.put("pregunta", q.getQuestion());
            map.put("preguntaId", q.getId());
            map.put("opcion1", q.getOption1());
            map.put("opcion2", q.getOption2());
            map.put("opcion3", q.getOption3());
            map.put("opcion4", q.getOption4());
            return new ModelAndView(map, "./views/challenge.mustache");
        }, new MustacheTemplateEngine());

        post("/responder", ( req, res ) -> {
            req.session().attribute("user");
            Question q    = new Question(Long.parseLong(req.queryParams("preguntaID")));
            Map      map2 = new HashMap();
            map2.put("respuestaCorrecta", q.getAnswer());
            if ( req.queryParams("respuesta").equals(q.getAnswer()) ) {
                return new ModelAndView(map2, "./views/responderChallenge.mustache");
            } else {
                return new ModelAndView(map2, "./views/responderMalChallenge.mustache");
            }

        }, new MustacheTemplateEngine());

        //        pregunta2.Game.Challenge();

    }
}
