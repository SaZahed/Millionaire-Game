package at.fhj.swq;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Game class manages the main game flow.
 * The run() Method is the one that starts the CUI. That's why the input of the players are processed in this class.
 * This class has two instances of Objects: Player and QuestionManager.
 * The saving and loading of data follows here. --> the class must be Serializable!
 * The prizes are represented as an array: with every correct answer the money variable will update the position of the prize array.
 * I tried to keep the given attributes of this class here --> questionDone is calculated here.
 */
public class Game implements Serializable {
    public List<Question> questionList = new ArrayList<>();
    String[] prizes = {"50€", "100€", "200€", "300€", "500€", "1000", "2000", "4000", "8000", "15.000", "32.000", "64.000", "125.000", "500.000", "1.000.000"};
    QuestionManager qm = new QuestionManager();
    Player player;
    Player savedPlayer = null; //needed if a previous player wants to continue his last game
    int securedPrize = 0;

    /***
     * Next, the questions are loaded from the questions file.
     * The game loop starts at the number of questions the player has already answered. This allows the player to continue from their previous progress instead of starting from the beginning.
     *  For every correctly answered question:
     *   - The number of completed questions is increased.
     *   - The current difficulty level is calculated.
     *   - The player's prize money is updated.
     *   - The security level is checked.
     *   If the player answers incorrectly, the secured prize isndisplayed. The player can then decide whether to saventheir current game using serialization.
     *  * If all 15 questions are answered correctly, the player wins the million and the game is saved automatically.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***************************************************************");
        System.out.println("Welcome to the millionaire game! Please enter your name :");
        System.out.println("***************************************************************");

        String name = scanner.nextLine();
        System.out.println("Hello," + name + "! " + "Let us start with the questions. If you want to cancel the game, just type in 0:");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("playerData.ser"));// is responsible to convert byte stream to object
            savedPlayer = (Player) ois.readObject(); // geladenes Objekt soll ein Player sein

            if (savedPlayer.getName().equals(name)) {
                System.out.println("Welcome back!");
                System.out.println("The data that was stored about your last game:" + savedPlayer.toString());
                System.out.println("Do you want to continue the saved round from last time(Y/N))");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    player = savedPlayer;
                } else {
                    player = new Player(name, 0, "0€"); // create new player in order to start new game
                }
            } else {
                player = new Player(name, 0, "0€"); // create new player for those who never played before
            }

        } catch (Exception e) {
            System.err.println("Something went wrong with the Input stream");
            player = new Player(name,0,"O€"); //Nullpointer Exception vermeiden

        }

        qm.loadQuestions("questions.txt");
        questionList = qm.getQuestions();

        for (int i = player.getQuestionsDone(); i < 15; i++) { //loop is going through start of list if i=0, but if a player wants to continue his last game we need to start from where he was, also size is 15 now since txt file has more questions
            boolean correct = qm.nextQuestion(i, scanner);
            if (correct) {
                player.calculateQuestionDone(); //after every correct answer we need to increment it
                int diffLevel = calculateDifficultyLevel(player.getQuestionsDone()); //depending on amount of questionDone it is level 1-3
                player.changeMoney(prizes[i]);//after every correct answer we move one step in the array
                System.out.println("You are now at difficulty level " + " " + diffLevel + "!" + "And you have passed " + player.getQuestionsDone() + "question(s)!" + "Your prize right now:" + player.getmoney());
                securityLevelCheck(player.getQuestionsDone());
            } else {
                System.out.println("You have passed" + " " + player.getQuestionsDone() + "question(s)!" + "Your prize to take home:" + securedPrize + " " + "If you want you can start a new round!!");
                System.out.println("If you want to save the game please enter Y. If not then just enter N ");
                String save = scanner.nextLine().toUpperCase();
                if (save.equals("Y")) {
                    saveGame(player);//just if the user want to save the game it will call the methode
                    System.out.println("All right, your game is saved now. You can continue whenever you want to");
                } else {
                    System.out.println("Bye bye, your game was not saved");
                }
                break;
            }
        }
        if (player.getQuestionsDone() == 15) {//not handled: what happens if player that won last round continues
            System.out.println("WOW CONGRATULATION!! You have passed all 15 Questions and won the million! If you want you can play the game again.");
            saveGame(player);//when you win it will be saved automatically
        }
    }

    public void saveGame(Player player) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("playerData.ser"));
            oos.writeObject(player);
            oos.close();
        } catch (Exception e) {
            System.err.println("Something went wrong with the Output Stream");
        }
    }

    /**
     * first 5 questions are by default Level 1, Question 6-10 are Level 2 and the rest is Level 3
     *
     * @param questionDone is the factor to decide the level
     * @return is the difficultyLevel --> needed in CUI to show the user
     */
    public int calculateDifficultyLevel(int questionDone) {
        int difficultyLevel = 0;
        if (questionDone <= 5) {
            difficultyLevel = 1;
            return difficultyLevel;
        } else if (questionDone <= 10) {
            difficultyLevel = 2;
            return difficultyLevel;
        } else {
            difficultyLevel = 3;
            return difficultyLevel;
        }
    }

    public void securityLevelCheck(int questionDone) {
        if (questionDone == 5) {
            securedPrize = 500;
            System.out.println("Great!!!Your prize can not be lower than 500€ <--->");
        } else if (questionDone == 10) {
            securedPrize = 15000;
            System.out.println("Great!!!Your prize can not be lower than 15.000€ <--->");
        }
    }
}