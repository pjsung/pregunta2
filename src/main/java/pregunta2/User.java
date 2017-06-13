package pregunta2;

import org.javalite.activejdbc.Model;

public
class User
        extends Model {

    public
    Integer getCount() {
        return (Integer) this.get("count");
    }

    public
    void setCount( Integer count ) {
        this.set(count);
    }

    public
    Integer getId() {
        return (Integer) this.get("id");
    }

    public
    void setId( Integer id ) {
        this.setId(id);
    }

    public
    String getNick() {
        return (String) this.get("nick");
    }

    public
    void setNick( String nick ) {
        this.set(nick);
    }

    public
    String getPass() {
        return (String) this.get("pass");
    }

    public
    void setPass( String pass ) {
        this.set(pass);
    }

    public
    Integer getRecordChallenge() {
        return (Integer) this.get("recordChallenge");
    }

    public
    void setRecordChallenge( Integer recordChallenge ) {
        this.set(recordChallenge);
    }

    public
    Integer getRecordClassic() {
        return (Integer) this.get("recordClassic");
    }

    public
    void setRecordClassic( Integer recordClassic ) {
        this.set(recordClassic);
    }
    //    static {
    //        validatePresenceOf("username").message("Please, provide your username");
    //    }

}
