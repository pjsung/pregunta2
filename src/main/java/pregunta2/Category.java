package pregunta2;

import org.javalite.activejdbc.Model;

/**
 * Created by celia on 26/05/17.
 */
public
class Category
        extends Model {
    private int    id;
    private String name;

    public
    String getName( int id_Category ) {
        Category c = Category.findFirst("id = ?", id_Category);
        return (String) c.get("name");
    }
}
