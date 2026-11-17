package at.fhj.swq;

import java.io.Serializable;

/**
 * Representing a player in the Millionaire Game.
 * While I was working on the storing player data it turned out helpful to handle the Player as an object.
 * The Player has a name, questionDone (to update the prize and level ) and money which actually saves what amount of money the player has.
 * In order to update the player data getter and setter were needed.
 * The toString method provides a formatted Stringr representation of the player, which is useful for the saving.
 */

public class Player implements Serializable {
    String playerName;
    int questionsDone;
    String money;

    public Player(String playerName, int questionsDone, String money) {
        this.playerName = playerName;
        this.questionsDone = questionsDone;
        this.money = money;
    }

    public String getName() {
        return playerName;
    }
    //since I have decided to handle Player as an Object it is useful to manage questionDone and money here

    public int getQuestionsDone() {
        return questionsDone;
    }

    public String getmoney() {
        return money;
    } //renaming the setter caused problems?

    public void calculateQuestionDone() {
        this.questionsDone = questionsDone + 1;
    }

    public void changeMoney(String money) {
        this.money = money;
    }

    //old writer.write format from Gameclass belongs here now --> easier calling the method instead of new formatting
    public String toString() {
        return playerName + "-" + questionsDone + "-" + "questions done" + "-" + "With a winning of:" + money + "\n";
    }
}
