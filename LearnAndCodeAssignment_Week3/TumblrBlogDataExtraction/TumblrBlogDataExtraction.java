package TumblrBlogDataExtraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// This program extracts data from the Tumblr Blog API.
public class TumblrBlogDataExtraction {

	private static final String API_URL_TEMPLATE = "https://%s.tumblr.com/api/read/json?num=%d&start=%d";
	private static final int MAX_POSTS_PER_REQUEST = 50;
	private static final int INVALID_INDEX = -1;
	private static final int OFFSET = 1;
	private static final String JSON_START_CHAR = "{";
	private static final String JSON_END_CHAR = "}";
	private static final String POSTS_KEY = "\"posts\":[";
	private static final String POST_SEPARATOR = "}\},{";
	private static final String PHOTO_URL_KEY = "\"photo-url-1280\":\"";
	private static final String TITLE_KEY = "\"title\":\"";
	private static final String NAME_KEY = "\"name\":\"";
	private static final String DESCRIPTION_KEY = "\"description\":\"";
	private static final String TOTAL_POSTS_KEY = "\"posts-total\":";
	private static final String STRING_QUOTE = "\"";
	private static final String COMMA_SEPARATOR = ",";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Tumblr blog name (e.g., good): ");
		String blogName = scanner.nextLine();

		// Try to keep the range in such a way that the difference is less than 50 as
		// this API can give only 50 posts data for a get response.
		System.out.print("Enter the post range (e.g., 1-5): ");
		String postRange = scanner.nextLine();

		String[] rangeParts = postRange.split("-");
		int startPost = Integer.parseInt(rangeParts[0]);
		int endPost = Integer.parseInt(rangeParts[1]);
		int totalPosts = endPost - startPost + OFFSET;

		if (totalPosts > MAX_POSTS_PER_REQUEST) {
			System.out.println(
					"Warning: Tumblr API only allows a maximum of " + MAX_POSTS_PER_REQUEST + " posts per request.");
			totalPosts = MAX_POSTS_PER_REQUEST;
		}

		int startIndex = startPost - OFFSET;
		String apiUrl = String.format(API_URL_TEMPLATE, blogName, totalPosts, startIndex);

		String jsonResponse = fetchApiResponse(apiUrl);
		if (jsonResponse == null || jsonResponse.isEmpty()) {
			System.err.println("Failed to fetch data from the Tumblr API.");
			return;
		}

		String formattedJsonResponse = formatJsonResponse(jsonResponse);
		if (formattedJsonResponse.isEmpty()) {
			System.err.println("Invalid JSON response from Tumblr API.");
			return;
		}

		displayBlogInfo(formattedJsonResponse, startPost);
	}

	private static String fetchApiResponse(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder responseBuilder = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					responseBuilder.append(line);
				}
				reader.close();
				return responseBuilder.toString();
			}
		} catch (Exception e) {
			System.err.println("Error while fetching API response: " + e.getMessage());
		}
		return null;
	}

	private static String formatJsonResponse(String response) {
		int jsonStart = response.indexOf(JSON_START_CHAR);
		int jsonEnd = response.lastIndexOf(JSON_END_CHAR);

		return (jsonStart != INVALID_INDEX && jsonEnd != INVALID_INDEX)
				? response.substring(jsonStart, jsonEnd + OFFSET)
				: "";
	}

	private static void displayBlogInfo(String jsonResponse, int startPost) {
		String title = extractJsonValue(jsonResponse, TITLE_KEY, STRING_QUOTE + COMMA_SEPARATOR);
		String blogName = extractJsonValue(jsonResponse, NAME_KEY, STRING_QUOTE + COMMA_SEPARATOR);
		String description = extractJsonValue(jsonResponse, DESCRIPTION_KEY, STRING_QUOTE + COMMA_SEPARATOR);
		String totalPosts = extractJsonValue(jsonResponse, TOTAL_POSTS_KEY, COMMA_SEPARATOR);

		System.out.println("Title: " + title);
		System.out.println("Blog Name: " + blogName);
		System.out.println("Description: " + description);
		System.out.println("Total Number of Posts: " + totalPosts);

		int currentPostNumber = startPost;
		int postsArrayStartIndex = jsonResponse.indexOf(POSTS_KEY);

		if (postsArrayStartIndex != INVALID_INDEX) {
			String postsArray = jsonResponse.substring(postsArrayStartIndex + POSTS_KEY.length(),
					jsonResponse.lastIndexOf(JSON_END_CHAR));
			String[] posts = postsArray.split(POST_SEPARATOR);

			for (String post : posts) {
				String photoUrl = extractJsonValue(post, PHOTO_URL_KEY, STRING_QUOTE + COMMA_SEPARATOR);
				System.out.print(currentPostNumber);

				if (photoUrl.isEmpty()) {
					System.out.println("No images found in this post.");
				} else {
					System.out.println(photoUrl);
				}
				currentPostNumber++;
			}
		}
	}

	private static String extractJsonValue(String json, String key, String endMarker) {
		int keyIndex = json.indexOf(key);

		if (keyIndex != INVALID_INDEX) {
			int valueStart = keyIndex + key.length();
			int valueEnd = json.indexOf(endMarker, valueStart);
			if (valueEnd != INVALID_INDEX) {
				return json.substring(valueStart, valueEnd).replace(STRING_QUOTE + STRING_QUOTE, STRING_QUOTE);
			}
		}
		return "";
	}
}
