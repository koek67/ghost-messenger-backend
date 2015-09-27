package data.sentence;

import data.word.IWord;
import data.word.keyword.Keyword;
import data.word.utils.WordInserter;

import java.util.Set;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class SentenceInserter {

    public static void insert(Set<Keyword> keywords, IWord initial, String[] sentence) {
        IWord next = initial;
        for (String text : sentence) {
            next = WordInserter.insert(keywords, next, text);
        }
    }

}
