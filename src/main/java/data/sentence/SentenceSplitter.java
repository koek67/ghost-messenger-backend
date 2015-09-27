package data.sentence;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.process.DocumentPreprocessor;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class SentenceSplitter {

    /**
     *
     * @param sentences a string that contains 1 or more sentences in it
     * @return array in which each element is a sentence from the input string
     */
    public static String[] split(String sentences) {
        Reader reader = new StringReader(sentences);
        DocumentPreprocessor dp = new DocumentPreprocessor(reader);
        List<String> sentenceList = new ArrayList<String>();

        for (List<HasWord> sent : dp) {
            String sentenceString = Sentence.listToString(sent);
            sentenceList.add(sentenceString.toString());
        }

        String[] stringArr = new String[sentenceList.size()];
        for (int i = 0; i < stringArr.length; i++)
            stringArr[i] = sentenceList.get(i);

        return stringArr;
    }

}
