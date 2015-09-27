package parse;

import javax.swing.*;
import java.util.Date;

/**
 * Created by andrew on 9/26/15.
 */
public class Message extends AbstractMessage {

    String text;
    boolean isMe;

    public Message(String text, Date time, boolean isMe) {
        super(time);
        this.text = text;
        this.isMe = isMe;
    }
}
