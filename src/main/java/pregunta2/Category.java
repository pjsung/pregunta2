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
    Category() {
        this.id = 0;
        this.name = null;
    }

    public
    Category( int id_Category ) {
        Category c = Category.findFirst("id = ?", id_Category);
        this.id = (Integer) c.get("id");
        this.name = (String) c.get("name");
    }

    public
    Category(
            int id,
            String name
    ) {
        this.id = id;
        this.name = name;
    }

    @Override
    public
    Object getId() {
        return id;
    }

    public
    String getName() {
        return name;
    }

    public
    void setName( String name ) {
        this.name = name;
    }

}
