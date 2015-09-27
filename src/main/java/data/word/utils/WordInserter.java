package data.word.utils;

import data.word.IWord;
import data.word.Word;
import data.word.keyword.Keyword;
import data.word.successive.WordKeywordPair;

import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class WordInserter {

    /**
     * Function will insert a Word into the graph along with its keywords
     *
     * @param keywordSet the set of keywords associated with this insertion
     * @param currentWord the current word in this graph
     * @param text
     * @return
     */
    public static Word insert(Set<Keyword> keywordSet, IWord currentWord, String text) {

        // check to see if the new word is already a child word of current word
        WordKeywordPair duplicate = null;
        for (WordKeywordPair wordKeywordPair : currentWord.getWordKeywordPairs()) {
            Word word = wordKeywordPair.getWord();
            if (word.getText().equals(text)) {
                duplicate = wordKeywordPair;
            }
        }

        // CASE 1: newWord is already in the child words of currentWord
        //          - find the WordKeywordPair
        //          - add keywords to this WordKeywordPair
        //          - increment frequency of currentWord
        if (duplicate != null) {
            duplicate.addKeywords(keywordSet);
            duplicate.getWord().incrementFrequency();
            return duplicate.getWord();
        } else {
            // CASE 2: newWord is not in the child words of currentWord
            //          - create and add a child word
            //          - to this child add the new keywords
            //          - increment the frequency of currentWord
            Word wordToAdd = new Word(text);
            WordKeywordPair newWordKeywordPair = new WordKeywordPair(wordToAdd, keywordSet);
            currentWord.addWordKeyWordPair(newWordKeywordPair);
            currentWord.incrementFrequency();
            return newWordKeywordPair.getWord();
        }

    }

}
