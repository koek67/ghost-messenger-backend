package parse;

import java.util.Date;

/**
 * Created by andrew on 9/26/15.
 */
public class Request extends AbstractMessage {

    String message;

    public Request(String message, Date time) {
        super(time);
        this.message = message;
    }

    public String toString() { return super.time + " " + message; }
}
