package at.fhj.swq;

//haven't found a good way to include this class

/**
 * Originally this class would have represented the answer of a player.
 * I haven't found a good way to include this class, since I handled the input differently.
 * Answers are represented as an int (number from 1-4). The input will be compared with the position of the correct option in the option Array.
 * Since there was no free text questions, it was enough to handle it as an input from 1-4.
 */
public class Answer {
    String text;
    boolean isCorrect;

    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }
}
