package pregunta2;

import org.javalite.activejdbc.Model;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by celia on 26/05/17.
 */
public
class Game
        extends Model {

    /**
     * @param minID:         ID mas chico
     * @param maxID:         ID mas grande
     * @param maxResultSize: cuantos elementos resultantes
     *
     * @return: Long list with maxResultSize elements
     */
    public static
    List< Integer > randomID(
            Integer minID,
            Integer maxID,
            Integer maxResultSize
    ) {
        if ( maxResultSize > ( ( maxID + 1 ) - minID ) ) {
            throw new IllegalArgumentException("maxResultSize should be <= than (maxID+1)-minID");
        }
        return new Random().
                ints(minID, maxID + 1). //Return an unlimited stream of primitive ints between minID (inclusive) and maxID
                distinct(). //A stateful check that only unique items are returned
                limit(maxResultSize). //Limits the unlimited stream to the maxResultSize we care about
                boxed(). //Convert int to Integer
                collect(Collectors.toList()); //Turn the stream into a java.utilList for returning
    }

    @Override
    public
    Integer getId() {
        return (Integer) this.get("id");
    }

    public
    Integer getQuestion_id() {
        return (Integer) this.get("question_id");
    }

    public
    void setQuestion_id( Integer question_id ) {
        this.set("question_id", question_id);
    }

}
