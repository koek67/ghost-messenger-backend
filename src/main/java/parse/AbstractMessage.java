package parse;

import java.util.Date;

/**
 * Created by andrew on 9/26/15.
 */
public abstract class AbstractMessage implements Comparable<AbstractMessage>{

    Date time;

    public AbstractMessage(Date time) {
        this.time = time;
    }

    public AbstractMessage() {}

    public int compareTo(AbstractMessage other) {
        return this.time.compareTo(other.time);
    }
}
