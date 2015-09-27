package data.word;

import data.word.successive.WordKeywordPair;

import java.util.List;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public interface IWord {

    /**
     *
     * @return a String representation of this word
     */
    String getText();

    /**
     * Accessor to retrieve this Word's list of Words that come after it
     *
     * @return list of successive words
     */
    List<WordKeywordPair> getWordKeywordPairs();

    void addWordKeyWordPair(WordKeywordPair pair);

    void incrementFrequency();

    int getFrequency();

}