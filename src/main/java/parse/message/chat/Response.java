package parse.message.chat;

import parse.message.AbstractMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andrew on 9/26/15.
 */
public class Response extends AbstractMessage {
    public List<String> responses;

    public Response(List<String> responses, Date time) {
        super(time);
        this.responses = responses;
    }

    public Response(String response, Date time) {
        super(time);
        responses = new ArrayList<String>();
        responses.add(response);
    }
}
