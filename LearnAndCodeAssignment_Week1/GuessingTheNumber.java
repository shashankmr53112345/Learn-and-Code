package LearnAndCodeAssignment_Week1;

import java.util.Random;
import java.util.Scanner;

public class GuessingTheNumber 
{
	public static boolean isGuessValid(String guessInput) {
        try {
            int guess = Integer.parseInt(guessInput);
            return guess >= 1 && guess <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        boolean isNumberGuessed = false;
        int attemptCount = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Guess a number between 1 and 100.");

        while (!isNumberGuessed) {
            System.out.print("Enter your guess: ");
            String userGuessInput = scanner.nextLine();

            if (!isGuessValid(userGuessInput)) {
                System.out.println("Invalid input. Please enter a number between 1 and 100.");
                continue;
            }

            attemptCount++;
            int userGuess = Integer.parseInt(userGuessInput);

            if (userGuess < randomNumber) {
                System.out.println("Too low. Try again.");
            } else if (userGuess > randomNumber) {
                System.out.println("Too high. Try again.");
            } else {
                System.out.println("Congratulations! You guessed the correct number in " + attemptCount + " attempts.");
                isNumberGuessed = true;
            }
        }
        scanner.close();
    }
}
