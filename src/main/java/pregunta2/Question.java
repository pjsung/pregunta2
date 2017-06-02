package pregunta2;

import org.javalite.activejdbc.Model;

/**
 * Created by celia on 26/05/17.
 */
public
class Question
        extends Model {
    private String answer;
    private Long   category_id;
    private Long   id;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String question;

    public
    Question() {
        this.id = 0L;
        this.question = null;
        this.option1 = null;
        this.option2 = null;
        this.option3 = null;
        this.option4 = null;
        this.answer = null;
        this.category_id = 0L;
    }

    public
    Question( Long id_question ) {
        Question q = Question.findFirst("id = ?", id_question);
        this.id = id_question;
        this.question = (String) q.get("question");
        this.option1 = (String) q.get("option1");
        this.option2 = (String) q.get("option2");
        this.option3 = (String) q.get("option3");
        this.option4 = (String) q.get("option4");
        this.answer = (String) q.get("answer");
        Integer cId = (Integer) q.get("category_id");
        this.category_id = cId.longValue();
    }

    public
    Question(
            Long id,
            String question,
            String option1,
            String option2,
            String option3,
            String option4,
            String answer,
            Long category_id
    ) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.category_id = category_id;
    }

    public
    String getAnswer() {
        return answer;
    }

    public
    void setAnswer( String answer ) {
        this.answer = answer;
    }

    public
    Long getCategory_id() {
        return category_id;
    }

    public
    void setCategory_id( Long category_id ) {
        this.category_id = category_id;
    }

    @Override
    public
    Object getId() {
        return id;
    }

    public
    void setId( Long id ) {
        this.id = id;
    }

    public
    String getOption1() {
        return option1;
    }

    public
    void setOption1( String option1 ) {
        this.option1 = option1;
    }

    public
    String getOption2() {
        return option2;
    }

    public
    void setOption2( String option2 ) {
        this.option2 = option2;
    }

    public
    String getOption3() {
        return option3;
    }

    public
    void setOption3( String option3 ) {
        this.option3 = option3;
    }

    public
    String getOption4() {
        return option4;
    }

    public
    void setOption4( String option4 ) {
        this.option4 = option4;
    }

    public
    String getQuestion() {
        return question;
    }

    public
    void setQuestion( String question ) {
        this.question = question;
    }

}
