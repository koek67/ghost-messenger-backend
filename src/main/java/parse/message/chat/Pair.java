package parse.message.chat;
import parse.message.AbstractMessage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

/**
 * Created by andrew on 9/26/15.
 */
public class Pair extends AbstractMessage {
    private Request req;
    private Response res;

    public Pair(Request req, Response res) {
        super(req.time);
        this.req = req;
        this.res = res;
    }

    public Pair() {}

    public Request getReq() { return req; }
    public Response getRes() { return res; }

    public void setReq(Request req) {
        super.time = req.time;
        this.req = req;
    }
    public void setRes(Response res) { this.res = res; }

    public Date start() { return req.time; }
    public Date end() { return res.time; }
    public Date duration() { throw new NotImplementedException(); }

    public int compareTo(Pair other) {
        return req.time.compareTo(other.req.time);
    }
}
