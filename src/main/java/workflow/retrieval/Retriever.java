package workflow.retrieval;

import data.word.keyword.Keyword;
import data.word.keyword.utils.KeywordExtractor;

import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class Retriever {

    public static void retrieve(String request) {
        // get Keywords from request
        Set<Keyword> keywordsFromRequest = KeywordExtractor.getKeywords(request);

        
    }

}
