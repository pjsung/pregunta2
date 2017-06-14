package pregunta2;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.UniquenessValidator;

public
class User
        extends Model {

    static {
        validatePresenceOf("nick").message("Please, provide your username");
        validatePresenceOf("pass").message("Please, provide your password");
        validateWith(new UniquenessValidator("nick")).message("This username is already exists.");
    }

    public
    Integer getId() {
        return (Integer) this.get("id");
    }

    public
    String getNick() {
        return (String) this.get("nick");
    }

    public
    void setNick( String nick ) {
        this.set("nick", nick);
    }

    public
    String getPass() {
        return (String) this.get("pass");
    }

    public
    void setPass( String pass ) {
        this.set("pass", pass);
    }

    public
    Integer getRecordChallenge() {
        return (Integer) this.get("recordChallenge");
    }

    public
    void setRecordChallenge( Integer recordChallenge ) {
        this.set("recordChallenge", recordChallenge);
    }

    public
    Integer getRecordClassic() {
        return (Integer) this.get("recordClassic");
    }

    public
    void setRecordClassic( Integer recordClassic ) {
        this.set("recordClassic", recordClassic);
    }

    public
    String getUserType() { return (String) this.get("userType");}

    public
    void setUserType( String userType ) {this.set("userType", userType);}


}
