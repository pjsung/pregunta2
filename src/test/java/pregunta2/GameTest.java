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
class GameTest {
    @After
    public
    void after() {
        System.out.println("GameTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Before
    public
    void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2", "celia", "celia");
        System.out.println("GameTest setup");
        Base.openTransaction();
    }

    @Test
    public
    void validateNotNullOfQuestionId() {
        Game g = new Game();
        g.set("question_id", "");
        g.save();

        assertEquals(false, g.isValid());
    }

    @Test
    public
    void validateUniquenessOfCategoryNames() {
        Game g = new Game();
        g.set("question_id", 5000);
        g.saveIt();

        Game g2 = new Game();
        g2.set("question_id", 5000);
        g2.save();

        assertEquals(false, g2.isValid());
    }

}
