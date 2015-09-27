package data.word;

import data.word.successive.WordKeywordPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class NullWord implements IWord {

    public static String nullString = "$$nullString$$";
    private List<WordKeywordPair> wordKeywordPairs;
    private int frequency;

    public NullWord(List<WordKeywordPair> wordKeywordPairs) {
        this.wordKeywordPairs = wordKeywordPairs;
        this.frequency = 1;
    }

    public NullWord() {
        this(new ArrayList<WordKeywordPair>());
    }

    @Override
    public String getText() {
        return nullString;
    }

    @Override
    public List<WordKeywordPair> getWordKeywordPairs() {
        return wordKeywordPairs;
    }

    @Override
    public void addWordKeyWordPair(WordKeywordPair pair) {
        wordKeywordPairs.add(pair);
    }

    @Override
    public void incrementFrequency() {
        frequency++;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }


}
