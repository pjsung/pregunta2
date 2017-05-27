package pregunta2;

import org.javalite.activejdbc.Model;

public
class User
        extends Model {
    private int    id;
    private String nick;
    private String pass;
    private int    recordChanllenge;
    private int    recordClassic;

    public
    User() {
        this.id = 0;
        this.nick = null;
        this.pass = null;
        this.recordClassic = 0;
        this.recordChanllenge = 0;
    }

    public
    User( String nick_user ) {
        User u = User.findFirst("nick = ?", nick_user);
        this.id = (Integer) u.get("id");
        this.nick = (String) u.get("nick");
        this.pass = (String) u.get("pass");
        this.recordClassic = (Integer) u.get("recordClassic");
        this.recordChanllenge = (Integer) u.get("recordChallenge");
    }

    public
    User(
            int id,
            String nick,
            String pass
    ) {
        this.id = id;
        this.nick = nick;
        this.pass = pass;
        this.recordClassic = 0;
        this.recordChanllenge = 0;
    }

    @Override
    public
    Object getId() {
        return id;
    }

    public
    void setId( int id ) {
        this.id = id;
    }

    public
    String getNick() {
        return nick;
    }

    public
    void setNick( String nick ) {
        this.nick = nick;
    }

    public
    String getPass() {
        return pass;
    }

    public
    void setPass( String pass ) {
        this.pass = pass;
    }

    public
    int getRecordChanllenge() {
        return recordChanllenge;
    }

    public
    void setRecordChanllenge( int recordChanllenge ) {
        this.recordChanllenge = recordChanllenge;
    }

    public
    int getRecordClassic() {
        return recordClassic;
    }

    public
    void setRecordClassic( int recordClassic ) {
        this.recordClassic = recordClassic;
    }

    //    static {
    //        validatePresenceOf("username").message("Please, provide your username");
    //    }

}
