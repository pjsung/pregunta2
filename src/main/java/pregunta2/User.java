package pregunta2;

import org.javalite.activejdbc.Model;

public
class User
        extends Model {

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

    //    static {
    //        validatePresenceOf("username").message("Please, provide your username");
    //    }

}
