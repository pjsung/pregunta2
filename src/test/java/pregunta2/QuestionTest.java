package pregunta2;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by celia on 26/05/17.
 */
public
class QuestionTest {
    @After
    public
    void after() {
        System.out.println("QuestionTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Before
    public
    void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2_test", "celia", "celia");
        System.out.println("QuestionTest setup");
        Base.openTransaction();
    }

    @Test
    public
    void validateNotNullOfAnswer() {
        Question q = new Question();
        q.set("answer", "");
        q.save();

        assertEquals(false, q.isValid());
    }

    @Test
    public
    void validateNotNullOfCategoryId() {
        Question q = new Question();
        q.set("category_id", "");
        q.save();

        assertEquals(false, q.isValid());
    }

    @Test
    public
    void validateNotNullOfOption1() {
        Question q = new Question();
        q.set("option1", "");
        q.save();

        assertEquals(false, q.isValid());
    }

    @Test
    public
    void validateNotNullOfOption2() {
        Question q = new Question();
        q.set("option2", "");
        q.save();

        assertEquals(false, q.isValid());
    }

    @Test
    public
    void validateNotNullOfOption3() {
        Question q = new Question();
        q.set("option3", "");
        q.save();

        assertEquals(false, q.isValid());
    }

    @Test
    public
    void validateNotNullOfOption4() {
        Question q = new Question();
        q.set("option4", "");
        q.save();

        assertEquals(false, q.isValid());
    }

    @Test
    public
    void validateNotNullOfQuestion() {
        Question q = new Question();
        q.set("question", "");
        q.save();

        assertEquals(false, q.isValid());
    }

    @Test
    public
    void validateUniquenessOfQuestions() {
        Question q = new Question();
        q.set("question", "¿Cuanto?");
        q.set("option1", "opcion1");
        q.set("option2", "opcion2");
        q.set("option3", "opcion3");
        q.set("option4", "opcion4");
        q.set("answer", "respuesta");
        q.set("category_id", 1);
        q.saveIt();

        Question q2 = new Question();
        q2.set("question", "¿Cuanto?");
        q.set("option1", "opcion1");
        q.set("option2", "opcion2");
        q.set("option3", "opcion3");
        q.set("option4", "opcion4");
        q.set("answer", "respuesta");
        q.set("category_id", 1);
        q2.save();

        assertEquals(false, q2.isValid());
    }
}
