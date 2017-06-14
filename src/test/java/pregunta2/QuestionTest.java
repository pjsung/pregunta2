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
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2", "celia", "celia");
        System.out.println("QuestionTest setup");
        Base.openTransaction();
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
    void validateUniquenessOfCategoryNames() {
        Question q = new Question();
        q.set("question", "¿Cuanto?");
        q.saveIt();

        Question q2 = new Question();
        q2.set("question", "¿Cuanto?");
        q2.save();

        assertEquals(false, q2.isValid());
    }
}
