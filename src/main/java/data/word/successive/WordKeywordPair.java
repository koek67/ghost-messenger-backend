package data.word.successive;

import data.word.keyword.Keyword;
import data.word.Word;

import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class WordKeywordPair {

    private Word word;
    private Set<Keyword> keywordSet;

    public WordKeywordPair(Word word, Set<Keyword> keywordSet) {
        this.word = word;
        this.keywordSet = keywordSet;
    }

    public WordKeywordPair(String text, Set<Keyword> keywordSet) {
        this(new Word(text), keywordSet);
    }

    public Word getWord() {
        return word;
    }

    public Set<Keyword> getKeywordSet() {
        return keywordSet;
    }

    public boolean keywordExists(String text) {
        return keywordExists(new Keyword(text));
    }

    public boolean keywordExists(Keyword keyword) {
        return keywordSet.contains(keyword);
    }

    public void addKeyword(String text) {
        keywordSet.add(new Keyword(text));
    }

    public void addKeywords(Set<Keyword> keywords) {
        // iterate through the Keywords to be added
        for (Keyword keyword : keywords) {
            String tmp = keyword.getText();

            // see if this keyword already exists in our keywordSet
            for (Keyword existingKeyword : keywordSet) {
                String existingKeywordText = existingKeyword.getText();
                // if it already exists, increment this keyword's count
                if (tmp.equals(existingKeywordText)) {
                    existingKeyword.incrementFrequency();
                }
            }
            // if it does not exist, add it to the set
            keywords.add(keyword);
        }
    }

    public String toString() {
        return "Word: " + word + ": " + keywordSet;
    }

}
