package workflow.indexing;

import data.sentence.SentenceInserter;
import data.sentence.SentenceSplitter;
import data.sentence.SentenceTokenizer;
import data.word.keyword.Keyword;
import data.word.NullWordSingleton;

import java.util.List;
import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class Indexer {

    public static void index(List<String> messages, Set<Keyword> keywordSet) {
        for (String message : messages) {
            index(message, keywordSet);
        }
    }

    public static void index(String message, Set<Keyword> keywordSet) {

        // Step 1: split text into sentences
        String[] sentences = SentenceSplitter.split(message);

        // Step 2: tokenize each sentence
        for (String sentence : sentences) {
            String[] tokenizedSentence = SentenceTokenizer.tokenize(sentence);
            // Step 3: insert each sentence
            SentenceInserter.insert(keywordSet, NullWordSingleton.getInstance(), tokenizedSentence);
        }

    }

}
