package GeocodingApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class GeocodingService {

	private static final String GEOCODING_API_URL = "https://nominatim.openstreetmap.org/search";
	private static final String USER_AGENT_HEADER = "User-Agent";
	private static final String USER_AGENT_VALUE = "JavaGeocodingApp/1.0 (your_email@example.com)";
	private static final String REQUEST_METHOD_GET = "GET";
	private static final int HTTP_SUCCESS_CODE = 200;
	private static final String QUERY_FORMAT = "format=json";
	private static final String QUERY_LIMIT = "limit=1";

	public static Coordinates fetchCoordinatesByPlaceName(String placeName) throws Exception {
		String encodedPlaceName = URLEncoder.encode(placeName, "UTF-8");
		String requestUrl = GEOCODING_API_URL + "?" + QUERY_FORMAT + "&q=" + encodedPlaceName + "&" + QUERY_LIMIT;

		URL url = new URL(requestUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(REQUEST_METHOD_GET);
		connection.setRequestProperty(USER_AGENT_HEADER, USER_AGENT_VALUE);

		int responseCode = connection.getResponseCode();
		if (responseCode != HTTP_SUCCESS_CODE) {
			return null;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder jsonResponseBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			jsonResponseBuilder.append(line);
		}
		reader.close();

		String jsonResponse = jsonResponseBuilder.toString();
		return GeocodingResponseParser.parseCoordinatesFromJson(jsonResponse);
	}
}