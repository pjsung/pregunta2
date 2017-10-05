package pregunta2;

import org.javalite.activejdbc.Base;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.*;

/**
 * Main app
 */
public class App {
    public static void main(String[] args) {

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

    }

}
