package at.fhj.swq;

/**
 * Representation of a Question.
 * This class shows what the components of a question is:
 * text is the question itself
 * the array of options provides to add different answers
 * the solutionindex saves the correct answer within the options. In my implementation the first option is always the correct one.
 */
public class Question {
    String text;
    String[] options;
    int solutionIndex;

    public Question(String text, String[] options, int solutionIndex) {
        this.text = text;
        this.options = options;
        this.solutionIndex = solutionIndex;

    }
}

