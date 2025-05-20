package GeocodingApplication;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

class GeocodingResponseParser {

	private static final int FIRST_ELEMENT_INDEX = 0;
	private static final String JSON_LATITUDE_FIELD = "lat";
	private static final String JSON_LONGITUDE_FIELD = "lon";
	private static final int NO_RESULTS_FOUND = 0;

	public static Coordinates parseCoordinatesFromJson(String jsonResponse) {
		JsonElement rootElement = JsonParser.parseString(jsonResponse);

		if (!rootElement.isJsonArray()) {
			return null;
		}

		JsonArray resultsArray = rootElement.getAsJsonArray();
		if (resultsArray.size() == NO_RESULTS_FOUND) {
			return null;
		}

		JsonElement firstResult = resultsArray.get(FIRST_ELEMENT_INDEX);
		double latitude = firstResult.getAsJsonObject().get(JSON_LATITUDE_FIELD).getAsDouble();
		double longitude = firstResult.getAsJsonObject().get(JSON_LONGITUDE_FIELD).getAsDouble();

		return new Coordinates(latitude, longitude);
	}
}