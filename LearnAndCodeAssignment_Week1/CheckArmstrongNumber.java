package LearnAndCodeAssignment_Week1;

import java.util.Scanner;

public class CheckArmstrongNumber {
	public static boolean checkIfArmstrongNumber(int numberToCheck) {
        int sumOfDigitPowers = 0;
        int digitCount = String.valueOf(numberToCheck).length();
        int temporaryNumber = numberToCheck;

        while (temporaryNumber > 0) {
            int currentDigit = temporaryNumber % 10;
            sumOfDigitPowers += Math.pow(currentDigit, digitCount); 
            temporaryNumber /= 10;
        }

        return sumOfDigitPowers == numberToCheck;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter a number to check if it's an Armstrong number: ");
        int numberToCheck = scanner.nextInt();

        if (checkIfArmstrongNumber(numberToCheck)) {
            System.out.println(numberToCheck + " is an Armstrong number.");
        } else {
            System.out.println(numberToCheck + " is NOT an Armstrong number.");
        }

        scanner.close();
    }
}
