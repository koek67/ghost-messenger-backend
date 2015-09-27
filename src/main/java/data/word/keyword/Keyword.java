package data.word.keyword;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class Keyword {

    private String text;
    private int frequency;
    public double norm;
    public Keyword(String text) {
        this.text = text;
        this.frequency = 1;
    }

    public String getText() {
        return text;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean equals(Keyword other) {
        return getText().equals(other.getText());
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }



    public String toString() {
        return "(" + text + ": " + frequency + ")";
    }
}
