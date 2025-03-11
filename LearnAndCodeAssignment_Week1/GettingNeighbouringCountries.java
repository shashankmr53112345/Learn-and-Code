package LearnAndCodeAssignment_Week1;

import java.util.Map;
import java.util.Scanner;

public class GettingNeighbouringCountries {
    
    private static final Map<String, String> COUNTRY_NEIGHBORS = Map.of(
        "IN", "Pakistan, China, Nepal, Bangladesh, Bhutan, Myanmar, Sri Lanka",
        "US", "Canada, Mexico",
        "NZ", "Australia",
        "CH", "India, Russia, Mongolia, Nepal, Pakistan, Kazakhstan, Myanmar, Vietnam",
        "FR", "Germany, Spain, Belgium, Italy, Switzerland, Luxembourg"
    );

    public static String getNeighboringCountries(String countryCode) {
        return COUNTRY_NEIGHBORS.getOrDefault(countryCode, null);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Neighboring Countries Finder");
        System.out.println("Enter a country code (IN, US, NZ, CH, FR) to find its neighboring countries.");
        System.out.println("Type 'EXIT' to quit.\n");

        while (true) {
            System.out.print("Enter Country Code: ");
            String countryCode = scanner.nextLine().toUpperCase();

            if ("EXIT".equals(countryCode)) {
                System.out.println("\nThank you!");
                break;
            }

            String neighbors = getNeighboringCountries(countryCode);

            if (neighbors != null) {
                System.out.println("Neighboring Countries for '" + countryCode + "': " + neighbors);
            } else {
                System.out.println("Invalid country code. Please try again.");
            }
        }

        scanner.close();
    }
}
