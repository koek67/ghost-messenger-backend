package parse.message.chat;

import parse.message.AbstractMessage;

import java.util.Date;

/**
 * Created by andrew on 9/26/15.
 */
public class Request extends AbstractMessage {

    public String message;

    public Request(String message, Date time) {
        super(time);
        this.message = message;
    }

    public String toString() { return super.time + " " + message; }
}
