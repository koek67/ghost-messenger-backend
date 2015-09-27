package parse.chunk;

import data.word.keyword.Keyword;
import data.word.keyword.utils.KeywordExtractor;
import parse.chunk.Chunk;
import parse.message.Message;
import parse.message.chat.Pair;
import parse.message.chat.Request;
import parse.message.chat.Response;
import workflow.indexing.Indexer;

import java.util.*;

/**
 * Created by andrew on 9/26/15.
 */
public class Conversation {
    String me;
    String friend;
    List<Chunk> chunks;
    Date start;
    Date end;

    public Conversation (SortedSet<Message> messages, String myName, String friendName, Date start, Date end) {
        this.chunks = chunk(messages, start, end);
        this.me = myName;
        this.friend = friendName;
        System.out.printf("%d conversation chunks%n", chunks.size());
    }

    static List<Chunk> chunk(SortedSet<Message> messages, Date start, Date end) {
        ArrayList<Chunk> chunks = new ArrayList<Chunk>();
        double expectedFreq = (60. * 5 * messages.size() / secondsBetween(start, end)); // average messages per minute

        int MINIMUM_MESSAGES_PER_CHUNK = 2;
        double CUTOFF_PERCENTAGE = .1;
        Chunk chunk = new Chunk();
        PriorityQueue<Message> lastMinute = new PriorityQueue<Message>();
        System.out.printf("%f expected messages per 5 minute%n", expectedFreq);
        for (Message message : messages) {
            lastMinute.add(message);
            while (lastMinute.size() > 0 && secondsBetween(lastMinute.peek().time, message.time) > 60 * 5) {
                chunk.add(lastMinute.poll());
            }
//            System.out.print(lastMinute.size() + " messages in queue\t" + chunk.size() + " messages in chunk\t");
//            if (lastMinute.size() > 0) {
//                System.out.print(secondsBetween(lastMinute.peek().time, message.time)/60 + " minutes\t");
//            }
//            System.out.printf("%s to %s%n", message.time, lastMinute.peek().time);

            // if messaging has died down
//            if (lastMinute.size() < expectedFreq * CUTOFF_PERCENTAGE && chunk.size() > MINIMUM_MESSAGES_PER_CHUNK) {
            if (lastMinute.size() < 2 && chunk.size() > MINIMUM_MESSAGES_PER_CHUNK) {
                chunks.add(chunk);
                chunk = new Chunk();
//                System.out.println("new chunk");
            }
        }
        while (lastMinute.size() > 0) { chunk.add(lastMinute.poll()); }
        chunks.add(chunk);
//        Message nextMessage = messages.first();
//        for (Message m : messages) {
//            System.out.printf("%s   %d minutes%n", m.time, secondsBetween(nextMessage.time, m.time)/60);
//            nextMessage = m;
//        }
        return chunks;
    }

    private static long secondsBetween(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        long endTime = calendar.getTimeInMillis();
        calendar.setTime(start);
        long startTime = calendar.getTimeInMillis();
        long duration = (endTime - startTime) / 1000; // duration in seconds
        return duration;
    }

    public void learn() {
        for (Chunk chunk : chunks) {
            for (Pair pair : chunk.pairs) {
                Request req = pair.getReq();
                Response res = pair.getRes();
                if (res == null || req == null || req.message == null || req.message == "") { continue; }
                Set<Keyword> keys = KeywordExtractor.getKeywords(req.message);
                //System.out.println("RESPONSE " + res.responses + " " + keys);

                Indexer.index(res.responses, keys);
                System.out.println(pair.start() + " " + req.message + " " + keys);
            }
        }
    }

    public static void main(String[] args) {

    }

}
