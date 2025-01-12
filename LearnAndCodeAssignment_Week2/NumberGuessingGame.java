
import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.startGame();
    }


    public void startGame() {
        int targetNumber = generateRandomNumber();
        int numberOfGuesses = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");

        while (!hasGuessedCorrectly) {
            String userInput = getUserInput();
            if (!isValidGuess(userInput)) {
                System.out.println("Invalid input. Please enter a number between 1 and 100.");
                continue;
            }

            numberOfGuesses++;
            int guessedNumber = Integer.parseInt(userInput);
            hasGuessedCorrectly = checkGuess(guessedNumber, targetNumber, numberOfGuesses);
        }
    }


    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        return scanner.nextLine();
    }

    private boolean isValidGuess(String guess) {
        try {
            int number = Integer.parseInt(guess);
            return number >= 1 && number <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkGuess(int guessedNumber, int targetNumber, int numberOfGuesses) {
        if (guessedNumber < targetNumber) {
            System.out.println("Too low! Try again.");
            return false;
        } else if (guessedNumber > targetNumber) {
            System.out.println("Too high! Try again.");
            return false;
        } else {
            System.out.println("Congratulations! You guessed the number in " + numberOfGuesses + " attempts.");
            return true;
        }
    }
}

