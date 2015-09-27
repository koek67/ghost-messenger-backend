package data.sentence;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.StringTokenizer;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class SentenceTokenizer {

    /**
     *
     * @param sentence a sentence
     * @return an array of strings in which each element is a word/punctuation (not periods) in the sentence
     */
    public static String[] tokenize(String sentence) {
        String parsed = parseString(sentence);
        StringTokenizer st = new StringTokenizer(parsed, " ");
        String parsed2 = "";
        ArrayList<String> strings = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String next = st.nextToken();
            strings.add(next);
        }

        for (int i = 0; i < strings.size(); i++) {
            String current = strings.get(i);
            String[] tmpArr = current.split("_");
            strings.set(i, tmpArr[0]);
        }

        String[] stringArr = new String[strings.size()];
        for (int i = 0; i < stringArr.length; i++)
            stringArr[i] = strings.get(i);

        return stringArr;
    }

    /**
     * method will split up a sentence into all of its words and punctuation
     *
     * @param sentence
     * @return an array in which each element is a word/punctuation
     */
    private static String parseString(String sentence) {
        MaxentTagger tagger = new MaxentTagger("models/english-bidirectional-distsim.tagger");
        return tagger.tagString(sentence);
    }

    public static void main(String[] args) {
        String sentence = "Hi there my name is mr. koushik. why are you being so dumb!! ugh...";
        String[] s = SentenceSplitter.split(sentence);
        for (String sent : s)
            System.out.println(Arrays.toString(tokenize(sent)));
    }

}
