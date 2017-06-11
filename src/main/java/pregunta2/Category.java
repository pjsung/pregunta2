package pregunta2;

import org.javalite.activejdbc.Model;

/**
 * Created by celia on 26/05/17.
 */
public
class Category
        extends Model {
    private Long   id;
    private String name;

    public
    Category() {
        this.id = 0L;
        this.name = null;
    }

    public
    Category( Long id_Category ) {
        Category c = Category.findFirst("id = ?", id_Category);
        this.id = id_Category;
        this.name = (String) c.get("name");
    }

    public
    Category(
            Long id,
            String name
    ) {
        this.id = id;
        this.name = name;
    }

    @Override
    public
    Long getId() {
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
