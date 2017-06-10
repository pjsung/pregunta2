package pregunta2;

import org.javalite.activejdbc.Model;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by celia on 26/05/17.
 */
public
class Game
        extends Model {
    private Integer count;
    private Long    id;
    private Long    question_id;

    public
    Game( Long id ) {
        question_id = 0L;
        count = 0;
    }

    public
    Game(
            Long id,
            Long question_id,
            Integer count
    ) {
        this.id = id;
        this.question_id = question_id;
        this.count = count;
    }

    /**
     * @param minID:         ID mas chico
     * @param maxID:         ID mas grande
     * @param maxResultSize: cuantos elementos resultantes
     *
     * @return: Long list with maxResultSize elements
     */
    public static
    List< Long > randomID(
            Long minID,
            Long maxID,
            Long maxResultSize
    ) {
        if ( maxResultSize > ( ( maxID + 1 ) - minID ) ) {
            throw new IllegalArgumentException("maxResultSize should be <= than (maxID+1)-minID");
        }
        return new Random().
                longs(minID, maxID + 1). //Return an unlimited stream of primitive ints between minID (inclusive) and maxID
                distinct(). //A stateful check that only unique items are returned
                limit(maxResultSize). //Limits the unlimited stream to the maxResultSize we care about
                boxed(). //Convert int to Integer
                collect(Collectors.toList()); //Turn the stream into a java.utilList for returning
    }

    public
    Integer getCount() {
        return count;
    }

    public
    void setCount( Integer count ) {
        this.count = count;
    }

    @Override
    public
    Long getId() {
        return id;
    }

    public
    void setId( Long id ) {
        this.id = id;
    }

    public
    Long getQuestion_id() {
        return question_id;
    }

    //    public static
    //    void Challenge() {
    //        List< Long > listIdQuestion = randomID(1L, Question.count(), 10L);
    //        Question     q              = new Question(listIdQuestion.get(0));
    //        Scanner      sc             = new Scanner(System.in);
    //        Integer      userAnswer;
    //        Map          map            = new HashMap();
    //        map.put("pregunta", q.getQuestion());
    //        map.put("preguntaId", q.getId());
    //        map.put("opcion1", q.getOption1());
    //        map.put("opcion2", q.getOption2());
    //        map.put("opcion3", q.getOption3());
    //        map.put("opcion4", q.getOption4());
    //        for ( int i = 0; i < 9; i++ ) {
    //            get("/gameChagenlle", ( req, res ) -> {
    //                return new ModelAndView(map, "./views/challenge.mustache");
    //            }, new MustacheTemplateEngine());
    //
    //            post("/responder", ( req, res ) -> {
    //                if ( req.queryParams("respuesta").equals(q.getAnswer()) ) {
    //                    return new ModelAndView(map, "./views/responderChallenge.mustache");
    //                } else {
    //                    return new ModelAndView(map, "./views/responderMalChallenge.mustache");
    //                }
    //
    //            }, new MustacheTemplateEngine());
    //        }
    //        List< Long > listIdQuestion = randomID(1L, Question.count(), 10L);
    //        System.out.println(listIdQuestion);
    //        Scanner sc              = new Scanner(System.in);
    //        String  userAnswer      = null;
    //        Integer recordChallenge = 0;
    //        for ( int i = 0; i < 10; i++ ) {
    //            Question q = new Question(listIdQuestion.get(i));
    //            System.out.print("Pregunta: ");
    //            System.out.println(q.getQuestion());
    //            System.out.print("Opción 1: ");
    //            System.out.println(q.getOption1());
    //            System.out.print("Opción 2: ");
    //            System.out.println(q.getOption2());
    //            System.out.print("Opción 3: ");
    //            System.out.println(q.getOption3());
    //            System.out.print("Opción 4: ");
    //            System.out.println(q.getOption4());
    //            System.out.print("Ingrese su respuesta: ");
    //            userAnswer = sc.next();
    //            if ( userAnswer == q.getAnswer() ) {
    //                System.out.println("¡Respuesta Correcta!");
    //                recordChallenge++;
    //            } else {
    //                System.out.println("Respuesta Incorrecta.");
    //                System.out.print("La respuesta correcta es: ");
    //                System.out.println(q.getAnswer());
    //            }
    //
    //        }
    //        System.out.println("Contestaste bien: " + recordChallenge + " preguntas.");
    //    }

    //    public static
    //    void Classic() {
    //        List< Long > listIdQuestion2 = randomID(1L, Question.count(), Question.count());
    //        System.out.println(listIdQuestion2);
    //        Scanner sc            = new Scanner(System.in);
    //        String  userAnswer    = null;
    //        Integer recordClassic = 0;
    //        Boolean condNext      = true;
    //        for ( int i = 0; i < 60 && condNext; i++ ) {
    //            Question q = new Question(listIdQuestion2.get(i));
    //            Category c = new Category(q.getCategory_id());
    //            System.out.print("Categoria de la pregunta: ");
    //            System.out.println(c.getName());
    //            System.out.print("Pregunta: ");
    //            System.out.println(q.getQuestion());
    //            System.out.print("Opción 1: ");
    //            System.out.println(q.getOption1());
    //            System.out.print("Opción 2: ");
    //            System.out.println(q.getOption2());
    //            System.out.print("Opción 3: ");
    //            System.out.println(q.getOption3());
    //            System.out.print("Opción 4: ");
    //            System.out.println(q.getOption4());
    //            System.out.print("Ingrese su respuesta: ");
    //            userAnswer = sc.next();
    //            if ( userAnswer == q.getAnswer() ) {
    //                System.out.println("¡Respuesta Correcta!");
    //                recordClassic++;
    //            } else {
    //                System.out.println("Respuesta Incorrecta.");
    //                System.out.print("La respuesta correcta es: ");
    //                System.out.println(q.getAnswer());
    //                condNext = false;
    //            }
    //
    //        }
    //        System.out.println("Contestaste bien: " + recordClassic + " preguntas.");
    //    }

    public
    void setQuestion_id( Long question_id ) {
        this.question_id = question_id;
    }

}
