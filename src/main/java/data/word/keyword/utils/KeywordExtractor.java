package data.word.keyword.utils;

import data.http.AzureHandler;
import data.json.JsonDecoder;
import data.word.keyword.Keyword;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class KeywordExtractor {

    // this is "AccountKey:{azure_key}" after a base64 encoding
    private static String azureUrl = "https://api.datamarket.azure.com/data.ashx/amla/text-analytics/v1/GetKeyPhrases?Text=";

    private static void getRequest() throws Exception {

    }

    public static Set<Keyword> getKeywords(String message) {
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
        System.out.println(KeywordExtractor.getKeywords("hello there my name is koushik and I am very happy today"));
    }

}
