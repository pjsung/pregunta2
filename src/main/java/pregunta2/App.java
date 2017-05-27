package pregunta2;

import org.javalite.activejdbc.Base;

/**
 * Hello world!
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

        Category c = new Category();
        System.out.println("imprimiendo");
        System.out.println(c.getName(1));
        System.out.print(Category.count());

        //Question q = new Question();
        //System.out.println(Question.);
        Base.close();
    }
}
