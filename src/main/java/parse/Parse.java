package parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by andrew on 9/26/15.
 */
public class Parse {

    public static Conversation getMessagesWith(String friendName, Document doc) throws IOException {
        System.out.println("Looking for messages with " + friendName + " ...");
        // we are only looking for messages between the user and their friend (no group conversations)
        Element content = doc.getElementsByClass("contents").first();
        String myName = content.children().first().text();
        System.out.println("My name is " + myName);
        Elements contentChildren = content.children();
        ArrayList<Element> threads = new ArrayList<Element>();
        for (int i = 1; i < contentChildren.size(); i++) {
            Elements someThreads = contentChildren.get(i).getElementsByClass("thread");
            for (Element thread : someThreads) {
                String people = thread.ownText();
//                System.out.println("conversation between " + people);
                if (people.equals(myName + ", " + friendName) || people.equals(friendName + ", " + myName)) {
                    threads.add(thread);
                }
            }
        }
        System.out.println("I have have had " + threads.size() + " conversation(s) with " + friendName);
        SortedSet<Message> messages = new TreeSet<Message>();
        Date start = null;
        Date end = null;
        for (Element thread : threads) {
            Elements elems = thread.children();
            String sender = "";
            Date time = null;
            String text = "";
            SimpleDateFormat f = new SimpleDateFormat("E, MMMMM dd, yyyy 'at' hh:mma z");
            for (Element elem : elems) {
                String tag = elem.tagName();
                if (tag.equals("div") && elem.hasClass("message")) {
                    sender = elem.getElementsByClass("user").first().ownText();
                    try {
                        time = f.parse(elem.getElementsByClass("meta").first().ownText());
                        if (start == null || time.before(start)) { start = time; }
                        if (end == null || time.after(end)) { end = time; }
                    } catch (ParseException e) {
                        System.out.println("failed to parse " + elem.child(0).child(1).ownText());
                    }
                } else if (tag.equals("p")) {
                    text = elem.ownText();
                    Message message = new Message(text, time, sender.equals(myName));
                    messages.add(message);
                }
            }
        }
        return new Conversation(messages, myName, friendName, start, end);
    }

    public static Conversation learn(String filepath, String friendName) {
        File messagesFile = new File(filepath);
        try {
            Document doc = Jsoup.parse(messagesFile, "UTF-8");
//            Parse.getMessagesWith("Rohan Kadambi", doc);
            Conversation convo = Parse.getMessagesWith(friendName, doc);
            convo.learn();
            return convo;
//            Scanner s = new Scanner(System.in);
//            String next = "";
//            while (!next.equals("quit")) {
//
//            }
        } catch (IOException e) {
            System.out.println("Cannot find messages file");
        }
        return null;
    }

    public static void main(String[] args) {
        learn("assets/facebook-data/html/messages.htm", "Rohan Kadambi");
    }

}
