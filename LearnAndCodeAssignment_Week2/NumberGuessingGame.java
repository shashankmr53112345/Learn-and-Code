package LearnAndCodeAssignmenr_Week2;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 100;

	public static boolean isGuessValid(String guessInput) {
		try {
			int guess = Integer.parseInt(guessInput);
			return guess >= MIN_NUMBER && guess <= MAX_NUMBER;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		Random random = new Random();
		int randomNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
		boolean isNumberGuessed = false;
		int attemptCount = 0;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");

		while (!isNumberGuessed) {
			System.out.print("Enter your guess: ");
			String userGuessInput = scanner.nextLine();

			if (!isGuessValid(userGuessInput)) {
				System.out.println(
						"Invalid input. Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
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
