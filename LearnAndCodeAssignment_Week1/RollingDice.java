package LearnAndCodeAssignment_Week1;

import java.util.Random;
import java.util.Scanner;

public class RollingDice 
{
	public static int simulateDiceRoll(int NumberOfDiceSides) 
	{
		Random random = new Random();
	    return random.nextInt(NumberOfDiceSides) + 1; 
	}
	public static void main(String[] args) 
	{
		int NumberOfDiceSides = 6;
		boolean continueRolling = true;
	    Scanner scanner = new Scanner(System.in);

	    while (continueRolling) 
	    {
	    	System.out.print("Ready to roll the dice? Enter Q to Quit: ");
	        String userChoice = scanner.nextLine();
	        if (!userChoice.equalsIgnoreCase("q")) 
	        {
	        	int rolledResult = simulateDiceRoll(NumberOfDiceSides);
	        	System.out.println("You rolled a " + rolledResult);
	        } 
	        else 
	        {
	        	System.out.println("Thanks for playing!");
	            continueRolling = false;
	        }
	    }
	    scanner.close();
	    
	}
}
