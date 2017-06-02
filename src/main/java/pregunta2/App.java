package pregunta2;

import org.javalite.activejdbc.Base;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Main app
 */
public
class App {
    public static
    void main( String[] args ) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2", "celia", "celia");

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
        //
        //
        //        Map map = new HashMap();
        //        map.put("name", "Sam");
        //        map.put("value", 1000);
        //        map.put("taxed_value", 1000 - (1000 * 0.4));
        //        map.put("in_ca", true);
        //
        //        get("/hello2", (req, res) -> {
        //                    return new ModelAndView(map, "./views/test.mustache");
        //                }, new MustacheTemplateEngine()
        //        );

        List< Long > listIdQuestion = pregunta2.Game.randomID(1L, Question.count(), 1L);
        Question     q              = new Question(listIdQuestion.get(0));
        Scanner      sc             = new Scanner(System.in);
        Integer      userAnswer;
        Map          map            = new HashMap();
        map.put("pregunta", q.getQuestion());
        map.put("preguntaId", q.getId());
        map.put("opcion1", q.getOption1());
        map.put("opcion2", q.getOption2());
        map.put("opcion3", q.getOption3());
        map.put("opcion4", q.getOption4());
        //        map.put("getAnswer", "");
        //        userAnswer = sc.nextInt();
        //        if ( userAnswer == q.getAnswer() ) {
        //            System.out.println("¡Respuesta Correcta!");
        //        } else {
        //            System.out.println("Respuesta Incorrecta.");
        //            System.out.print("La respuesta correcta es: ");
        //            System.out.println(q.getAnswer());
        //        }

        get("/hello2", ( req, res ) -> {
            return new ModelAndView(map, "./views/challenge.mustache");
        }, new MustacheTemplateEngine());

        post("/responder", ( req, res ) -> {
            if ( req.queryParams("respuesta").equals(q.getAnswer()) ) {
                return new ModelAndView(map, "./views/responderChallenge.mustache");
            } else {
                return new ModelAndView(map, "./views/responderMalChallenge.mustache");
            }

        }, new MustacheTemplateEngine());


        Base.close();
    }
}
