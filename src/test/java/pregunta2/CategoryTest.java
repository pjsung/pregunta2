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
class CategoryTest {
    @After
    public
    void after() {
        System.out.println("CategoryTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Before
    public
    void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2", "celia", "celia");
        System.out.println("CategoryTest setup");
        Base.openTransaction();
    }

    @Test
    public
    void validateNotNullOfCategoryName() {
        Category c = new Category();
        c.set("name", "");
        c.save();

        assertEquals(false, c.isValid());
    }

    @Test
    public
    void validateUniquenessOfCategoryNames() {
        Category c = new Category();
        c.set("name", "manga");
        c.saveIt();

        Category c2 = new Category();
        c2.set("name", "manga");
        c2.save();

        assertEquals(false, c2.isValid());
    }

}
