package data.word;

import data.word.successive.WordKeywordPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class Word implements IWord {

    private String text;
    private List<WordKeywordPair> wordKeywordPairs;
    private int frequency;

    public Word(String text, List<WordKeywordPair> wordKeywordPairs) {
        this.text = text;
        this.wordKeywordPairs = wordKeywordPairs;
        this.frequency = 1;
    }

    public Word(String text) {
        this(text, new ArrayList<WordKeywordPair>());
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<WordKeywordPair> getWordKeywordPairs() {
        return wordKeywordPairs;
    }

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

    public String toString() {
        return text;
    }

}
