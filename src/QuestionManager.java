package at.fhj.swq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The QuestionManager was implemented to handle the switching of the questions and the loading of the questions from the txt file
 * I decided to add this class since the Question class should only represent the attributes of question.
 * Here methods are implemented and the instances of question are created.
 */
public class QuestionManager {
    public List<Question> questionList = new ArrayList<>();

    public QuestionManager() {
    }

    public List<Question> getQuestions() {
        return questionList;
    }

    /**
     * Method implemented with the help of Chatgpt --> formatation idea
     *
     * @param index
     * @param scanner
     * @return
     */
    public boolean nextQuestion(int index, Scanner scanner) {
        Question q = questionList.get(index);
        System.out.println("Question:" + (index + 1) + ": " + q.text);

        for (int i = 0; i < q.options.length; i++) {
            System.out.println((i + 1) + ") " + q.options[i]);//to number the options
        }
        System.out.print(">");
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 0) {
            System.out.println("The game will be canceled as you wanted to. Bye bye!");
            return false;
        }
        int answer = input - 1; // option array is based on indices with 0

        if (answer == q.solutionIndex) { //solutionIndex rn is always 0
            System.out.println("Correct answer!");
            return true;
        } else {
            System.err.println("Incorrect T_T The answer is: " + q.options[q.solutionIndex]); //question of saving game follows in Game class
            return false;
        }
    }

    public void loadQuestions(String filename) {
        File questions = new File("questions.txt");
        Scanner scannerFile = null;
        try {
            scannerFile = new Scanner(questions);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        while (scannerFile.hasNext()) { //as long as this loop follows Question objects will be created, line by line
            String text = scannerFile.nextLine();
            String corectAnswer = scannerFile.nextLine();//always the first one
            String falseAnswer1 = scannerFile.nextLine();
            String falseAnswer2 = scannerFile.nextLine();
            String falseAnswer3 = scannerFile.nextLine();
            int difficultyLevel = Integer.parseInt(scannerFile.nextLine());

            String[] options = {corectAnswer, falseAnswer1, falseAnswer2, falseAnswer3}; // creating option Array to pass it to the constructor
            Question question = new Question(text, options, 0);//solution index default
            questionList.add(question);
        }
    }
}