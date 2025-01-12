package TumblrBlogDataExtraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
// This program tries to extract data from TumblrBlog API.
public class TumblrBlogDataExtraction {

    private static final String API_BASE_URL_TEMPLATE = "https://%s.tumblr.com/api/read/json?num=%d&start=%d";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Tumblr blog name (e.g., good): ");
        String blogName = scanner.nextLine();
        // Try to keep the range in such a way that the difference is less than 50 as this API can give only 50 posts data for a get response.
        System.out.print("Enter the post range (e.g., 1-5): ");
        String postRange = scanner.nextLine();
        String[] rangePostsParts = postRange.split("-");
        int startPost = Integer.parseInt(rangePostsParts[0]);
        int endPost = Integer.parseInt(rangePostsParts[1]);
        int numPosts = endPost - startPost + 1;
        int startIndex = startPost - 1;
        String apiUrl = String.format(API_BASE_URL_TEMPLATE, blogName, numPosts, startIndex);
        String jsonResponse = fetchApiResponse(apiUrl);
        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.err.println("Failed to fetch data from the Tumblr API.");
            return;
        }
        String cleanResponse = cleanResponse(jsonResponse);
        if (cleanResponse.isEmpty()) {
            System.err.println("Invalid JSON response from Tumblr API.");
            return;
        }
        processAndDisplayBlogInfo(cleanResponse, startPost);
    }

    private static String fetchApiResponse(String apiUrl) {
        try {
            @SuppressWarnings("deprecation")
			URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            }
        } catch (Exception e) {
            System.err.println("Error while fetching API response: " + e.getMessage());
        }
        return null;
    }

    private static String cleanResponse(String response) {
        int jsonStart = response.indexOf('{');
        int jsonEnd = response.lastIndexOf('}');
        if (jsonStart != -1 && jsonEnd != -1) {
            return response.substring(jsonStart, jsonEnd + 1);
        }
        return "";
    }

    private static void processAndDisplayBlogInfo(String jsonResponse, int startPost) {
        String title = extractValue(jsonResponse, "\"title\":\"", "\",");
        String name = extractValue(jsonResponse, "\"name\":\"", "\",");
        String description = extractValue(jsonResponse, "\"description\":\"", "\",");
        String postsTotal = extractValue(jsonResponse, "\"posts-total\":", ",");
        System.out.println("Title: " + title);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Total number of posts: " + postsTotal);
        int currentPost = startPost;
        int startIndex = jsonResponse.indexOf("\"posts\":[");
        if (startIndex != -1) {
            String postsArray = jsonResponse.substring(startIndex + 9, jsonResponse.lastIndexOf("]"));
            String[] posts = postsArray.split("\\},\\{");
            for (String post : posts) {
                String photoUrl = extractValue(post, "\"photo-url-1280\":\"", "\",");
                System.out.print(currentPost + ". ");
                if (photoUrl.isEmpty()) {
                    System.out.println("No images found in this post.");
                } else {
                    System.out.println(photoUrl);
                }
                currentPost++;
            }
        }
    }
    
    private static String extractValue(String json, String key, String endMarker) {
        int keyIndex = json.indexOf(key);
        if (keyIndex != -1) {
            int start = keyIndex + key.length();
            int end = json.indexOf(endMarker, start);
            if (end != -1) {
                return json.substring(start, end).replace("\\\"", "\"");
            }
        }
        return "";
    }
}

