package LearnAndCodeAssignment_Week1;

import java.util.Scanner;

public class GettingNeighbouringCountries {
	public static String getNeighboringCountries(String countryCode) {
        switch (countryCode) {
            case "IN":
                return "Pakistan, China, Nepal, Bangladesh, Bhutan, Myanmar, Sri Lanka";
            case "US":
                return "Canada, Mexico";
            case "NZ":
                return "Australia";
            case "CH":
                return "India, Russia, Mongolia, Nepal, Pakistan, Kazakhstan, Myanmar, Vietnam";
            case "FR":
                return "Germany, Spain, Belgium, Italy, Switzerland, Luxembourg";
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Neighboring Countries Finder");
        System.out.println("Enter a country code (IN, US, NZ, CH, FR) to find its neighboring countries.");
        System.out.println("Type 'EXIT' to quit.\n");

        while (true) {
            System.out.print("Enter Country Code: ");
            String countryCode = scanner.nextLine().toUpperCase();

            if (countryCode.equals("EXIT")) {
                System.out.println("\nThank you ");
                break;
            }
            String neighbors = getNeighboringCountries(countryCode);
            
            if (neighbors != null) {
                System.out.println("Neighboring Countries for '" + countryCode + "': " + neighbors);
            } 
            else {
                System.out.println("Invalid country code. Please try again.");
            }
        }

        scanner.close();
    }

}
