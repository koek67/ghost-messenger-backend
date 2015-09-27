package workflow.retrieval;

import data.word.IWord;
import data.word.NullWordSingleton;
import data.word.keyword.Keyword;
import data.word.keyword.utils.KeywordExtractor;
import data.word.successive.WordKeywordPair;

import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class Retriever {

    private static Random random = new Random();

    private static void walk(IWord initial, Set<Keyword> keywordsFromRequest, ArrayList<String> words) {
        int randNum = random.nextInt() * 5 + 5;
        if (randNum > words.size()) return;
        // walk through children
        for (WordKeywordPair wordKeywordPair : initial.getWordKeywordPairs()) {

            // for each child, multiply corresponding keyword with keyword from input
            Set<Keyword> keywordsFromResponse = wordKeywordPair.getKeywordSet();
            for (Keyword keyword : keywordsFromResponse) {
                // try to find this keyword in the input
                boolean found = false;
                for (Keyword keywordLooking : keywordsFromRequest) {
                    if (keyword.getText().equals(keywordLooking.getText())) {
                        // if found, multiply value from input with value from this set
                        // and set as norm of keyword from set
                        double prod = keyword.getFrequency() * keywordLooking.norm;
                        keyword.norm = prod;
                        found = true;
                    }
                }
                if (!found) {
                    // set it to 0
                    keyword.norm = 0;
                }
            }
            double sum = 0;
            // sum up the norms of this child keyword set
            for (Keyword keyword : keywordsFromResponse) {
                sum += keyword.norm;
            }
            // set this as the score
            wordKeywordPair.score = sum;

        }

        // go through children to normalize and set freqscores
        double sumScore = 0;
        for (WordKeywordPair wordKeywordPair : initial.getWordKeywordPairs()) {
            sumScore += wordKeywordPair.score;
        }
        for (WordKeywordPair wordKeywordPair : initial.getWordKeywordPairs()) {
            wordKeywordPair.score = wordKeywordPair.score / sumScore;
            wordKeywordPair.freqScore = wordKeywordPair.getWord().getFrequency() / initial.getFrequency();
            wordKeywordPair.finalScore = 0.7*wordKeywordPair.score + 0.3*wordKeywordPair.freqScore;
        }

        // pick the next
        double a = random.nextDouble();
        double currScore = 0;
        for (WordKeywordPair wordKeywordPair : initial.getWordKeywordPairs()) {
             currScore += wordKeywordPair.finalScore;
            if (a < currScore) {
                words.add(wordKeywordPair.getWord().getText());
                walk(wordKeywordPair.getWord(), keywordsFromRequest, words);
            }
        }


    }

    public static String retrieve(String request) {
        // get Keywords from request
        Set<Keyword> keywordsFromRequest = KeywordExtractor.getKeywords(request);

        // normalize values for keywords
        // sum up the totals
        int sum = 0;
        for (Keyword key : keywordsFromRequest) {
            sum += key.getFrequency();
        }
        // normalize
        for (Keyword key : keywordsFromRequest) {
            double norm = (key.getFrequency() * 1.0) / sum;
            key.norm = norm;
        }

        // start at the null node
        IWord start = NullWordSingleton.getInstance();
        // begin the walk
        ArrayList<String> words = new ArrayList<>();
        walk(start, keywordsFromRequest, words);
        String response = "";
        for (String s : words) {
            response += " " + s;
        }
        return response;

    }

}
