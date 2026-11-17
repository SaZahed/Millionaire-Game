# “Java Advanced” Practical Example

To practice the Java "Advanced" features, we are developing a game for the console.
It is a quiz, analogous to the well-known "Who Wants to Be a Millionaire?" or analogous to the “Millionenshow”.

Pull a branch from this repo and develop in your branch. Push regularly.

    branch name is your surname
(to check Branches see [https://git-iit.fh-joanneum.at/software-design-itm_msd/check-labsetup](https://git-iit.fh-joanneum.at/software-design-itm_msd/check-labsetup))

## Lab Session "SW_Qual" on 17th October 2025:
In our lab session on 17th October 2025 we will look at how far you have come, where you don't know what to do next, where you have/had problems...
**Everyone** briefly presents their status/findings/problems.

In the last part of the session we will go through a possible solution/implementation together.

## Contents of the example:
- Program flow, control structures
- Classes, inheritance, interfaces
- Console input and output
- Read and write files
- Exception handling
- Collections
- (De-)serialization

## General rules:
- Document your source code where necessary.
- Code that has been hard to write should not be hard to read.
- Mark places where you have adopted source code. (Stackoverflow, ChatGPT, etc.)
- Use descriptive class-, method- and variable-names.
- Format your source code (IntelliJ Auto Format: Ctrl+Alt+L)
- In general, pay attention to readable source code.
- Avoid "spaghetti code" in the finished project.
- Choose your Java library classes and functions yourself.
  - Explain why you use a specific class for file handling or a special collection class.
- Try to organize your code in packages.

## Gameplay:
- The game will start.
- The player is asked to enter his name.
- If a player with this name has already played and saved their game before being eliminated or answered all 15 questions correctly, they will be asked if they want to continue the existing game.
  If yes, the last game continues, otherwise it starts at question 1.

- The player is asked a question with 4 possible answers. Only one answer is correct.
- The player decides whether he wants to answer the question or cancel.
  - If he wants to cancel, he can choose whether he wants to save first or cancel the game completely.
    - Saving ends the game. He can continue it the next time the program starts.
    - Cancel ends the game. The player is shown his winnings. He is asked if he would like to play again.
  - If the player answers, the answer is checked.
    - If the answer is correct, his current winnings will be displayed.
      - If 15 questions are answered correctly, the player will be congratulated on winning the million. The game is over. He is asked if he would like to play again.
      - If there are questions left, the next question is presented to the player.
    - If the answer to a question is incorrect, the player will be shown his winnings. The game is over. He is asked if he would like to play again.

### Correct questions and winnings
"<-->" Marks a "security level". Once reached, the winnings cannot be lowered any further.
```
    1	100 €
    2	200 €
    3	300 €
    4	400 €
    5	500 €		<-->
    6	1.000 €
    7	2.000 €
    8	5.000 €
    9	10.000 €
    10	15.000 €	<-->
    11	30.000 €
    12	75.000 €
    13	150.000 €
    14	300.000 €
    15	1.000.000 €
```
## Framework conditions/requirements:
- Try to develop the game with appropriate classes, interfaces and inheritance structure.
- If you want, start with “Spaghetti Code” first to familiarize yourself with the language. But then make sure that you refactor your project and convert it to a class structure.
- The answers to the questions should be mixed up. It would be pretty boring if the first answer was always correct.
- Optional:
  - Questions in one level should be mixed up and chosen randomly.

### A starting point for your classes may be...
You are  NOT limited to the specified classes and their properties. The structure serves as food for thought for a possible implementation.
  - MillionaireGame
    - main()
  - Game
    - String playerName
    - int questionsDone
    - prizes[]
    - "List of" availableQuestions
    - run()
  - Question
    - text
    - "List of" answers
    - difficultyLevel
      - (Note: The DifficultyLevel indicates which level (1-15) a question is suitable for.)
  - Answer
    - text
    - isCorrect

### File structure (question collection):
Attached is a text file "questions.txt" with questions for the quiz. The file is structured as follows:

- 1st line: question
- 2nd line: Correct answer.
- Lines 3,4,5: Wrong answers.
- 6th line: difficulty level (0-14).
- 7th line: Next question.
- 8th line: Correct answer to the next question.
- etc.

Credit: The file has been created with the help of ChatGPT.

### File structure (savegame):
- You serialize and deserialize your game state in binary form (Serializable Interface or Externalizable Interface)
- You decide for yourself which data/classes you (de-)serialize.
