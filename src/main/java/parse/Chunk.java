package parse;

import java.time.Duration;
import java.util.*;

/**
 * Created by andrew on 9/26/15.
 */
public class Chunk {
    SortedSet<Pair> pairs;
    Date start;
    Date end;


    public Chunk() {
        pairs = new TreeSet<Pair>();
    }

//    public SortedSet<Pair> convertListToPairs(List<Message> messages) {
//        start = messages.get(0).time;
//        end = start;
//        TreeSet<Pair> p = new TreeSet<Pair>();
//        Pair currentPair = new Pair();
//        for (int i = 0; i < messages.size(); i++) {
//            Message m = messages.get(i);
//            if (m.time.before(start)) { start = m.time; }
//            else if (m.time.after(end)) { end = m.time; }
//            if (!m.isMe) { // not me messaging aka a request
//                // if this is a request and a response is already there,
//                // then finish the old pair, insert into the queue, and make a new one
//                if (currentPair.getRes() != null) {
//                    p.add(currentPair);
//                    currentPair = new Pair();
//                }
//                // if the req is not there yet, set this as the req
//                // otherwise append this message text to the existing req
//                if (currentPair.getReq() == null) {
//                    currentPair.setReq(new Request(m.text, m.time));
//                } else {
//                    currentPair.getReq().message += "\n" + m.text;
//                }
//            } else {
//                // if there is no req then make a null req and continue
//                if (currentPair.getReq() == null) {
//                    currentPair.setReq(new Request("", m.time));
//                }
//                // if there is no response then make a new one,
//                // otherwise append this message to the existing res
//                if (currentPair.getRes() == null) {
//                    ArrayList<String> text = new ArrayList<String>();
//                    text.add(m.text);
//                    currentPair.setRes(new Response(text, m.time));
//                } else {
//                    currentPair.getRes().responses.add(m.text);
//                }
//            }
//        }
//        if (currentPair.getReq() != null) {
//            currentPair.setRes(new Response("", currentPair.getReq().time));
//            p.add(currentPair);
//        }
//        System.out.println("After squishing, there are " + p.size() + " request-responses");
//        return p;
//    }

    public int size() { return pairs.size(); }

    public void add(Pair p) {
        pairs.add(p);
        if (p.getReq() != null && (start == null || p.start().before(start))) { start = p.start(); }
        if (p.getRes() != null && (end == null || p.end().after(end))) { end = p.end(); }
    }

    public void add(Iterable<Message> ms) {
        for (Message m : ms) { add(m); }
    }

    public void add(Message m) {
//        System.out.println("adding new message");
        Pair currentPair;
        if (pairs.size() > 0) {
            currentPair = pairs.last();
        } else {
            currentPair = null;
        }
        if (!m.isMe) { // not me messaging aka a request
            // if this is a request and a response is already there,
            // then finish the old pair and make a new one
            if (currentPair != null && currentPair.getRes() != null) {
                currentPair = null;
            }
            if (currentPair == null) {
                currentPair = new Pair();
                currentPair.setReq(new Request(m.text, m.time));
                add(currentPair);
            } else {
                currentPair.getReq().message += "\n" + m.text;
            }
        } else {
            // if there is no req then make a null req and continue
            if (currentPair == null) {
                currentPair = new Pair();
                currentPair.setReq(new Request("", m.time));
                add(currentPair);
            }
            // if there is no response then make a new one,
            // otherwise append this message to the existing res
            if (currentPair.getRes() == null) {
                ArrayList<String> text = new ArrayList<String>();
                text.add(m.text);
                currentPair.setRes(new Response(text, m.time));
            } else {
                currentPair.getRes().responses.add(m.text);
            }
        }

    }

}
