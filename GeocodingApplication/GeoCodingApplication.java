package GeocodingApplication;

import java.util.Scanner;

public class GeoCodingApplication {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter place: ");
		String placeName = scanner.nextLine();
		scanner.close();

		Coordinates coordinates = GeocodingService.fetchCoordinatesByPlaceName(placeName);

		if (coordinates != null) {
			System.out.println("Latitude: " + coordinates.getLatitude());
			System.out.println("Longitude: " + coordinates.getLongitude());
		} else {
			System.out.println("Location not found.");
		}
	}
}