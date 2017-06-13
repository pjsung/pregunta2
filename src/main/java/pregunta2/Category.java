package pregunta2;

import org.javalite.activejdbc.Model;

/**
 * Created by celia on 26/05/17.
 */
public
class Category
        extends Model {

    @Override
    public
    Integer getId() {
        return (Integer) this.get("id");
    }

    public
    void setId( Integer id ) {
        this.setId(id);
    }

    public
    String getName() {
        return (String) this.get("name");
    }

    public
    void setName( String name ) {
        this.set("name");
    }

}
