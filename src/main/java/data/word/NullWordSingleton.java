package data.word;

import data.word.NullWord;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class NullWordSingleton {

    private static NullWord nullWord = new NullWord();

    private NullWordSingleton() {}

    public static NullWord getInstance() {
        return nullWord;
    }

}
