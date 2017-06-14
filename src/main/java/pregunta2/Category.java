package pregunta2;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.UniquenessValidator;

/**
 * Created by celia on 26/05/17.
 */
public
class Category
        extends Model {

    static {
        validatePresenceOf("name").message("Please, provide category name");
        validateWith(new UniquenessValidator("name")).message("This category is already exists.");
    }

    @Override
    public
    Integer getId() {
        return (Integer) this.get("id");
    }

    public
    String getName() {
        return (String) this.get("name");
    }

    public
    void setName( String name ) {
        this.set("name", name);
    }

}
