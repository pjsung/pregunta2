package pregunta2;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public
class UserTest {
    @After
    public
    void after() {
        System.out.println("UserTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Before
    public
    void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2_test", "celia", "celia");
        System.out.println("UserTest setup");
        Base.openTransaction();
    }

    @Test
    public
    void validateNotNullOfPassword() {
        User user = new User();
        user.set("pass", "");

        assertEquals(false, user.isValid());
    }

    @Test
    public
    void validateNotNullOfUsernames() {
        User user = new User();
        user.set("nick", "");
        user.save();

        assertEquals(false, user.isValid());
    }

    //
    @Test
    public
    void validateUniquenessOfUsernames() {
        User user = new User();
        user.set("nick", "anakin");
        user.set("pass", "anakin");
        user.saveIt();

        User user2 = new User();
        user.set("nick", "anakin");
        user.set("pass", "anakin");
        user2.save();

        assertEquals(false, user2.isValid());
    }
}
