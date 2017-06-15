package pregunta2;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.UniquenessValidator;

/**
 * Created by celia on 26/05/17.
 */
public
class Question
        extends Model {

    static {
        validatePresenceOf("question").message("Please, provide question");
        validatePresenceOf("option1").message("Please, provide option1");
        validatePresenceOf("option2").message("Please, provide option2");
        validatePresenceOf("option3").message("Please, provide option3");
        validatePresenceOf("option4").message("Please, provide option4");
        validatePresenceOf("answer").message("Please, provide answer");
        validatePresenceOf("category_id").message("Please, provide category_id");
        validateWith(new UniquenessValidator("question")).message("This question is already exists.");
    }

    public
    String getAnswer() {
        return (String) this.get("answer");
    }

    public
    void setAnswer( String answer ) {
        this.set("answer", answer);
    }

    public
    Integer getCategory_id() {
        return (Integer) this.get("category_id");
    }

    public
    void setCategory_id( Integer category_id ) {
        this.set("category_id", category_id);
    }

    @Override
    public
    Integer getId() {
        return (Integer) this.get("id");
    }

    public
    String getOption1() {
        return (String) this.get("option1");
    }

    public
    void setOption1( String option1 ) {
        this.set("option1", option1);
    }

    public
    String getOption2() {
        return (String) this.get("option2");
    }

    public
    void setOption2( String option2 ) {
        this.set("option2", option2);
    }

    public
    String getOption3() {
        return (String) this.get("option3");
    }

    public
    void setOption3( String option3 ) {
        this.set("option3", option3);
    }

    public
    String getOption4() {
        return (String) this.get("option4");
    }

    public
    void setOption4( String option4 ) {
        this.set("option4", option4);
    }

    public
    String getQuestion() {
        return (String) this.get("question");
    }

    public
    void setQuestion( String question ) {
        this.set("question", question);
    }
}
