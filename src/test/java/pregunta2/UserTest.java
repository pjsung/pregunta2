package pregunta2;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pregunta2_test", "franco", "franco");
        System.out.println("UserTest setup");
        Base.openTransaction();
    }

    @Test
    public
    void validateNotNullOfPassword() {
        User user = new User();
        user.set("pass", "");

        assertEquals(user.isValid(), false);
    }

    @Test
    public
    void validateNotNullOfUsernames() {
        User user = new User();
        user.set("nick", "");

        assertEquals(user.isValid(), false);
    }

    @Test
    public
    void validateUniquenessOfUsernames() {
        User user = new User();
        user.set("nick", "anakin");
        user.saveIt();

        User user2 = new User();
        user.set("nick", "anakin");

        assertEquals(user2.isValid(), false);
    }
}
