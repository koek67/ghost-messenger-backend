package data.word.keyword.utils;

import data.http.AzureHandler;
import data.word.keyword.Keyword;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class KeywordExtractor {

    // this is "AccountKey:{azure_key}" after a base64 encoding
    private static String azureUrl = "https://api.datamarket.azure.com/data.ashx/amla/text-analytics/v1/GetKeyPhrases?Text=";

    public static Set<Keyword> getKeywords(String message) {
        Set<Keyword> set = new HashSet<>();
        String[] filters = {"CC", "DT", "IN", "LS", "PDT", "POS", "RP", "SYM", "TO", "UH", "WDT", "WH", "WP$", "WRB" };
        MaxentTagger tagger = new MaxentTagger("models/english-bidirectional-distsim.tagger");
        String tagged = tagger.tagString(message);
        String[] words = tagged.split(" ");
        //System.out.println(Arrays.toString(words));
        for (String word : words) {
            String[] wordSplit = word.split("_");
            if (wordSplit.length > 1) {
                String token = wordSplit[1];
                boolean exists = false;
                for (String filter : filters) {
                    if (filter.equals(token)){
                        exists = true;
                    }
                }

                if (!exists) {
                    // add to set
                    boolean isThere = false;
                    for (Keyword key : set) {
                        if (key.getText().equals(wordSplit[0])) {
                            key.incrementFrequency();
                            isThere = true;
                        }
                    }
                    if (!isThere) {
                        set.add(new Keyword(wordSplit[0]));
                    }
                }
            }
        }
        return set;
    }

    public static Set<Keyword> getKeywordsBad(String message) {
        // uri encode the message
        String uriEncodedMessage = AzureHandler.uriEncode(message);
        String response = "";
        try {
            response = AzureHandler.getRequest(azureUrl + uriEncodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] words = message.split(" ");
        Set<Keyword> keywords = new HashSet<>();
        for (String word : words) {
            if (response.indexOf(word) != -1) {
                boolean found = false;
                for (Keyword key : keywords) {
                    if (word.equals(key.getText())) {
                        key.incrementFrequency();
                        found = true;
                    }
                }
                if (!found) {
                    keywords.add(new Keyword(word));
                }

            }
        }

        return keywords;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(KeywordExtractor.getKeywords("mvc there my name is koushik and I am very happy today"));
    }

}
