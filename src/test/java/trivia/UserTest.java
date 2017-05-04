package trivia;

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
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/trivia_test", "franco", "franco");
        System.out.println("UserTest setup");
        Base.openTransaction();
    }

    // @Test
    // public void validateUniquenessOfUsernames(){
    //     User user = new User();
    //     user.set("username", "anakin");
    //     user.saveIt();

    //     User user2 = new User();
    //     user.set("username", "anakin");

    //     assertEquals(user2.isValid(), false);
    // }

    @Test
    public
    void validateUniquenessOfUsernames() {
        User user = new User();
        user.set("username", "");

        assertEquals(user.isValid(), false);
    }
}
